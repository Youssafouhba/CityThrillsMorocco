import {Component, Injectable, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthentificationService} from "../authentification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {SessionService} from "../../session.service";
import {NavigatorService} from "../../../navigator/navigator.service";
import {AlertsService} from "../../alerts/alerts.service";
import {FormsService} from "../../forms/forms.service";

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private token = '';
  loginForm!: FormGroup;

  constructor(
              private authService: AuthentificationService,
              private navigator: NavigatorService,
              private sessionService: SessionService,
              private route: ActivatedRoute,
              private form: FormsService,
              private alerts: AlertsService,
              ) {
  }

  ngOnInit(): void {
    this.loginForm = this.form.loginForm;
    this.alerts.loginAlerts(this.route.snapshot.queryParams['v'])
  }

  login() {

    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;
    if (this.loginForm.invalid) {
      return;
    }
    this.authService.login({email, password}).subscribe(
      response => {
        this.token = response.token;
        this.sessionService.setSessionData('user',
          {
            token: this.token,
            Uid: response.id,
            role: response.role,
            inAccount: true
          });
        console.log(response.id)
        this.navigator.login_navigator(response.role.name);
      },
      error => this.alerts.loginAlerts(error.status)
        );
  }

}
