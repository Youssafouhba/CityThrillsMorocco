package com.CityThrillsMorocco.comment.Service;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Repository.CommentRepository;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final ActivityService activityService;

    public Comment addComment(Comment comment,Long activityId,Long UserId) throws NoSuchAlgorithmException {
        Activity activity = activityService.getActivityById(activityId);
        UserDto userDto = userService.getUserById(UserId);
        comment.setUser(mapper.map(userDto, User.class));
        comment.setActivity(activity);
        return commentRepository.save(comment);
    }
}
