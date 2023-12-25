package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.constant.ApiResponseStatus;
import com.shubham.project.spring_network.dto.request.MemberCreateDTO;
import com.shubham.project.spring_network.dto.response.ApiResponse;
import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.exceptions.UserNotFoundException;
import com.shubham.project.spring_network.service.IMemberService;
import com.shubham.project.spring_network.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        ApiResponse<List<MemberDTO>> apiResponse = null;

        List<MemberDTO> result = memberService.findAllDTO();

        apiResponse = new ApiResponse<>(HttpStatus.OK.value(), ApiResponseStatus.API_SUCCESS.getValue(), "All valid moderators info fetched successfully", result);

        return apiResponse;
    }

    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    @GetMapping("/member/{id}")
    public ApiResponse<MemberDTO> getMemberById (@RequestParam(name = "id") long id) throws Exception {
        ApiResponse<MemberDTO> apiResponse = null;

        MemberDTO memDTO = memberService.findDTOById(id);

        if (memDTO == null) {
            apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ApiResponseStatus.NOT_FOUND.getValue(), "Member with id " + id + " not found.", null);
            return apiResponse;
        }

        apiResponse = new ApiResponse<>(HttpStatus.OK.value(), ApiResponseStatus.API_SUCCESS.getValue(), "Member with id " + id + " info fetched successfully.", memDTO);
        return apiResponse;
    }

    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    @PostMapping("/member")
    public ApiResponse<MemberDTO> createMember (@RequestBody MemberCreateDTO member) throws Exception {
        ApiResponse<MemberDTO> response = null;

        MemberDTO memberDTO = memberService.createMember(member);

        if (memberDTO != null) {
            return new ApiResponse<>(HttpStatus.ACCEPTED.value(), ApiResponseStatus.API_SUCCESS.getValue(), "Member " + memberDTO.getUsername() + " created successfully with id: " + memberDTO.getId(), memberDTO);
        }

        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.INTERNAL_SERVER_ERROR.getValue(), "Internal server error encountered in member creation, sorry for inconvenience.", null);
    }

    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    @PutMapping("/member")
    public ApiResponse<MemberDTO> updateMember (@RequestBody MemberCreateDTO member) throws Exception {
        ApiResponse<MemberDTO> response = null;

        MemberDTO memberDTO = memberService.updateMember(member);

        if (memberDTO != null) {
            return new ApiResponse<>(HttpStatus.ACCEPTED.value(), ApiResponseStatus.API_SUCCESS.getValue(), "Member " + memberDTO.getUsername() + " updated successfully with id: " + memberDTO.getId(), memberDTO);
        }

        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.INTERNAL_SERVER_ERROR.getValue(), "Internal server error encountered in member update, sorry for inconvenience.", null);
    }

    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    @DeleteMapping("/member/{id}")
    public ApiResponse<Boolean> deleteMember (@RequestParam long id) throws UserNotFoundException {
        boolean isDeleted = memberService.deleteMember(id);

        ApiResponse<Boolean> apiResponse = new ApiResponse<>(HttpStatus.ACCEPTED.value(), ApiResponseStatus.API_SUCCESS.getValue(), "Member deleted successfully with response: " + isDeleted, isDeleted);

        return apiResponse;
    }

}
