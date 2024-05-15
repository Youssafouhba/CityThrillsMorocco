import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environnment} from "../../environnment/environnment";
import {ActivityDto} from "../../models-dto/activity-dto";
import {Activity} from "../../models/Activity";
import {map, Observable} from "rxjs";
import {ActivityCategories} from "../enumerations/activity-categories";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  private activityYRL = environnment.adminURL + "/activities";

  constructor(private http: HttpClient) { }

  getActivities(): Observable<Activity[]> {
    return this.http.get<ActivityDto[]>(this.activityYRL).pipe(
      map(activities => activities.map(activity => {
        return this.convertToActivity(activity)
      }))
    );
  }



  getActivity(id: number): Observable<Activity> {
    return this.http.get<ActivityDto>(this.activityYRL+'/'+id).
    pipe(
      map(activity => this.convertToActivity(activity))
    )
  }

  addActivity(data: Activity): Observable<Activity> {
    return this.http.post<ActivityDto>(this.activityYRL, data).pipe(
      map(activity => this.convertToActivity(activity))
    );
  }

  updateActivity(data: Activity,id: number): Observable<void> {
    return this.http.put<void>(this.activityYRL +'/'+data.id,{ data,id});
  }

  deleteActivity(id: number): Observable<void> {
    return this.http.delete<void>(this.activityYRL+'/'+id);
  }

  private convertToActivity(activity: ActivityDto): Activity {
    return {
      id: activity.id,
      designation: activity.designation,
      starteddate: activity.starteddate,
      finisheddate: activity.finisheddate,
      startedduration: activity.startedduration,
      finishedduration: activity.finishedduration,
      description: activity.description,
      price: activity.price,
      descriptiondetail: activity.descriptiondetail,
      location: activity.location,
      imageUrl: activity.imageUrl,
      category: activity.category,
      status: activity.status,
    };
  }
}
