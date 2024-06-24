package com.th.mux.controller;

import com.th.mux.dto.ProfileDto;
import com.th.mux.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/profil")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/{id}")
    ResponseEntity<ProfileDto> updateProfile(@PathVariable(name = "id") long userId, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(userId, profileDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProfileDto> getProfile(@PathVariable(name = "id") long userId) {
        return ResponseEntity.ok(profileService.getProfile(userId));
    }
}
