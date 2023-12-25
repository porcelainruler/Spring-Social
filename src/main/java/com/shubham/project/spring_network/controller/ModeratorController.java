package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.constant.ApiResponseStatus;
import com.shubham.project.spring_network.dto.response.ApiResponse;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.service.IModeratorService;
import com.shubham.project.spring_network.service.ModeratorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Moderator Group", description = "A group of moderator apis")
@RestController
@RequestMapping(path = "${apiPrefixV1}/moderator")
public class ModeratorController {
    // * Logger
    private final Logger logger = LoggerFactory.getLogger(ModeratorController.class);

    private final IModeratorService moderatorService;

    @Autowired
    public ModeratorController (ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/moderators")
    public ApiResponse<List<ModeratorDTO>> getModerators () {
        ApiResponse<List<ModeratorDTO>> apiResponse = null;

        List<ModeratorDTO> result = moderatorService.findAllDTO();

        apiResponse = new ApiResponse<>(HttpStatus.OK.value(), ApiResponseStatus.API_SUCCESS.getValue(), "All valid moderators info fetched successfully", result);

        return apiResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/moderator/{id}")
    public ApiResponse<ModeratorDTO> getModerator (@RequestParam(name = "id") long id) throws Exception {
        ApiResponse<ModeratorDTO> apiResponse = null;

        ModeratorDTO modDTO = moderatorService.findDTOById(id);

        if (modDTO == null) {
            apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ApiResponseStatus.NOT_FOUND.getValue(), "Moderator with id " + id + " not found.", null);
            return apiResponse;
        }

        apiResponse = new ApiResponse<>(HttpStatus.OK.value(), ApiResponseStatus.API_SUCCESS.getValue(), "Moderator with id " + id + " info fetched successfully.", modDTO);
        return apiResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/moderator")
    public ApiResponse<ModeratorDTO> createModerator (@RequestBody ModeratorDTO moderatorDTO) {
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/moderator")
    public ApiResponse<ModeratorDTO> updateModerator (@RequestBody ModeratorDTO moderatorDTO) {
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/moderator/{id}")
    public ApiResponse<String> deleteModerator (@RequestParam long id) {
        return null;
    }
}
