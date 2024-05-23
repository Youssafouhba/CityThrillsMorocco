import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {SessionService} from "../session.service";
import {Router} from "@angular/router";
import {User} from "../../models/User";
import {environnment} from "../../environnment/environnment";

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  constructor(private http: HttpClient,
              private sessionService: SessionService,
              private router: Router,) { }

  isAuthenticated(){
    const user = this.sessionService.getSessionData('user');
    if (!user){
      this.router.navigate(["/Admin_login"])
    }
  }

  login(data: {email: string; password: string}): Observable<any> {
    return this.http.post<any>(environnment.authURL+'/login',data).pipe(
      tap((data: any) => console.log(data)),
      catchError(err => throwError(() => err))
    )
  }

  register(user: User,role: boolean): Observable<any> {
    let roleName = "ROLE_USER"
    if (role) roleName = "ROLE_CONTENT_MANAGER"
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.sessionService.getSessionData('user').token)
      .set('Content-Type', 'application/json');
    return this.http.post<User>(environnment.authURL+'/register/'+roleName, user, {headers});
  }

  resetPasword(email: string): Observable<any>  {
    return this.http.post<any>(environnment.authURL+'/account/Reset-password',email,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  registerNewPassword(token: string,password: string): Observable<any>{
    return this.http.post<any>(environnment.authURL+'/account/New_password?token='+token,password,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })} );
  }

  verifyAccount(token: string): Observable<any> {
    return this.http.get<any>(environnment.authURL+'/account/confirm-account?token='+token,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

}
