package com.beeswithmotion.thehive.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return postService.getPostsByThreadId(threadId);
    }
}
