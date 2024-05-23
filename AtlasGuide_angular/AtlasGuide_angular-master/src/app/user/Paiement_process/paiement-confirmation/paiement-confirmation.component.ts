import {Component, OnDestroy, OnInit, ChangeDetectorRef} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PaymentDetails} from "../../../../model/PaymentDetails.model";
import {PaymentService} from "../../../../service/payment/payment.service";

@Component({
  selector: 'app-paiement-confirmation',
  templateUrl: './paiement-confirmation.component.html',
  styleUrl: './paiement-confirmation.component.css',
})
export class PaiementConfirmationComponent implements OnInit, OnDestroy {

  paymentStatus!: boolean;
  paymentInformation !: PaymentDetails;


  constructor(private paymentService: PaymentService ,private changeDetectorRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.data.subscribe(data => {
      this.paymentStatus = data['state']; // state est le nom que vous avez donné dans la définition de la route
      console.log(this.paymentStatus);
      this.getPaymentInformation(2);

    });
  }
  goBack(): void {
    window.history.back();
  }

  getPaymentInformation(userId :number):void{
    this.paymentService.getPaymentInformation(userId)
      .subscribe(paymentDetails=> {
        this.paymentInformation = paymentDetails;
        console.log(paymentDetails);
      });
  }


  ngOnDestroy() {
  }
}
