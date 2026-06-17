package com.beeswithmotion.thehive.post;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long threadId;
    private String postContent;
    private String postAuthor;
    private LocalDateTime postDate;

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

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}
