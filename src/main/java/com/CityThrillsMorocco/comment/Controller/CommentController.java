package com.CityThrillsMorocco.comment.Controller;

import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.comment.Dto.CommentDto;
import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Service.CommentService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping("/{activity_id}/{userId}")
//    public Comment addComment(@RequestBody Comment comment, @PathVariable("activity_id") Long activityId, @PathVariable("userId") Long userId) throws NoSuchAlgorithmException {
//        return commentService.addComment(comment, activityId, userId);
//    }


    @PostMapping("/add/{activityId}/{userId}")
    public ResponseEntity<CommentDto> addCommentToActivity(@PathVariable("activityId") Long activityId,
                                                           @PathVariable ("userId")Long userId,
                                                           @RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.addCommentToActivity(activityId, userId, commentDto);
        return ResponseEntity.ok(createdComment);
    }

    @DeleteMapping("/{id}")
    public void removeComment(@PathVariable("id") Long id ) throws NoSuchAlgorithmException{
        commentService.deleteComment(id);
    }
    
    @GetMapping("/comments_with_High_Rating")
    public List<ActivityDto> findActivitiesWithHighRatings(){
        return commentService.findTop6Activities();
    }

    @GetMapping("/comments_with_High_Rating/{category}")
    public List<ActivityDto> findActivitiesWithHighRatings(@PathVariable("category") String category){
        return commentService.findTop6Activities(category);
    }



    @GetMapping("/note/{activity_id}")
    public Long getNote(@PathVariable("activity_id") Long activity_id){
        return commentService.getNote(activity_id);
    }

    @GetMapping("/numberOfComments/{id}")
    public Long getNumberOfComments(@PathVariable("id") Long activity_id){
        return commentService.getNumberOfComments(activity_id);
    }

    @GetMapping("/numberOfGoodComments")
    public Long getNumberOfGoodComments(){
        return commentService.getNumberOfGoodComments();
    }


    @GetMapping("/comments/{activityId}")
    public List<CommentDto> getCommentsByActivity(@PathVariable("activityId") Long activityId){
        return commentService.getCommenByActivity(activityId);
    }



}
