import {Component, OnInit} from '@angular/core';
import {CommentService} from "../comment.service";
import {Comment} from "../../../models/Comment";
import {AuthentificationService} from "../../authentification/authentification.service";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit{

  comments!: Comment[];

  constructor(private commentService: CommentService,
              private AauthService: AuthentificationService) {}

  ngOnInit(): void {
    this.getComments();
    this.AauthService.isAuthenticated()
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
