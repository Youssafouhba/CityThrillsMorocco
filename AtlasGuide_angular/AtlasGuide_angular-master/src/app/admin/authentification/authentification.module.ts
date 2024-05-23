import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthentificationRoutingModule} from "./authentification-routing.module";
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {MatButtonModule} from "@angular/material/button";

@NgModule({
    declarations: [
        LoginComponent,
        RegisterComponent,
        ResetPasswordComponent,
    ],
    exports: [
        RegisterComponent,
        LoginComponent
    ],
  imports: [
    CommonModule,
    FormsModule,
    AuthentificationRoutingModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
})
export class AuthentificationModule { }
