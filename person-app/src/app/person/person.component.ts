import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Person } from '../models/person.model';
import { PersonService } from './person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styles: []
})
export class PersonComponent implements OnInit {

  persons: Person[];

  constructor(private router: Router, private personService: PersonService) {

  }

  ngOnInit() {
    this.personService.getPersons()
      .subscribe( data => {
        console.log(data)
        this.persons = data['person'];
      });
  };

  deletePerson(person: Person): void {
    this.personService.deletePerson(person)
      .subscribe( data => {
        this.persons = this.persons.filter(u => u !== person);
      })
  };

}
