package com.beeswithmotion.thehive.post;

import com.beeswithmotion.thehive.post.dto.CreatePostRequest;
import com.beeswithmotion.thehive.post.dto.PostResponse;
import com.beeswithmotion.thehive.thread.Thread;
import com.beeswithmotion.thehive.thread.ThreadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ThreadRepository threadRepository;

    public PostService(PostRepository postRepository, ThreadRepository threadRepository) {
        this.postRepository = postRepository;
        this.threadRepository = threadRepository;
    }

    public ResponseEntity<Post> getPostById(Long postId) {
        Post post = postRepository.findByPostId(postId);
        return ResponseEntity.ok(post);
    }

    // Probably don't use
    public ResponseEntity<List<Post>> getPostsByThreadId(Long threadId) {
        List<Post> posts = postRepository.findByThreadId(threadId);
        return ResponseEntity.ok(posts);
    }

    public ResponseEntity<List<Post>> getPostsByBoardAbbreviationAndThreadId(String boardAbbreviation, Long threadId) {
        Thread thread = threadRepository.findByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);

        if(thread == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Thread not found");
        }

        List<Post> posts = postRepository.findByThreadId(threadId);
        return ResponseEntity.ok(posts);
    }

    public PostResponse createPost(String boardAbbreviation, Long threadId, CreatePostRequest request) {
        Thread thread = threadRepository.findByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);

        if(thread == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Thread not found");
        }

        String author = request.postAuthor();
        if(author == null || author.isBlank()) {
            author = "Drone";
        }
        String content = request.postContent();
        if(content == null || content.isBlank()) {
            content = "";
        }

        Post post = new Post();
        post.setThreadId(threadId);
        post.setPostContent(content);
        post.setPostAuthor(author);
        post.setPostDate(java.time.LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return toResponse(savedPost);
    }

    private PostResponse toResponse(Post post) {
        return new PostResponse(post.getPostId(), post.getThreadId(), post.getPostContent(), post.getPostAuthor(), post.getPostDate().toString());
    }
}
