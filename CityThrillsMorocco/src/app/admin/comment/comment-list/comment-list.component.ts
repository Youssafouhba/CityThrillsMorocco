import {Component, OnInit} from '@angular/core';
import {CommentService} from "../comment.service";
import {Comment} from "../../../models/Comment";
import {SessionService} from "../../session.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit{

  comments!: Comment[];

  constructor(private commentService: CommentService,
              private sessionService: SessionService,
              private router: Router) {}

  ngOnInit(): void {
    this.getComments();
    const user = this.sessionService.getSessionData('user');
    if (!user){
      this.router.navigate(["/admin"])
    }
  }

  private getComments() {
    this.commentService.getComments().subscribe( comments => {
        this.comments = comments;
      }
    );
  }

  removeCommen(commentId: number){
    this.commentService.removeComment(commentId).subscribe(comments => {
        console.log(commentId)
        this.getComments()
    }
    )

  }

}
