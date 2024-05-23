import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subject, takeUntil} from "rxjs";


@Component({
  selector: 'app-comment-show',
  templateUrl: './comment-show.component.html',
  styleUrls: ['./comment-show.component.css']
})
export class CommentShowComponent implements OnDestroy, OnInit  {

  @Input() id = -1;
  name = '';
  private productSub = new Subject<void>();

  constructor() { }

  ngOnDestroy(): void {
    this.productSub.next();
    this.productSub.complete();
  }

  ngOnInit(): void {
    this.getProduct();
  }

  private getProduct() {

  }
}
