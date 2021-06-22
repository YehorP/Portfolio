import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Person } from "../models/Person.model";

@Injectable({
    providedIn: 'root'
})

export class LoginService {

    constructor(
        private http: HttpClient
    ){}

    login(credentials: {login: string, password: string}): Observable<Person> {
       return this.http.post<Person>(`${environment.url}/user/login`, credentials);
    }
}