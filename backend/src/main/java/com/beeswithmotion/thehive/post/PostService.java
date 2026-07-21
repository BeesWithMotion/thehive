package com.beeswithmotion.thehive.post;

import com.beeswithmotion.thehive.image.ImageService;
import com.beeswithmotion.thehive.image.dto.ImageResponse;
import com.beeswithmotion.thehive.post.dto.CreatePostRequest;
import com.beeswithmotion.thehive.post.dto.PostResponse;
import com.beeswithmotion.thehive.thread.Thread;
import com.beeswithmotion.thehive.thread.ThreadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ThreadRepository threadRepository;
    private final ImageService imageService;

    public PostService(PostRepository postRepository, ThreadRepository threadRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.threadRepository = threadRepository;
        this.imageService = imageService;
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

    public ResponseEntity<List<PostResponse>> getPostsByBoardAbbreviationAndThreadId(String boardAbbreviation, Long threadId) {
        Thread thread = threadRepository.findByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);

        if(thread == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Thread not found");
        }

        List<PostResponse> posts = postRepository.findByThreadId(threadId)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(posts);
    }

    public PostResponse createPost(String boardAbbreviation, Long threadId, CreatePostRequest request, MultipartFile imageFile, String author) {
        Thread thread = threadRepository.findByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);

        if(thread == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Thread not found");
        }

        //String author = request.postAuthor();
        if(author == null || author.isBlank()) {
            author = "Drone";
        }

        Post post = new Post();
        post.setThreadId(threadId);
        post.setPostContent(request.postContent());
        post.setPostAuthor(author);
        post.setPostDate(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        ImageResponse imageResponse = imageService.saveImage(savedPost.getPostId(), imageFile);
        return toResponse(savedPost, imageResponse);
    }

    private PostResponse toResponse(Post post) {
        ImageResponse imageResponse = imageService.getImageResponseByPostId(post.getPostId());

        return new PostResponse(post.getPostId(), post.getThreadId(), post.getPostContent(), post.getPostAuthor(), post.getPostDate(), imageResponse);
    }

    private PostResponse toResponse(Post post, ImageResponse image) {
        return new PostResponse(post.getPostId(), post.getThreadId(), post.getPostContent(), post.getPostAuthor(), post.getPostDate(), image);
    }
}
