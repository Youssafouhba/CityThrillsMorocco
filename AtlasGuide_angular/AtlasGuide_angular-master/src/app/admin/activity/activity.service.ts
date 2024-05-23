import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environnment} from "../../environnment/environnment";
import {ActivityDto} from "../../models-dto/activity-dto";
import {Activity} from "../../models/Activity";
import {map, Observable} from "rxjs";
import {SessionService} from "../session.service";
import colors from "tailwindcss/colors";
import {Browser} from "leaflet";
import retina = Browser.retina;
import {Reservation} from "../../models/Reservation";
import {User} from "../../models/User";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  data!: Activity[];
  private activityYRL = environnment.authURL+ "/activities";

  constructor(
    private http: HttpClient,
    private sessionService: SessionService)
  {}



  getActivities(): Observable<Activity[]> {
    return this.http.get<ActivityDto[]>(this.activityYRL).pipe(
      map(activities => activities.map(activity => {
        return this.convertToActivity(activity)
      }))
    );
  }

  getAgencyActivitiesById(agenceId: number): Observable<Activity[]>  {
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.sessionService.getSessionData('user').token);
    return this.http.get<ActivityDto[]>(this.activityYRL+'/agence/'+agenceId, {headers }).pipe(
      map(activities => activities.map(activity => {
        return this.convertToActivity(activity)
      }))
    );
  }

  getActivity(id: number): Observable<Activity> {
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.sessionService.getSessionData('user').token);
    return this.http.get<ActivityDto>(this.activityYRL+'/'+id,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }).
    pipe(
      map(activity => this.convertToActivity(activity))
    )
  }

  addActivity(data: Activity,agenceId: number): Observable<Activity> {
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.sessionService.getSessionData('user').token)
      .set('Content-Type', 'application/json');
    return this.http.post<ActivityDto>(this.activityYRL+'/'+agenceId,data,{headers}).pipe(
      map(activity => this.convertToActivity(activity))
    );
  }

  updateActivity(data: Activity,id: number): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.sessionService.getSessionData('user').token);
    return this.http.put<void>(this.activityYRL +'/'+id,data,{headers});
  }

  deleteActivity(id: number): Observable<any> {
    return this.http.delete<void>(this.activityYRL+'/'+id);
  }

  private convertToActivity(activity: ActivityDto): Activity{
    return {
      id: activity.id,
      designation: activity.designation,
      description: activity.description,
      price: activity.price,
      descriptionDetail: activity.descriptionDetail,
      location: activity.location,
      imageUrl: activity.imageUrl,
      category: activity.category,
      city: activity.city,
      startDate: activity.startDate,
      endDate: activity.endDate,
      bookingStartDate: activity.bookingStartDate,
      bookingEndDate: activity.bookingEndDate,
      participants: activity.participants,
      maxParticipants: activity.maxParticipants,
      isPlacesLimited: activity.isPlacesLimited,
      isFlexibleDates: activity.isFlexibleDates,
      isAvailableYearRound: activity.isAvailableYearRound,
      status: activity.status,
      program: activity.program
    };
  }

}
