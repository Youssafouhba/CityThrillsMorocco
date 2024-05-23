import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PaymentService} from "../../../../service/payment/payment.service";
import {PaymentDetails} from "../../../../model/PaymentDetails.model";


@Component({
  selector: 'app-paiement-form',
  templateUrl: './paiement-form.component.html',
  styleUrl: './paiement-form.component.css'
})
export class PaiementFormComponent {

  constructor(private http: HttpClient, private paymentService:PaymentService) { }
  paymentStatus!: boolean;

  firstName: string = '';
  lastName: string = '';
  cardNumber: string = '';
  expirationMonth: string = '';
  email: string = '';
  ccv: string = '';
  confirmEmail: string = '';
  expirationYear: string = '';
  phoneNumber: string = '';
  firstNameError: string = '';
  lastNameError: string = '';
  emailError: string = '';
  confirmEmailError: string = '';
  phoneNumberError: string = '';
  cardNumberError: string = '';
  expirationMonthError: string = '';
  expirationYearError: string = '';
  ccvError: string = '';



  validateFirstName():boolean {
    // Validation du prénom (lettres en anglais uniquement et non vide)
    if (!this.firstName.trim()) {
      this.firstNameError = 'The first name field is required.';
      return false;
    } else if (!/^[a-zA-Z]+$/.test(this.firstName)) {
      this.firstNameError = 'The first name should only contain letters.';
      return false;
    } else {
      this.firstNameError = '';
      return true;
    }
  }
  validateCCV(): boolean {
    // Validation du CCV (doit contenir exactement 3 chiffres)
    if (!/^\d{3}$/.test(this.ccv)) {
      this.ccvError = 'The CCV should contain exactly 3 digits.';
      return false;
    } else {
      this.ccvError = '';
      return true;
    }
  }

  validateExpirationYear() :boolean{
    // Validation de l'année d'expiration (doit contenir 2 ou 4 chiffres)
    if (!/^\d{2}|\d{4}$/.test(this.expirationYear)) {
      this.expirationYearError = 'The expiration year should contain either 2 or 4 digits.';
      return false;
    } else {
      this.expirationYearError = '';
      return true;
    }
  }

  validateExpirationMonth():boolean {
    // Validation de mois de l'expiration (doit contenir exactement 2 chiffres)
    if (!/^\d{2}$/.test(this.expirationMonth)) {
      this.expirationMonthError = 'The expiration month should contain exactly 2 digits.';
      return false;
    } else {
      this.expirationMonthError = '';
      return true;
    }
  }

  validatePhoneNumber(): boolean {
    // Validation du numéro de téléphone (doit contenir uniquement des chiffres)
    if (!/^\d+$/.test(this.phoneNumber)) {
      this.phoneNumberError = 'The phone number should only contain numbers.';
      return false;
    } else {
      this.phoneNumberError = '';
      return true;
    }
  }

  validateCardNumber(): boolean {
    // Validation du numéro de carte (doit contenir exactement 16 chiffres)
    if (!/^\d{16}$/.test(this.cardNumber)) {
      this.cardNumberError = 'The card number should contain exactly 16 digits.';
      return false;
    } else {
      this.cardNumberError = '';
      return true;
    }
  }

  validateLastName(): boolean {
    if (!this.lastName.trim()) {
      this.lastNameError = 'The last name field is required.';
      return false;
    } else if (!/^[a-zA-Z]+$/.test(this.lastName)) {
      this.lastNameError = 'The last name should only contain letters.';
      return false;
    } else {
      this.lastNameError = '';
      return true;
    }
  }
  validateEmail(): boolean {
    // Validation de l'email (contient '@')
    if (!/@/.test(this.email)) {
      this.emailError = 'The email address is not a valid';
      return false;
    } else {
      this.emailError = '';
      return true;
    }
  }

  validateConfirmEmail(): boolean {
    // Validation de la confirmation de l'email
    if (this.confirmEmail !== this.email) {
      this.confirmEmailError = 'Please Confirm your email';
      return false;
    } else {
      this.confirmEmailError = '';
      return true;
    }
  }
  areAllFieldsValid(): boolean {
    return this.validateCCV() &&  this.validateExpirationYear() && this.validateCardNumber() && this.validateExpirationMonth() && this.validateFirstName() && this.validateLastName() && this.validateEmail() && this.validateConfirmEmail() && this.validatePhoneNumber();
  }

  chargeCreditCard() {
    (<any>window).Stripe.card.createToken({
      number: this.cardNumber,
      exp_month: this.expirationMonth,
      exp_year: this.expirationYear,
      cvc: this.ccv
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token,this.firstName,this.lastName,this.email,this.phoneNumber);
      } else {
        console.log(response.error.message);
      }
    });
  }
  chargeCard(token: string,firstName: string,lastName: string,email: string,phone: string) {
    console.log(token);
    const bodyData = {
      "token": token,
      "firstName": firstName,
      "lastName": lastName,
      "email": email,
      "phone": phone
    }
    this.paymentService.chargeCard(bodyData,2).subscribe(
      response => {
        this.paymentStatus=response;
        console.log(this.paymentStatus);
        this.paymentService.updatePaymentStatus(this.paymentStatus);
        },
      error => {
        console.error('Erreur lors du traitement du paiement :', error);
        this.paymentService.updatePaymentStatus(this.paymentStatus);
      }
    );
  }
}
