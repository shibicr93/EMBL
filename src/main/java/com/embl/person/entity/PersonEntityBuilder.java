package com.embl.person.entity;

import java.util.List;

public class PersonEntityBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String age;
    private String favouriteColour;
    private List<String> hobby;

    public PersonEntityBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonEntityBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonEntityBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonEntityBuilder setAge(String age) {
        this.age = age;
        return this;
    }

    public PersonEntityBuilder setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
        return this;
    }

    public PersonEntityBuilder setHobby(List<String> hobby) {
        this.hobby = hobby;
        return this;
    }

    public Person createPerson() {
        return new Person(id, firstName, lastName, age, favouriteColour, hobby);
    }
}