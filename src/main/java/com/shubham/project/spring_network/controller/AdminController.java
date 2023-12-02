package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.dao.AdminDAO;
import com.shubham.project.spring_network.persistence.dao.MemberDAO;
import com.shubham.project.spring_network.persistence.dao.ModeratorDAO;
import com.shubham.project.spring_network.persistence.dao.UserDAO;
import com.shubham.project.spring_network.persistence.model.Moderator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Admin Group", description = "A group of admin apis")
@RestController
@Log4j2
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private MemberDAO memberDAO;

    private ModeratorDAO moderatorDAO;

    private AdminDAO adminDAO;

    private UserDAO userDAO;

    @Autowired
    public AdminController (MemberDAO memberDAO, ModeratorDAO moderatorDAO, AdminDAO adminDAO, UserDAO userDAO) {
        this.memberDAO = memberDAO;
        this.moderatorDAO = moderatorDAO;
        this.adminDAO = adminDAO;
        this.userDAO = userDAO;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/moderators")
    public ResponseEntity<List<ModeratorDTO>> getModerators () {
        List<Moderator> moderators = moderatorDAO.findAll();

        List<ModeratorDTO> result = new ArrayList<>();

        for (Moderator moderator : moderators) {
            ModeratorDTO modDTO = new ModeratorDTO.ModeratorDTOBuilder()
                    .setId(moderator.getId())
                    .setName(moderator.getName())
                    .setUsername(moderator.getUsername())
                    .setEmail(moderator.getEmail())
                    .setPhone(moderator.getPhone())
                    .setAddress(moderator.getAddress())
                    .setEnabled(moderator.isEnabled())
                    .build();

            result.add(modDTO);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/moderator/{id}")
    public ResponseEntity<ModeratorDTO> getModerator (@RequestParam(name = "id") long id) throws Exception {
        Moderator moderator = moderatorDAO.findById(id);


        if (moderator != null) {
            ModeratorDTO modDTO = new ModeratorDTO.ModeratorDTOBuilder()
                    .setId(moderator.getId())
                    .setName(moderator.getName())
                    .setUsername(moderator.getUsername())
                    .setEmail(moderator.getEmail())
                    .setPhone(moderator.getPhone())
                    .setAddress(moderator.getAddress())
                    .setEnabled(moderator.isEnabled())
                    .build();

            return new ResponseEntity<>(modDTO, HttpStatus.OK);
        } else {
            throw new Exception("Moderator with id " + id + " not found.");
        }
    }
}
