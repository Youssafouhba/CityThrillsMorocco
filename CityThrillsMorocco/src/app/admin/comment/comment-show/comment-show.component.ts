import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subject, takeUntil} from "rxjs";
import {ProductViewService} from "../../products/product-view/product-view.service";

@Component({
  selector: 'app-comment-show',
  templateUrl: './comment-show.component.html',
  styleUrls: ['./comment-show.component.css']
})
export class CommentShowComponent implements OnDestroy, OnInit  {

  @Input() id = -1;
  name = '';
  private productSub = new Subject<void>();

  constructor(private productviewService: ProductViewService) { }

  ngOnDestroy(): void {
    this.productSub.next();
    this.productSub.complete();
  }

  ngOnInit(): void {
    this.getProduct();
  }

  private getProduct() {
    this.productviewService.getProduct(this.id).pipe(
      takeUntil(this.productSub)
    ).subscribe(product => {
      if (product) {
        this.name = product.name;
      }
    });
  }
}
