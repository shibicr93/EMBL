package com.embl.person.service;

import com.embl.person.domain.models.Person;
import com.embl.person.domain.models.PersonBuilder;
import com.embl.person.repository.PersonRepositoty;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService<Person> {

    @Autowired
    private PersonRepositoty personRepositoty;

    private static Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public List<Person> getAllPersons() {
        Iterable<com.embl.person.entity.Person> personEntityList = personRepositoty.findAll();
        List<Person> personList = new ArrayList<>();

        personEntityList.forEach(personEntity -> {
            Person person = Person.fromEntity(personEntity);
            personList.add(person);
        });

        return personList;
    }

    @Override
    public Person findById(Long id) {
        com.embl.person.entity.Person personEntity = personRepositoty.findById(id).get();
        return Person.fromEntity(personEntity);
    }

    @Override
    public void deletePerson(Long id) {
        personRepositoty.deleteById(id);
    }

    @Override
    public Person updatePerson(Person person) {
        com.embl.person.entity.Person personUpdated = personRepositoty.save(Person.toEntity(person));
        LOGGER.info("person inserted");
        return Person.fromEntity(personUpdated);
    }

}
