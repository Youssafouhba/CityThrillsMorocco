import { Component, OnInit } from '@angular/core';
import {Cart} from "../../../../../model/Cart.model";
import {CartService} from "../../../../../service/cart/cart.service";
import {CartElement} from "../../../../../model/CartElement.model";




@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
    cart: Cart = new Cart();


    constructor(private cartService: CartService) {
    }

    ngOnInit(): void {
        this.getCartDetails();

    }

    getCartDetails(): void {
        this.cartService.getCart()
            .subscribe(cart => {
                this.cart = cart;
            });
    }
    deleteFromCart(element: CartElement ,cart: Cart): void {

      this.cartService.deleteFromCart(element.id,cart.id).subscribe(
        () => {
          const index = this.cart.cartElements.indexOf(element);
          if (index !== -1) {
            this.cart.cartElements.splice(index, 1);
            this.getCartDetails();
            this.cartService.decrementElementCount();
          }
          console.log('Élément de panier supprimé avec succès');
        },
        (error) => {
          console.error('Erreur lors de la suppression de l\'élément de panier :', error);
        }
      );

    }









}
