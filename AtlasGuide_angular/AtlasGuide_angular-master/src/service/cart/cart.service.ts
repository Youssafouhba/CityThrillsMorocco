import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, catchError, Observable} from 'rxjs';
import {Cart} from "../../model/Cart.model";


@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8083/CityThrillsMorocco/cart';

  private elementsCountSubject = new BehaviorSubject<number>(0);
  elementCountVariable$ = this.elementsCountSubject.asObservable();
  setelementCountVariable(value: number) {
    this.elementsCountSubject.next(value);
  }
  incrementElementCount() {
    const currentValue = this.elementsCountSubject.value;
    this.elementsCountSubject.next(currentValue + 1);
  }
  decrementElementCount() {
    const currentValue = this.elementsCountSubject.value;
    if (currentValue > 0) {
      this.elementsCountSubject.next(currentValue - 1);
    }
  }

  constructor(private http: HttpClient) {
    this.getCart().subscribe(cart => {
      // Mettre à jour le compteur avec le nombre d'éléments du panier
      this.setelementCountVariable(cart.cartElements.length);
    });
  }

  getCart(): Observable<Cart> {
    return this.http.get<Cart>(`${this.baseUrl}/user/2`);
  }

  addToCart(activity_id: number, user_id: number, nbr: number) {
    return this.http.post<void>(`${this.baseUrl}/${activity_id}/addTo/${user_id}/${nbr}`, {});
  }

  deleteFromCart(cartElementId: number,cartId:number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${cartElementId}/${cartId}`)
  }


}
