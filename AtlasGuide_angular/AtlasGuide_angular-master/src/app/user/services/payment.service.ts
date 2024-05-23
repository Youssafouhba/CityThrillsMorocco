import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiUrl = 'http://localhost:8081/api/payment/charge';
  private paymentMessageSubject = new Subject<string>();
  paymentMessage$: Observable<string> = this.paymentMessageSubject.asObservable();


  constructor(private http: HttpClient) {}


  chargeCard(paymentRequest: any): Observable<string> {
    return this.http.post<string>(this.apiUrl, paymentRequest, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      responseType: 'text' as 'json' // Définir le type de réponse comme texte
    });
  }


  updatePaymentStatus(message: string) {
    this.paymentMessageSubject.next(message);
  }
}
