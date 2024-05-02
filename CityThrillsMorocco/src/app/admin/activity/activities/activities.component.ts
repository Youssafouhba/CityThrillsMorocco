import {Component, OnInit} from '@angular/core';
import {Activity} from "../../../models/Activity";
import {ActivityService} from "../activity.service";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {Product} from "../../products/product";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit{

  activities: Activity[] = [];

  constructor(private activityService: ActivityService) {
  }

  ngOnInit() {
    this.getActivities();
  }

  reorder(event: CdkDragDrop<Activity[]>) {
    moveItemInArray(this.activities, event.previousIndex, event.currentIndex);
  }

  private getActivities(){
    this.activityService.getActivities().subscribe(activities => this.activities = activities)
  }
}
