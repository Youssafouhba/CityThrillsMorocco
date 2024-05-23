import {Component, OnInit} from '@angular/core';
import { Router} from "@angular/router";
import {CartService} from "../../../../service/cart/cart.service";


enum City {
  Agadir = 'Agadir',
  AlHoceima = 'Al Hoceima',
  Asilah = 'Asilah',
  Casablanca = 'Casablanca',
  Chefchaouen = 'Chefchaouen',
  ElJadida = 'El Jadida',
  Errachidia = 'Errachidia',
  Essaouira = 'Essaouira',
  Fes = 'Fes',
  Ifrane = 'Ifrane',
  Marrakech = 'Marrakech',
  Meknes = 'Meknes',
  Nador = 'Nador',
  Ouarzazate = 'Ouarzazate',
  Oujda = 'Oujda',
  Rabat = 'Rabat',
  Safi = 'Safi',
  Tanger = 'Tanger',
  Taroudant = 'Taroudant',
  Tetouan = 'Tetouan',
  Zagora = 'Zagora'
}


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{

  constructor(private router : Router,private cartService :CartService ) {
  }

 elementCount :number = 0;
  isHomepage : Boolean =false;

  ngOnInit(){
    this.isHomepage=this.router.url==='/';
    this.cartService.elementCountVariable$.subscribe(count => {
      this.elementCount = count;
    });
  }
  public isLightTheme = true;

  onThemeSwitchChange() {
    this.isLightTheme = !this.isLightTheme;

    document.body.setAttribute(
        'data-theme',
        this.isLightTheme ? 'light' : 'dark'
    );
  }
  cities = Object.values(City);
  searchText: string = "";
  filteredCities: string[] = [];

  filterCities() {
    if (this.searchText) {
      this.filteredCities = this.cities.filter((city: City) =>
          city.toLowerCase().includes(this.searchText.toLowerCase())
      );
    } else {
      this.filteredCities = [];
    }
  }


  selectCity(city: string) {
    this.searchText = city;
    this.filteredCities = [];
  }


}
