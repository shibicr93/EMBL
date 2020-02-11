import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Person } from '../models/person.model';
import { PersonService } from './person.service';

@Component({
  templateUrl: './add-person.component.html'
})
export class AddPersonComponent {

  person: Person = new Person();

  constructor(private router: Router, private personService: PersonService) {

  }

  createPerson(): void {
    this.personService.createPerson(this.person)
      .subscribe( data => {
        alert("Person created successfully.");
      });

  };

}
