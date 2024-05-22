package com.CityThrillsMorocco.comment.Service;

import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.comment.Dto.CommentDto;
import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Repository.CommentRepository;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final ActivityService activityService;
    private final ActivityRepo activityRepository;
    private final UserRepository userRepository;

//    public Comment addComment(Comment comment,Long activityId,Long UserId) throws NoSuchAlgorithmException {
//        Activity activity = activityService.getActivityById(activityId);
//        UserDto userDto = userService.getUserById(UserId);
//        comment.setUser(mapper.map(userDto, User.class));
//        comment.setActivity(activity);
//        comment.setCreatedDate(new Date());
//        return commentRepository.save(comment);
//    }

    public CommentDto addCommentToActivity(Long activityId, Long userId, CommentDto commentDto) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setNote(commentDto.getNote());
        comment.setContent(commentDto.getContent());
        comment.setCreatedDate(new Date());
        comment.setActivity(activity);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        return new CommentDto(savedComment.getId(), savedComment.getNote(), savedComment.getContent(),
                savedComment.getCreatedDate(), activity.getId(), user.getId());
    }
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
    
    public List<ActivityDto> findTop6Activities(){
        List<Activity> topActivities = commentRepository.findActivitiesWithHighRatings();
        if(topActivities.size() > 6){
            topActivities = topActivities.subList(0,6);
        }

        return topActivities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ActivityDto> findTop6Activities(String category){
        List<Activity> topActivities = commentRepository.findActivitiesWithHighRatings(category);
        if(topActivities.size() > 6){
            topActivities = topActivities.subList(0,6);
        }

        return topActivities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Long getNote(Long activity_id){
        return commentRepository.findAverageNoteByActivityId(activity_id);
    }

    public Long getNumberOfComments(Long activity_id){
        return commentRepository.getNumberOfComments(activity_id);
    }

    public Long getNumberOfGoodComments(){
        return commentRepository.getNumberOfGoodComments();
    }

    public List<CommentDto> getCommenByActivity(Long activity_id){
       List<Comment> comments= commentRepository.findCommentsByActivityId(activity_id);
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = mapper.map(activity, ActivityDto.class);
        activityDto.setAgence_id(activity.getAgence().getId());
        return activityDto;
    }

    public CommentDto convertToDto(Comment comment) {
        CommentDto commentDto =mapper.map(comment, CommentDto.class);
        commentDto.setId_activity(comment.getActivity().getId());
        commentDto.setUser_id(comment.getUser().getId());
        return commentDto;
    }
}
