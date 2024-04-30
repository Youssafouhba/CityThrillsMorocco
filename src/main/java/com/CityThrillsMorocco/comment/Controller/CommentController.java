package com.CityThrillsMorocco.comment.Controller;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.comment.Repository.CommentRepository;
import com.CityThrillsMorocco.comment.Service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

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
