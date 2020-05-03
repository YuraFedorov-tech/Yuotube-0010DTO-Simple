package ru.yura.web.app;
/*
 *
 *@Data 03.05.2020
 *@autor Fedorov Yuri
 *@project Bootstrap3.1.3
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import ru.yura.web.model.Role;
import ru.yura.web.service.PersonService;
import ru.yura.web.service.RoleService;

public class TestDataInit {
    @Autowired
    RoleService roleService;
    @Autowired
    PersonService personService;

    private void init() throws Exception {
     Role roleAdmin= new Role().setRole("ADMIN");
        Role rolePerson= new Role().setRole("PERSON");
        Role roleModerator= new Role().setRole("MODERATOR");
        roleService.save(roleAdmin);
        roleService.save(rolePerson);
        roleService.save(roleModerator);


    }
}
