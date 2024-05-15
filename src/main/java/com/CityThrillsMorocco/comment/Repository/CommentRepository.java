package com.CityThrillsMorocco.comment.Repository;

import com.CityThrillsMorocco.comment.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getCommentsByActivity_Id(Long activityId);
    List<Comment> findByActivityId(Long activityId);
    List<Comment> findByParentCommentId(Long parentCommentId);
}
