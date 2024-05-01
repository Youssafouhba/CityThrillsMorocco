import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-comment-detail',
  templateUrl: './comment-detail.component.html',
  styleUrls: ['./comment-detail.component.css']
})
export class CommentDetailComponent {
  @Input('commentContent') commentContent!: string;
  @Input('commentUser') commentUser!: string;
  @Input('commentActivity') commentActivity!: string;
  @Input('commentDte') commentDate!: Date;
  @Input('commentId') commentId!: number;

  @Output() deletedComment = new EventEmitter();

  removeComment() {
    this.deletedComment.emit()
  }
}
