import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: [`./sidebar.component.css`]
})
export class SidebarComponent implements OnInit {

  constructor(public session: SessionService,
              private router: Router,) {}

  ngOnInit() {

  }



  logout() {
    this.session.clearSessionData('user');
    this.router.navigate(['/admin']);
  }
}
