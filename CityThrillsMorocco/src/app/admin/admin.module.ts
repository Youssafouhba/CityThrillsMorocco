import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashbordComponent} from "./dashbord/dashbord.component";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommentModule} from "./comment/comment.module";
import {AccountModule} from "./account/account.module";
import {ActivityModule} from "./activity/activity.module";
import {AdminRoutingModule} from "./admin-routing.module";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {AuthentificationModule} from "./authentification/authentification.module";

@NgModule({
  declarations: [
    DashbordComponent,
    SidebarComponent,

  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AuthentificationModule,

    ActivityModule,
    AccountModule,
    CommentModule,
    AdminRoutingModule,
    FormsModule,


  ],
    exports: [
        SidebarComponent,
    ]
})
export class AdminModule { }
