import {Component, OnInit} from '@angular/core';
import {FiltersService} from "../../admin/filter/filters.service";
import {NavigatorService} from "../../navigator/navigator.service";
import {SessionService} from "../../admin/session.service";
import {AccountService} from "../../admin/account/account.service";
import {User} from "../../models/User";
@Component({
  selector: 'app-user-sidebar',
  templateUrl: './user-sidebar.component.html',
  styleUrl: "../../.././assets/css/main.css",
})
export class UserSidebarComponent implements OnInit{
  user!: User;
  constructor(
    protected accountService: AccountService,
    protected navigator: NavigatorService,
    ) {}

  ngOnInit() {
    this.accountService.getUser().subscribe(user=> this.user=user);
  }
}
