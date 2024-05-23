import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ActivityDetailsComponent} from "./activity-details/activity-details.component";
import {WishlistComponent} from "./Paiement_process/cart_wishlist/wishlist/wishlist.component";
import {PaiementConfirmationComponent} from "./Paiement_process/paiement-confirmation/paiement-confirmation.component";
import {PaiementFormComponent} from "./Paiement_process/paiement-form/paiement-form.component";
import {CartComponent} from "./Paiement_process/cart_wishlist/cart/cart.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "../admin/authentification/login/login.component";
import {RegisterComponent} from "../admin/authentification/register/register.component";


const routes: Routes = [
  {path:'' , component : HomeComponent},
  {path:'Admin_login' , component : LoginComponent},
  {path:'Admin_sign_up' , component : RegisterComponent},
  {path:'Cart' , component : CartComponent},
  {path:'paiement' , component : PaiementFormComponent},
  {path:'confirmation' , component : PaiementConfirmationComponent},
  {path:'wishlist' , component : WishlistComponent},
  {path:'Details/:id' , component : ActivityDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
