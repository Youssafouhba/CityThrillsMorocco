package com.CityThrillsMorocco.comment.Repository;

import com.CityThrillsMorocco.comment.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
