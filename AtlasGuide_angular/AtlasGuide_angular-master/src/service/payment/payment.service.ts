import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {PaymentDetails} from "../../model/PaymentDetails.model";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiUrl = 'http://localhost:8083/CityThrillsMorocco/api/payment';

  private paymentMessageSubject = new Subject<boolean>();
  paymentMessage$: Observable<boolean> = this.paymentMessageSubject.asObservable();


  constructor(private http: HttpClient) {}


  chargeCard(paymentRequest: any,userId: number): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/charge/${userId}`, paymentRequest, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      responseType: 'text' as 'json' // Définir le type de réponse comme texte
    });
  }

  getPaymentInformation(userId: number):Observable<PaymentDetails>{
    return this.http.get<PaymentDetails>(`${this.apiUrl}/${userId}`);
  }

  updatePaymentStatus(message: boolean) {
    this.paymentMessageSubject.next(message);
    console.log(message)
  }



}
