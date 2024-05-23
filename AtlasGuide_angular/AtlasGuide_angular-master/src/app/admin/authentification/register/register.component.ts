import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "../authentification.service";
import {AlertsService} from "../../alerts/alerts.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {passwordValidator} from "../passwordValidator/password-validator";
import {FormsService} from "../../forms/forms.service";
import {NavigatorService} from "../../../navigator/navigator.service";
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  formSubmitted = false;
  showPassword: boolean = false;
  showPasswordconfirm = false;
  role= true;

  registrationForm: FormGroup;
  passwordConfirmation: FormGroup;


  constructor(private authService: AuthentificationService,
              private navigator: NavigatorService,
              private alertService: AlertsService,
              private route: ActivatedRoute,
              protected formBuilder: FormBuilder,
              private form: FormsService,)
  {
    this.registrationForm = this.form.userForm;
    this.passwordConfirmation = this.form.passwordConfirmation;
  }

  ngOnInit() {
    const queryParams = this.route.snapshot.queryParams;
    this.authService.verifyAccount(queryParams['token']).subscribe(
      messages => {
        console.log(JSON.stringify(messages));
      },
      error => this.navigator.register_navigator(error.status)
    );
  }

  roleState(role: boolean){
    this.role = role;
  }

  register() {
    this.formSubmitted = true;
    setTimeout(() => {
      this.formSubmitted = false;
    },6000)
    if (this.registrationForm.valid) {
      this.authService.register(this.registrationForm.value,this.role).subscribe(
        messages => {
          console.log(JSON.stringify(messages));
        },
        error => this.alertService.registerAlert(error.status)
      );
    }
  }



}
