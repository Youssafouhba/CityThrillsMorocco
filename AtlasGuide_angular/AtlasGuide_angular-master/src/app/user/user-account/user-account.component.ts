import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../admin/account/account.service";
import {User} from "../../models/User";
@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrl: "./user-account.component.css"
})
export class UserAccountComponent implements OnInit{
  user!: User;
  constructor(
    private accountService: AccountService,
  ) {}

  ngOnInit() {
    this.accountService.getUser().subscribe(user=> this.user=user);
  }
}
