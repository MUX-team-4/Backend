package com.th.mux.controller;

import com.th.mux.model.User;
import com.th.mux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("api/mitarbeiter")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    EntityResponse<List<User>> getUsers() {
        return null;
    }

    @GetMapping("/{id}")
    // id ist dienstelle id
    EntityResponse<List<User>> getUsersInDepartment(@RequestBody int departmentId) {
        return null;
    }
}
