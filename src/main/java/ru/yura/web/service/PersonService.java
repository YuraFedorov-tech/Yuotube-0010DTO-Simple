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
import ru.yura.web.repositiry.PersonRepositiry;

import java.util.List;


@Service
public class PersonService {
     final PersonRepositiry personRepositiry;

    public PersonService(PersonRepositiry personRepositiry) {
        this.personRepositiry = personRepositiry;
    }


    @Transactional
    public void save(Person person) {
        personRepositiry.save(person);
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
