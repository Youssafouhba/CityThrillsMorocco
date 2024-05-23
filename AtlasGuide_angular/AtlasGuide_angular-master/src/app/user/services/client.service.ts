import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})

export class ClientService {

    constructor(private http: HttpClient) {}
    private apiUrl = 'http://localhost:8080/api/clients';

    postClient(client: any): Observable<any> {
        return this.http.post(this.apiUrl,client);
    }
    getALlClient(): Observable<any>{
        return this.http.get(this.apiUrl)
    }








}
