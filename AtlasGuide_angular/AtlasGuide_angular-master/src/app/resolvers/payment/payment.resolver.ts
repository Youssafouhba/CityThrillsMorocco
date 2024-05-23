import {Resolve} from '@angular/router';
import {PaymentService} from "../../../service/payment/payment.service";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class PaymentResolver implements Resolve<boolean>{
  constructor(private paymentService: PaymentService) {
  }
  resolve():Observable<boolean>{
    console.log(this.paymentService.paymentMessage$)
    return this.paymentService.paymentMessage$;
  }
}
