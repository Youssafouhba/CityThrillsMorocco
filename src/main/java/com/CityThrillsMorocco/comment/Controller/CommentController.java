package com.CityThrillsMorocco.comment.Controller;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Service.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentController {
  
    private CommentService commentService;
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
    
    @GetMapping("/comments_with_High_Rating")
    public List<Activity> findActivitiesWithHighRatings(){
        return commentService.findTop6Activities();
    }

    @GetMapping("/note/{activity_id}")
    public Long getNote(@PathVariable("activity_id") Long activity_id){
        return commentService.getNote(activity_id);
    }

    @GetMapping("/numberOfComments/{id}")
    public Long getNumberOfComments(@PathVariable("id") Long activity_id){
        return commentService.getNumberOfComments(activity_id);
    }
}
