import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CommentListComponent} from './comment-list/comment-list.component';
import {CommentShowComponent} from './comment-show/comment-show.component';
import {CommentDetailComponent} from './comment-detail/comment-detail.component';
import {CommentComponent} from './comment/comment.component';
import {DragDropModule} from "@angular/cdk/drag-drop";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatListModule} from "@angular/material/list";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";


@NgModule({
  declarations: [
    CommentListComponent,
    CommentShowComponent,
    CommentDetailComponent,
    CommentComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [
        CommonModule,
        DragDropModule,
        MatGridListModule,
        MatListModule,
        MatPaginatorModule,
        MatSortModule,
        MatTableModule,

    ],
  exports: [
    CommentListComponent,
  ]
})
export class CommentModule { }
