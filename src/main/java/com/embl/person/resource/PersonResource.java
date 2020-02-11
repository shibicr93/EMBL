package com.embl.person.resource;

import com.embl.person.domain.models.Person;
import com.embl.person.domain.response.CreatePersonResponse;
import com.embl.person.domain.response.GetAllPersonsResponse;
import com.embl.person.domain.response.GetPersonResponse;
import com.embl.person.domain.response.UpdatePersonResponse;
import com.embl.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PersonResource {

    @Autowired
    private PersonService<Person> personService;

    @GetMapping
    public GetAllPersonsResponse getAllPersons() {
        List<Person> personList = personService.getAllPersons();
        return new GetAllPersonsResponse(personList);
    }

    @GetMapping(path ={"/{id}"})
    public GetPersonResponse getPerson(@PathVariable("id") Long id) {
        return new GetPersonResponse();
    }

    @PostMapping
    public CreatePersonResponse createPerson(@RequestBody Person person ) {
        Person updatedPerson = personService.updatePerson(person);
        return new CreatePersonResponse(updatedPerson);
    }

    @DeleteMapping(path ={"/{id}"})
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }

    @PutMapping
    public UpdatePersonResponse updatePerson() {
        return new UpdatePersonResponse();
    }


}
