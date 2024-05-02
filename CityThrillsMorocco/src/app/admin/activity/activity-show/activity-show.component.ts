import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subject, takeUntil} from "rxjs";
import {ActivityService} from "../activity.service";

@Component({
  selector: 'app-activity-show',
  templateUrl: './activity-show.component.html',
  styleUrls: ['./activity-show.component.css']
})
export class ActivityShowComponent implements OnDestroy, OnInit {

  @Input() id = -1;
  designation = '';
  private activitySub = new Subject<void>();

  constructor(private activityService: ActivityService) { }

  ngOnDestroy(): void {
    this.activitySub.next();
    this.activitySub.complete();
  }

  ngOnInit(): void {
    this.getActivity();
  }

  private getActivity() {
    this.activityService.getActivity(this.id).pipe(
      takeUntil(this.activitySub)
    ).subscribe(activity => {
      if (activity) {
        this.designation = activity.designation;
      }
    });
  }

}
