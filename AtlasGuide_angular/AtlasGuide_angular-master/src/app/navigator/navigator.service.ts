import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {SessionService} from "../admin/session.service";

@Injectable({
  providedIn: 'root'
})
export class NavigatorService {

  constructor(
    private router: Router,
    private session: SessionService,
  ) { }

  login_navigator(role: string){
    if(role != 'ROLE_USER'){
      this.router.navigate(['/dashboard'])
    }else {
      this.router.navigate(['/account'])
    }
  }

  register_navigator(codestatus: number){
   if (codestatus==200) this.router.navigate(['/Admin_login'],{ queryParams: { v: '1' } });
  }


  logout() {
    this.session.clearSessionData('user');
    this.router.navigate(['/']);
  }
}
