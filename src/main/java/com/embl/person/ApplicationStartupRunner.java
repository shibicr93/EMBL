package com.embl.person;

import com.embl.person.domain.models.Person;
import com.embl.person.domain.models.PersonBuilder;
import com.embl.person.service.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("ApplicationStartupRunner run method Started !!");
        Person person = new PersonBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .setFavouriteColour("Blue")
                .setAge("26")
                .createPerson();
        personService.updatePerson(person);

        Person person1 = new PersonBuilder()
                .setFirstName("John")
                .setLastName("Smith")
                .setFavouriteColour("Green")
                .setAge("28")
                .createPerson();
        personService.updatePerson(person1);

        Person person2 = new PersonBuilder()
                .setFirstName("Mincy")
                .setLastName("Williams")
                .setFavouriteColour("Red")
                .setAge("26")
                .createPerson();
        personService.updatePerson(person2);

        Person person3 = new PersonBuilder()
                .setFirstName("Sania")
                .setLastName("Mirza")
                .setFavouriteColour("Pink")
                .setAge("30")
                .createPerson();
        personService.updatePerson(person3);



    }
}