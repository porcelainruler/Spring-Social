package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.constant.ApiResponseStatus;
import com.shubham.project.spring_network.dto.response.ApiResponse;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin Group", description = "A group of admin apis")
@RestController
@RequestMapping(path = "${apiPrefixV1}/auth")
@Log4j2
public class AdminController {

    // * Logger
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    // * Services
    private final IMemberService memberService;

    private final IModeratorService moderatorService;

    private final IAdminService adminService;

    private final IUserService userService;


    @Autowired
    public AdminController (MemberService memberService, ModeratorService moderatorService, AdminService adminService, UserService userService) {
        this.memberService = memberService;
        this.moderatorService = moderatorService;
        this.adminService = adminService;
        this.userService = userService;
    }


}
