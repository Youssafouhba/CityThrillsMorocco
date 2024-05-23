import {Component, ElementRef, OnInit, Renderer2} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ActivityService} from "../../../service/activity/activity.service";
import {Activity} from "../../models/Activity";


@Component({
  selector: 'app-activity-details',
  templateUrl: './activity-details.component.html',
  styleUrls: ['./activity-details.component.css']
})
export class ActivityDetailsComponent implements OnInit{
  isDescriptionTabSelected = true;
  isProgramTabSelected = false;
  isReviewTabSelected = false;
  isMapTabSelected = false;
  activityId !: number;

  activity!: Activity;


  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id !== null) {
        this.activityId = +id;

      }
    });
    this.getActivity();
  }
  constructor(private renderer: Renderer2, private el: ElementRef , private activityService : ActivityService, private route: ActivatedRoute) { }

  onProgramTabClick() {
    this.isProgramTabSelected=true;
    this.isDescriptionTabSelected=false;
    this.isMapTabSelected=false;
    this.isReviewTabSelected=false;
    const programContent = this.el.nativeElement.querySelector('#program').innerHTML;
    const overviewContent = this.el.nativeElement.querySelector('.overview-content');

    this.renderer.setProperty(overviewContent, 'innerHTML', programContent);
  }
  onDescriptionTabClick() {
    this.isDescriptionTabSelected = true;
    this.isProgramTabSelected = false;
    this.isReviewTabSelected = false;
    this.isMapTabSelected = false;
    const descriptionContent = this.el.nativeElement.querySelector('#overview').innerHTML;
    const Content = this.el.nativeElement.querySelector('.overview-content');
    this.renderer.setProperty(Content, 'innerHTML', descriptionContent);
  }
  onReviewTabClick() {
    this.isDescriptionTabSelected = false;
    this.isProgramTabSelected = false;
    this.isReviewTabSelected = true;
    this.isMapTabSelected = false;
    const reviewContent = this.el.nativeElement.querySelector('#review').innerHTML;
    const Content = this.el.nativeElement.querySelector('.overview-content');
    this.renderer.setProperty(Content, 'innerHTML', reviewContent);
  }
  onMapTabClick() {
    this.isDescriptionTabSelected = false;
    this.isProgramTabSelected = false;
    this.isReviewTabSelected = false;
    this.isMapTabSelected = true;
    const mapContent = this.el.nativeElement.querySelector('#map').innerHTML;
    const Content = this.el.nativeElement.querySelector('.overview-content');
    this.renderer.setProperty(Content, 'innerHTML', mapContent);
  }



  //data

 getActivity(): void {
    this.activityService.getActivity(this.activityId).subscribe(activity => {
      this.activity = activity;
    });

 }


}
