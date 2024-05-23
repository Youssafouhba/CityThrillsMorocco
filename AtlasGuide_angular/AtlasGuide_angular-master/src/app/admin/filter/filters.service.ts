import {Injectable} from '@angular/core';
import {Activity} from "../../models/Activity";
import {SessionService} from "../session.service";

@Injectable({
  providedIn: 'root'
})
export class FiltersService {

  constructor(
    private sessionService: SessionService
  ) { }

  roleFilter(role: string){
    if (this.sessionService.getSessionData('user'))
      return this.sessionService.getSessionData('user').role.name === role;
    return false
  }

  applyFilter(condition: boolean,data: Activity[]) {
    return data.filter(activity => {
      return activity.isAvailableYearRound === condition;
    });
  }

  StateFilter(condition: string,data: Activity[]) {
    return data.filter(activity => {
      return activity.status === condition;
    });
  }

}
