import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "../authentification.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {passwordValidator} from "../passwordValidator/password-validator";
import {ActivatedRoute, Router} from "@angular/router";
import Swal, {SweetAlertOptions} from "sweetalert2";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements  OnInit{
  resetForm!: FormGroup;
  newPasswordForm!: FormGroup;

  formSubmitted = false;
  resetSuccess = false;

  queryParams = this.route.snapshot.queryParams;

  constructor(private authService: AuthentificationService,
              private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  ngOnInit() {

    this.resetForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
    });
    this.newPasswordForm = this.formBuilder.group({
      newPassword: ['', [Validators.required,passwordValidator]],
      PasswordConfirmation: ['',[Validators.required,passwordValidator]]
    });

    if (this.queryParams['token']){
      this.resetSuccess = true;
      Swal.fire(this.passcreated_swalOptions);
    }
  }

  resetPassword(){
    this.formSubmitted = true;
    if (this.newPasswordForm.valid){
      this.authService.registerNewPassword(this.queryParams['token'],this.newPasswordForm.get('newPassword')?.value).subscribe(
        messages => {
          console.log(JSON.stringify(messages));
        },
        error => {
          if (error.status===200){
            Swal.fire(this.succeed_swalOptions);
          }
        }
      );
    }
  }


  sendResetLink() {
    this.formSubmitted = true;
    setTimeout(() => {
      this.formSubmitted = false;
    },8000)
    if(this.resetForm.valid){
      this.authService.resetPasword(this.resetForm.get('email')?.value).subscribe(
        messages => {
          console.log(JSON.stringify(messages));
        },
        error => {
          if (error.status===200){
            Swal.fire(this.succeed_swalOptions);
          }
        }

      );
    }

  }

  succeed_swalOptions: SweetAlertOptions = {
    text: 'Reset your password By the link sent to your email address .',
    position: 'top',
    width: '400px',
    showConfirmButton: true,
    customClass: {
      popup: 'alert alert-info',
    }
  };

  passcreated_swalOptions: SweetAlertOptions = {
    title: 'Create New password .',
    position: 'top',
    width: '400px',
    showConfirmButton: true,
    customClass: {
      popup: 'alert alert-info',
    }
  };

}
