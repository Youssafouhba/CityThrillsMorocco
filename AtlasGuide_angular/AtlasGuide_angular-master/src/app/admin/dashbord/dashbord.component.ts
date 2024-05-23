import {Component, OnInit} from '@angular/core';
import {SessionService} from "../session.service";
import {Router} from "@angular/router";
import {FiltersService} from "../filter/filters.service";

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.css','./dashboard.component1.css','./dashboard.component2.css','./dashboard.component3.css']
})
export class DashbordComponent implements  OnInit {
  constructor(
    private sessionService: SessionService,
    private  router: Router,
    protected filterService: FiltersService) {}

  ngOnInit() {

  }
}
