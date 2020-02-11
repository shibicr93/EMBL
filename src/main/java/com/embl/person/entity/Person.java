package com.embl.person.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String age;
    private String favouriteColour;
    @ElementCollection
    @CollectionTable(name = "person_hobby",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "hobby")
    private List<String> hobby;

    public Person() {
    }

    public Person(Long id, String firstName, String lastName, String age, String favouriteColour, List<String> hobby) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.hobby = hobby;
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

    public Long getId() {
        return id;
    }
}
