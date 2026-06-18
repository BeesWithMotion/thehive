package com.beeswithmotion.thehive.post;

import com.beeswithmotion.thehive.post.dto.CreatePostRequest;
import com.beeswithmotion.thehive.post.dto.PostResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardAbbreviation}/threads/{threadId}/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(@PathVariable String boardAbbreviation, @PathVariable Long threadId) {
        return postService.getPostsByBoardAbbreviationAndThreadId(boardAbbreviation, threadId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostResponse createPost(@PathVariable String boardAbbreviation, @PathVariable Long threadId, @Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(boardAbbreviation, threadId, request, null);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostResponse createPostMultipart(
            @PathVariable String boardAbbreviation,
            @PathVariable Long threadId,
            @Valid @RequestPart("post") CreatePostRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return postService.createPost(boardAbbreviation, threadId, request, image);
    }
}
