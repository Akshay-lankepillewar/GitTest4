package com.MyBlog7.MyBlog7.repository;

import com.MyBlog7.MyBlog7.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
