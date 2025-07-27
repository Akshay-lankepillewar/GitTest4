package com.MyBlog7.MyBlog7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comment")
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@Column(nullable = false)
  private String name;
@Column(nullable = false)
 private String email;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="Post_id",nullable = false)
private Post post;

}
