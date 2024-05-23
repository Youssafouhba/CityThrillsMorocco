import {Component, OnInit} from '@angular/core';
import {FiltersService} from "./admin/filter/filters.service";
import {ActivatedRoute, Router} from "@angular/router";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit{

  constructor(
    protected filterService: FiltersService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }


  isHomePage(): boolean {
    return this.router.url === '/'
      || this.router.url === '/Admin_login'
      || this.router.url === '/Admin_sign_up'
      || this.router.url === '/Cart'
      || this.router.url === '/paiement'
      || this.router.url === '/confirmation'
      || this.router.url === '/wishlist'
      || this.router.url === '/UserAccount'
      || this.router.url === '/Booking'
      || this.router.url === '/Details'
      || this.router.url === '/resetPassword';
  }

  ngOnInit() {}

}
