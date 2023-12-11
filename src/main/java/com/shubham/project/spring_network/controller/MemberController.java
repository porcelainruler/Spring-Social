package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.dto.response.ApiResponse;
import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.service.IMemberService;
import com.shubham.project.spring_network.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Member Group", description = "A group of member apis")
@RestController
@RequestMapping(path = "${apiPrefixV1}/member")
public class MemberController {
    // * Logger
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    // * Required services init /  dep-injection
    private final IMemberService memberService;

    @Autowired
    public MemberController (MemberService memberService) {
        this.memberService = memberService;
    }

    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    @GetMapping("/member")
    public ApiResponse<List<MemberDTO>> getAllMembers () {
        return null;
    }

    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    @GetMapping("/member/{id}")
    public ApiResponse<MemberDTO> getMemberById (@RequestParam(name = "id") long id) throws Exception {
        return null;
    }


}
