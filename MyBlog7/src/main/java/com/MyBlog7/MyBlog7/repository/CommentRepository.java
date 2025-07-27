package com.MyBlog7.MyBlog7.repository;

import com.MyBlog7.MyBlog7.entity.Comment;

import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {
    static List<Comment> findByPostId(long postId) {
        return null;
    }

}
