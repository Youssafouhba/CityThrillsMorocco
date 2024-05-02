import {Component, Injectable, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthentificationService} from "../authentification.service";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {SessionService} from "../../session.service";

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage = '';
  private token = '';
  addForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthentificationService,
              private dialog: MatDialog,
              private router: Router,
              private sessionService: SessionService) {
  }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    },);
    this.sessionService.clearSessionData('user');
  }

  login(email: string, password: string) {
    this.authService.login({email, password}).subscribe(
      response => {
        this.token = response.token;
        this.sessionService.setSessionData('user', {token: this.token});
        this.router.navigate(['/dashboard']);
      },
      error => {
        if (error.status !== 200) this.errorMessage = "Incorrect username or password"
      }
    );
  }

}
