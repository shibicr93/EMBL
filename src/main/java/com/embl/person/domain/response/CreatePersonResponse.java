package com.embl.person.domain.response;

import com.embl.person.domain.models.Person;

public class CreatePersonResponse {
    private Person person;

    public CreatePersonResponse(Person updatedPerson) {
        this.person = updatedPerson;
    }

    public Person getPerson() {
        return person;
    }
}
