import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Activity} from "../../app/models/Activity";


@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  private baseUrl = 'http://localhost:8083/activity';
  private comment_Url = 'http://localhost:8083/comment';

  constructor(private http : HttpClient) { }

  getAllActivities(): Observable<Activity[]> {
    return this.http.get<Activity[]>(`http://localhost:8080/CityThrillsMorocco/Admin/activities`);
  }

  getActivitiesWithHighRating () : Observable<Activity[]> {
    return this.http.get<Activity[]>(`${this.comment_Url}/comments_with_High_Rating`);
  }

  getNote(activity_id : number) : Observable<number>{
    return this.http.get<number>(`${this.comment_Url}/note/${activity_id}`);
  }

  getNumberOfComments (activity_id : number) : Observable<number>{
    return this.http.get<number>(`${this.comment_Url}/numberOfComments/${activity_id}`);
  }

  getActivity(activity_id : number) : Observable<Activity>{
    return this.http.get<Activity>(`${this.baseUrl}/${activity_id}`);
  }
}
