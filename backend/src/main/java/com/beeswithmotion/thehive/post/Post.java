package com.beeswithmotion.thehive.post;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long threadId;
    private String postContent;
    private String postAuthor;
    private Timestamp postDate;

    public Post() {}

    public Long getPostId() {
        return postId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public Timestamp getPostDate() {
        return postDate;
    }
}
