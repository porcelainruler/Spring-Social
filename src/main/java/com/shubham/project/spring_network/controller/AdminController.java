package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.constant.ApiResponseStatus;
import com.shubham.project.spring_network.dto.response.ApiResponse;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.dao.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin Group", description = "A group of admin apis")
@RestController
@Log4j2
public class AdminController {

    // * Logger
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    // * JPA Repo DAO
    private MemberDAO memberDAO;

    private ModeratorDAO moderatorDAO;

    private AdminDAO adminDAO;

    private UserDAO userDAO;

    private ModelMapper modelMapper;

    @Autowired
    public AdminController (MemberDAO memberDAO, ModeratorDAO moderatorDAO, AdminDAO adminDAO, UserDAO userDAO, ModelMapper modelMapper) {
        this.memberDAO = memberDAO;
        this.moderatorDAO = moderatorDAO;
        this.adminDAO = adminDAO;
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/moderators")
    public ResponseEntity<ApiResponse<List<ModeratorDTO>>> getModerators () {
        ApiResponse<List<ModeratorDTO>> apiResponse = null;

        List<ModeratorDTO> result = moderatorDAO.findAllDTO();

        apiResponse = new ApiResponse<>(HttpStatus.OK.value(), ApiResponseStatus.API_SUCCESS.getValue(), "All valid moderators info fetched successfully", result);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/moderator/{id}")
    public ResponseEntity<ApiResponse<ModeratorDTO>> getModerator (@RequestParam(name = "id") long id) throws Exception {
        ApiResponse<ModeratorDTO> apiResponse = null;

        ModeratorDTO modDTO = moderatorDAO.findDTOById(id);

        if (modDTO == null) {
            apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ApiResponseStatus.NOT_FOUND.getValue(), "Moderator with id " + id + " not found.", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        apiResponse = new ApiResponse<>(HttpStatus.OK.value(), ApiResponseStatus.API_SUCCESS.getValue(), "Moderator with id " + id + " info fetched successfully.", modDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
