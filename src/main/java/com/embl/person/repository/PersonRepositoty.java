package com.embl.person.repository;

import com.embl.person.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoty extends CrudRepository<Person, Long> {


}
