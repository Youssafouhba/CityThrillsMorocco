import { Injectable } from '@angular/core';
import axios from 'axios'
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GoogleMapsService {
  longitude!: number;
  latitude!: number;
  unsafeUrl = 'https://www.openstreetmap.org/export/embed.html?bbox=-8.0271313,31.6205935,-8.007131300000001,31.6405935&layer=mapnik';
  safeUrl: SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.unsafeUrl);
  constructor(
    public sanitizer: DomSanitizer,) { }

  updateMap(data: string): SafeResourceUrl {
    const placeName = data;
    axios.get(`https://nominatim.openstreetmap.org/search?q=${placeName}&format=json&limit=1`)
      .then((response) => {
        const result = response.data[0];

        if (result) {
          this.latitude = parseFloat(result.lat);
          this.longitude = parseFloat(result.lon);

          this.unsafeUrl = `https://www.openstreetmap.org/export/embed.html?bbox=${this.longitude - 0.01},${this.latitude - 0.01},${this.longitude + 0.01},${this.latitude + 0.01}&layer=mapnik`;
          this.safeUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.unsafeUrl);
          console.log(this.unsafeUrl)
        } else {
          console.log('No results found');
        }
      })
      .catch((error) => {
        console.log('Error geocoding:', error);
      });
    return this.safeUrl;
  }

  map(){


  }

}
