package com.embl.person.domain.models;

import com.embl.person.entity.PersonEntityBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("person")
public class Person {

    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("age")
    private String age;
    @JsonProperty("favourite_colour")
    private String favouriteColour;
    @JsonProperty("hobby")
    private List<String> hobby;

    public Person() {}

    public Person(Long id, String firstName, String lastName, String age, String favouriteColour, List<String> hobby) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.hobby = hobby;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public static Person fromEntity(com.embl.person.entity.Person personEntity) {
        return new PersonBuilder()
                .setId(personEntity.getId())
                .setFirstName(personEntity.getFirstName())
                .setLastName(personEntity.getLastName())
                .setAge(personEntity.getAge())
                .setFavouriteColour(personEntity.getFavouriteColour())
                .setHobby(personEntity.getHobby())
                .createPerson();
    }

    public static com.embl.person.entity.Person toEntity(Person person) {
        return new PersonEntityBuilder()
                .setId(person.getId())
                .setAge(person.getAge())
                .setFavouriteColour(person.getFavouriteColour())
                .setHobby(person.getHobby())
                .setFirstName(person.getFirstName())
                .setLastName(person.getLastName())
                .createPerson();

    }
}
