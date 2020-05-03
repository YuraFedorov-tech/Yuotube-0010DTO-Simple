package ru.yura.web.service;
/*
 *
 *@Data 13.04.2020
 *@autor Fedorov Yuri
 *@project Bootstrap3.1.3
 *
 */

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yura.web.model.Person;
import ru.yura.web.model.Role;
import ru.yura.web.repositiry.PersonRepositiry;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    final PersonRepositiry personRepositiry;
    final RoleService roleService;

    public PersonService(PersonRepositiry personRepositiry, RoleService roleService) {
        this.personRepositiry = personRepositiry;
        this.roleService = roleService;
    }


    @Transactional
    public void save(Person person, List<String> roles) {
        List<Role> roles1 = new ArrayList<>();
        for(String role:roles)
            roles1.add(roleService.findByRole(role));
        personRepositiry.save(person);
        person.setRoles(roles1);
    }



    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepositiry.findAll();
    }

    @Transactional(readOnly = true)
    public Person findById(Long id) {
        return personRepositiry.findPersonById(id);
    }

}
