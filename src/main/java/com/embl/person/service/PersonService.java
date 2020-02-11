package com.embl.person.service;

import com.embl.person.domain.models.Person;

import java.util.List;

public interface PersonService<Person> {

    List<Person> getAllPersons();

    Person findById(Long id);

    void deletePerson(Long id);

    Person updatePerson(Person person);

}
