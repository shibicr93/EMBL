package com.embl.person.domain.response;

import com.embl.person.domain.models.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GetAllPersonsResponse {
    private List<Person> persons;

    public GetAllPersonsResponse(List<Person> personList) {
        this.persons = personList;
    }

    @JsonProperty("person")
    public List<Person> getPersons() {
        return persons;
    }

}
