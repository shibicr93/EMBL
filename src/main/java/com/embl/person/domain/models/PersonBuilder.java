package com.embl.person.domain.models;

import java.util.List;

public class PersonBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String age;
    private String favouriteColour;
    private List<String> hobby;

    public PersonBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setAge(String age) {
        this.age = age;
        return this;
    }

    public PersonBuilder setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
        return this;
    }

    public PersonBuilder setHobby(List<String> hobby) {
        this.hobby = hobby;
        return this;
    }

    public PersonBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public Person createPerson() {
        return new Person(id, firstName, lastName, age, favouriteColour, hobby);
    }


}