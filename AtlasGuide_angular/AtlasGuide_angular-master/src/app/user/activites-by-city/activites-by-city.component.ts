import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ActivityService} from "../../../service/activity/activity.service";
import {City} from "../../../model/enumeration/City.enum";
import {Activity} from "../../../model/Activity.model";


@Component({
  selector: 'app-activites-by-city',
  templateUrl: './activites-by-city.component.html',
  styleUrl: './activites-by-city.component.css'
})
export class ActivitesByCityComponent implements OnInit{
  constructor(private route : ActivatedRoute , private activityService : ActivityService) {
  }
  city! : City ;
  activities : Activity[] = [];
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const city = params.get('city');
      if (city !== null) {
        this.city = City[city as keyof typeof City];
      }
    });

  }



}
