import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser'
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MatMomentDateModule} from "@angular/material-moment-adapter";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {GoogleMapsModule} from "@angular/google-maps";
import {HttpClientModule} from "@angular/common/http";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import {SlickCarouselModule} from "ngx-slick-carousel";
import {AdminModule} from "./admin/admin.module";
import {UserModule} from "./user/user.module";
import {MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {PaymentResolver} from "./resolvers/payment/payment.resolver";

import {SwaggerComponent} from './swagger/swagger/swagger.component';
import {CloudinaryModule} from '@cloudinary/ng';


// @ts-ignore
@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    SwaggerComponent,
    

  ],
  imports: [
    GoogleMapsModule,
    MatMomentDateModule,
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    AdminModule,
    UserModule,
    FormsModule,
    MatSlideToggleModule,
    SlickCarouselModule,
    MatDatepickerModule,
    MatNativeDateModule,
    CloudinaryModule
  ],
  providers: [
    provideAnimationsAsync(),
    PaymentResolver,
    DatePipe,

  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
