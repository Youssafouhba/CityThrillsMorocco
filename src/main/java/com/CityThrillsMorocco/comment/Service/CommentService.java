package com.CityThrillsMorocco.comment.Service;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.comment.Model.Comment;
import com.CityThrillsMorocco.comment.Repository.CommentRepository;
import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final ModelMapper mapper;
    private final ActivityService activityService;

    public Comment addComment(Comment comment,Long activityId,String token){
        Activity activity = activityService.getActivityById(activityId);
        UserDto userDto = userService.getUserById(getUser(token).getId());
        comment.setUser(mapper.map(userDto, User.class));
        comment.setActivity(activity);
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsByActivityId(Long activityId) {
        return commentRepository.getCommentsByActivity_Id(activityId);
    }

    public Comment createReply(Comment reply,Long parentCommentId,String token) {
        reply.setCreatedDate(LocalDateTime.now());
        reply.setUser(getUser(token));
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));
        reply.setParentComment(parentComment);
        reply.setActivity(parentComment.getActivity());
        parentComment.getReplies().add(reply);
        return commentRepository.save(reply);
    }

    public List<Comment> getRepliesByCommentId(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        return comment.getReplies();
    }

    public List<Comment> findByParentCommentId(Long parentCommentId){
        return commentRepository.findByParentCommentId(parentCommentId);
    }

    public User getUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userEmail = jwtUtil.extractUsername(token);
        return userService.searchByEmail(userEmail);
    }

}
