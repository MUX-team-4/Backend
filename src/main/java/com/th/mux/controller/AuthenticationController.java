package com.th.mux.controller;

import com.th.mux.dto.UserDto;
import com.th.mux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    // parameter email
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        log.info("login user={}", userDto.getEmail());
        UserDto savedUserDto = userService.isUserExist(userDto);
        if (savedUserDto == null) {
            throw new RuntimeException("User do not exist");
        }
        return ResponseEntity.ok(userService.isUserExist(userDto));
    }
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
//        return null;
//    }
}
