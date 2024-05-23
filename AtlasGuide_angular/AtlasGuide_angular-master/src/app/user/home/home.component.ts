import {Component, OnInit} from '@angular/core';
import {SessionService} from "../../admin/session.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  constructor(private sessionService: SessionService) {}

  ngOnInit(): void {
        this.sessionService.getSessionData('user').data.inAccount = false
    console.log(this.sessionService.getSessionData('user').data.inAccount)
    }
}
