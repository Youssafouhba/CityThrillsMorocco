import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AccountService} from "../account.service";
import {Agence} from "../../../models/Agence";
import {FiltersService} from "../../filter/filters.service";


@Injectable({
  providedIn: 'root'
})
export class AccountResolver implements Resolve<any> {
  constructor(private accountService: AccountService,protected filterService: FiltersService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
    if(this.filterService.roleFilter('ROLE_CONTENT_MANAGER')) return this.accountService.getAgence();
    return this.accountService.getUser();
  }
}
