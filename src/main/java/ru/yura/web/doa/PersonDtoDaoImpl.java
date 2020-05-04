package ru.yura.web.doa;
/*
 *
 *@Data 04.05.2020
 *@autor Fedorov Yuri
 *@project Bootstrap3.1.3
 *
 */

import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import ru.yura.web.dto.PersonDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class PersonDtoDaoImpl implements PersonDtoAbstract {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PersonDto> findAllDto() {
        List<PersonDto> personDtoList = entityManager.createQuery(
                "SELECT p.id, p.email, p.password, r.role FROM  Person p LEFT JOIN p.roles r"
        ).unwrap(Query.class)
                .setResultTransformer(new PersonDtoImplIn())
                .list();
        return personDtoList;
    }

    private class PersonDtoImplIn implements ResultTransformer {
        List<PersonDto> roots = new ArrayList<>();
        Map<Long, List<String>> rolesMap = new HashMap<>();

        @Override
        public Object transformTuple(Object[] tuple, String[] strings) {
            Long id = (Long) tuple[0];
            String email = (String) tuple[1];
            String password = (String) tuple[2];
            String role = (String) tuple[3];

            PersonDto personDto = new PersonDto(id, email, password);

            if (!rolesMap.containsKey(id)) {
                roots.add(personDto);
                rolesMap.put(id, new ArrayList<>());
            }
            if (role != null) {
                rolesMap.get(id).add(role);
            }
            return roots;
        }

        @Override
        public List transformList(List list) {
            for (PersonDto personDto : roots) {
                List<String> roles = rolesMap.get(personDto.getId());
                personDto.setRoles(roles);
            }
            return roots;
        }
    }
}
