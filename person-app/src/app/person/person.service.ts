import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Person } from '../models/person.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PersonService {

  constructor(private http:HttpClient) {}

  //private personUrl = 'http://localhost:8080/person-portal/person';
  private personUrl = 'http://localhost:8080/person';

  public getPersons() {
    return this.http.get<Person[]>(this.personUrl);
  }

  public deletePerson(person) {
    return this.http.delete(this.personUrl + "/"+ person.id);
  }

  public createPerson(person) {
    return this.http.post<Person>(this.personUrl, person);
  }

}
