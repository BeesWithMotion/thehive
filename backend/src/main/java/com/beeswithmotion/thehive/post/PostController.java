package com.beeswithmotion.thehive.post;

import com.beeswithmotion.thehive.post.dto.CreatePostRequest;
import com.beeswithmotion.thehive.post.dto.PostResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{boardAbbreviation}/{threadId}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable String boardAbbreviation, @PathVariable Long threadId) {
        return postService.getPostsByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);
    }

    @PostMapping("/{boardAbbreviation}/{threadId}/posts")
    public PostResponse createPost(@PathVariable String boardAbbreviation, @PathVariable Long threadId, @Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(boardAbbreviation, threadId, request);
    }
}
