import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SessionService} from "../session.service";
import {FiltersService} from "../filter/filters.service";
import {NavigatorService} from "../../navigator/navigator.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: [`./sidebar.component.css`]
})
export class SidebarComponent implements OnInit {

  constructor(public session: SessionService,
              protected navigator: NavigatorService,
              protected filterService: FiltersService,) {}
  isActivitiesClicked: boolean = false;
  hid2 = false;
  hid1 = true;
  stateFilters = ["Active","Expired","Pending"];
  onActivitiesClick() {
    this.isActivitiesClicked = !this.isActivitiesClicked;
  }
  ngOnInit() {

  }

  hide() {
    const elements = document.getElementsByClassName('dashboard-navigation');
    const containerWrapper = document.getElementsByClassName('dashboard-container');
    //const content_body = document.getElementsByClassName('dashboard-container');
    if (elements.length > 0) {
      if (this.hid1) {
        elements[0].classList.add('slide-in');
        elements[0].classList.remove('slide-out');
        containerWrapper[0].classList.add('slide-in1');
        containerWrapper[0].classList.remove('slide-out1');
       // content_body[0].classList.add('slide-in2')
        //content_body[0].classList.remove('slide-out2');
      } else {
        elements[0].classList.add('slide-out');
        elements[0].classList.remove('slide-in');
        containerWrapper[0].classList.add('slide-out1');
        containerWrapper[0].classList.remove('slide-in1');
        //content_body[0].classList.add('slide-out2')
        //content_body[0].classList.remove('slide-in2');
      }
    }
    this.hid1=!this.hid1;
    this.hid2=!this.hid2;
  }

}
