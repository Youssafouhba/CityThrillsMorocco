import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environnment} from "../../environnment/environnment";
import {Observable} from "rxjs";
import {Comment} from "../../models/Comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private commentsUrl = environnment.apiURL+'/Comments';

  constructor(private http: HttpClient,) { }

  getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.commentsUrl);
  }

  removeComment(commentId: number): Observable<void>{
    return this.http.delete<void>(this.commentsUrl+'/'+commentId);
  }

}
