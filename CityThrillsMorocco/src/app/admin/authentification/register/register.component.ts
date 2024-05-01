import {Component} from '@angular/core';
import {AuthentificationService} from "../authentification.service";
import {Agence} from "../../../models/Agence";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  agence!: Agence;

  constructor(private authService: AuthentificationService) {
  }

  register(name: string,email: string, location: string,phone: string,password: string) {
    this.authService.register({name, email, location, phone,password}).subscribe(
      messages => {
         console.log(JSON.stringify(messages)); // Affectation des messages au attribut
      }
    );
  }
}
