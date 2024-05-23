import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DetailsHeaderComponent} from "./activity-details/details-header/details-header.component";
import {BodyDetailsComponent} from "./activity-details/body-details/body-details.component";
import {ActivityDetailsComponent} from "./activity-details/activity-details.component";
import {TestimonialComponent} from "./home/testimonial/testimonial.component";
import {CallbackComponent} from "./home/callback/callback.component";
import {CitiesComponent} from "./home/cities/cities.component";
import {WishlistComponent} from "./Paiement_process/cart_wishlist/wishlist/wishlist.component";
import {PaiementConfirmationComponent} from "./Paiement_process/paiement-confirmation/paiement-confirmation.component";
import {PaiementFormComponent} from "./Paiement_process/paiement-form/paiement-form.component";
import {CartComponent} from "./Paiement_process/cart_wishlist/cart/cart.component";
import {SignUpComponent} from "./supplier/sign-up/sign-up.component";
import {LoginComponent} from "./supplier/login/login.component";
import {FamousPlacesComponent} from "./home/famous-places/famous-places.component";
import {FooterComponent} from "./shared/footer/footer.component";
import {HeaderComponent} from "./shared/header/header.component";
import {TopActivitiesComponent} from "./home/top-activities/top-activities.component";
import {SliderComponent} from "./home/slider/slider.component";
import {HomeComponent} from "./home/home.component";
import {UserRoutingModule} from "./user-routing.module";
import {provideAnimationsAsync} from "@angular/platform-browser/animations/async";
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {SlickCarouselModule} from "ngx-slick-carousel";
import {ActivitesByCityComponent} from "./activites-by-city/activites-by-city.component";
import { UserSidebarComponent } from './user-sidebar/user-sidebar.component';
import { UserAccountComponent } from './user-account/user-account.component';
import { BookingComponent } from './user-account/booking/booking.component';




@NgModule({
    declarations: [
      HomeComponent,
      SliderComponent,
      TopActivitiesComponent,
      HeaderComponent,
      FooterComponent,
      FamousPlacesComponent,
      LoginComponent,
      SignUpComponent,
      CartComponent,
      PaiementFormComponent,
      PaiementConfirmationComponent,
      WishlistComponent,
      CitiesComponent,
      CallbackComponent,
      TestimonialComponent,
      ActivityDetailsComponent,
      BodyDetailsComponent,
      DetailsHeaderComponent,
      ActivitesByCityComponent,
      UserSidebarComponent,
      UserAccountComponent,
      BookingComponent,
    ],
    imports: [
        CommonModule,
        UserRoutingModule,
        BrowserModule,
        HttpClientModule,
        FormsModule,
        MatSlideToggleModule,
        SlickCarouselModule,
        ReactiveFormsModule
    ],
    providers: [
        provideAnimationsAsync()
    ],
    exports: [
        FooterComponent,
        UserSidebarComponent
    ]
})
export class UserModule { }
