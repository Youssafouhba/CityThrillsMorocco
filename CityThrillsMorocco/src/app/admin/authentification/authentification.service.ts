import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, tap, throwError} from "rxjs";
import {AgenceDto} from "../../models-dto/AgenceDto";
import {Agence} from "../../models/Agence";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  constructor(private http: HttpClient) { }

  login(data: {email: string; password: string}): Observable<any> {
    return this.http.post<any>('http://localhost:8080/authenticate',data).pipe(
      tap((data: any) => console.log(data)),
      catchError(err => throwError(() => err))
    )
  }

  register(agence: {
    password: string;
    phone: string;
    name: string;
    location: string;
    email: string
  }): Observable<any> {
    return this.http.post<AgenceDto>('http://localhost:8080/CityThrillsMorocco/Admin/register', {
      email: agence.email,
      name: agence.name,
      location: agence.location,
      phone: agence.phone,
      password: agence.password,
    }, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })}).pipe(
      map(agence => this.convertToAgence(agence))
    );
  }


  private convertToAgence(agence: AgenceDto): Agence {
    return {
      id: agence.id,
      name: agence.name,
      location: agence.location,
      phone: agence.phone,
      email: agence.email,
      password: agence.password,
    }
  }
}
