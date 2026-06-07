package com.beeswithmotion.thehive.post;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long threadId;
    private String postContent;
    private String postAuthor;
    private Timestamp postDate;
}
