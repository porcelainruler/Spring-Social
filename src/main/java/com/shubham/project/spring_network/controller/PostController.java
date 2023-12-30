package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.dto.response.ApiResponse;
import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.dto.response.PostDTO;
import com.shubham.project.spring_network.service.IMemberService;
import com.shubham.project.spring_network.service.IPostService;
import com.shubham.project.spring_network.service.MemberService;
import com.shubham.project.spring_network.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Post Group", description = "A group of post apis")
@RestController
@RequestMapping(path = "${apiPrefixV1}/post")
public class PostController {
    // * Logger
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    // * Required services init /  dep-injection
    private final IPostService postService;

    @Autowired
    public PostController (PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR','ADMIN')")
    @GetMapping("/post")
    public ApiResponse<List<PostDTO>> getAllPosts () {
    return null;
    }
}
