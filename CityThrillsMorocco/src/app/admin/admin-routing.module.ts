import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ActivityCreateComponent} from "./activity/activity-create/activity-create.component";
import {ActivityListComponent} from "./activity/activity-list/activity-list.component";
import {DashbordComponent} from "./dashbord/dashbord.component";
import {CommentListComponent} from "./comment/comment-list/comment-list.component";
import {AccountComponent} from "./account/account/account.component";
import {LoginComponent} from "./authentification/login/login.component";

const routes: Routes = [
  {
    path: '',redirectTo: '/admin' , pathMatch: "full"
  }
  ,
  {
    path: 'admin',
    component: LoginComponent
  },
  {
    path: 'account',
    component: AccountComponent
  },
  {
    path: 'dashboard',
    component: DashbordComponent
  },
  {
    path: "comments",
    component: CommentListComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
