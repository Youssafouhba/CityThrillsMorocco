import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashbordComponent} from "./dashbord/dashbord.component";
import {CommentListComponent} from "./comment/comment-list/comment-list.component";
import {AccountComponent} from "./account/account/account.component";
import {LoginComponent} from "./authentification/login/login.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {AccountResolver} from "./account/Resolver/account.resolver";
import {ProgramComponent} from "./activity/program/program.component";

const routes: Routes = [

  {
    path: 'admin',
    component: LoginComponent
  },
  {
    path: 'account',
    component: AccountComponent,
    resolve: {
      account: AccountResolver
    }
  },
  {
    path: 'dashboard',
    component: DashbordComponent
  },
  {
    path: "comments",
    component: CommentListComponent
  },
  {
    path: 'prog',component: ProgramComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
