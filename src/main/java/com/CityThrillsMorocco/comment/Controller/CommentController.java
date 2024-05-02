package com.CityThrillsMorocco.comment.Controller;

import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/CityThrillsMorocco/Comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) throws NoSuchAlgorithmException {
        return commentService.addComment(comment,12L,9L);
    }

    @DeleteMapping("/{id}")
    public void removeComment(@PathVariable("id") Long id ) throws NoSuchAlgorithmException{
        commentService.deleteComment(id);
    }

}
