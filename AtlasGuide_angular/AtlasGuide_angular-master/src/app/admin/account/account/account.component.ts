import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "../../authentification/authentification.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Agence} from "../../../models/Agence";
import {AccountService} from "../account.service";
import {ActivatedRoute, Route} from "@angular/router";
import {AlertsService} from "../../alerts/alerts.service";
import {FiltersService} from "../../filter/filters.service";
import {SessionService} from "../../session.service";
import {User} from "../../../models/User";
import {FormsService} from "../../forms/forms.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit{

  constructor(private authService: AuthentificationService,
              private formBuilder: FormBuilder,
              private formService: FormsService,
              private alertService: AlertsService,
              private route: ActivatedRoute,
              private accountService: AccountService,
              private sessionService: SessionService,
              protected filterService: FiltersService) {
  }

  updateForm!: FormGroup;
  confirmation: boolean = false;
  agence!: Agence;
  passwordConfirmation!: string;
  user!: User;
  resetPasswordForm = this.formService.resetPasswordForm;



  ngOnInit() {
   //this.authService.isAuthenticated();
    if(this.filterService.roleFilter('ROLE_CONTENT_MANAGER')){
      this.agence = this.route.snapshot.data['account'];
      this.agence.user = this.route.snapshot.data['account'].admin;
    }else{
      this.user = this.route.snapshot.data['account'];
      console.log(this.user)
    }


    this.resetPasswordForm.patchValue({
      email: this.user.email
    })

   this.updateForm = this.formBuilder.group({
     name: [this.agence.name, Validators.required],
     email: [this.agence.email, [Validators.required, Validators.email]],
     phone: [this.agence.phone, Validators.required],
     location: [this.agence.location, Validators.required],
     firstname: [this.user.firstname],
     lastname: [this.user.lastname]
   })

  }

  resetForm() {
    this.updateForm.reset({
      name: this.agence.name,
      email: this.agence.email,
      location: this.agence.location,
      phone: this.agence.phone,
    });
    console.log(this.updateForm.get('email')?.value)
  }

  deleteAccount(){
    this.accountService.deleteAccount().subscribe(
      messages => {
        console.log(JSON.stringify(messages));
      },
      error => this.alertService.deleteAlert()
    );
  }

  updateData(){
    this.accountService.updateAgence(this.updateForm.value,this.agence.id).subscribe(
      messages => {
        this.alertService.updateAlert(messages.body)
      },
    );
  }


}
