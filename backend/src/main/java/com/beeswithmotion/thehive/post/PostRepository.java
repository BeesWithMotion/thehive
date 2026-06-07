package com.beeswithmotion.thehive.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);

    List<Post> findByThreadId(Long threadId);

    List<Post> findByPostAuthor(String postAuthor);
}
