package com.beeswithmotion.thehive.post;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<Post> getPostById(Long postId) {
        Post post = postRepository.findByPostId(postId);
        return ResponseEntity.ok(post);
    }

    public ResponseEntity<List<Post>> getPostsByThreadId(Long threadId) {
        List<Post> posts = postRepository.findByThreadId(threadId);
        return ResponseEntity.ok(posts);
    }
}
