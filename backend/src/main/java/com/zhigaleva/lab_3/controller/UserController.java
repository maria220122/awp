package com.zhigaleva.lab_3.controller;

import com.zhigaleva.lab_3.dto.UserCreateDto;
import com.zhigaleva.lab_3.entity.User;
import com.zhigaleva.lab_3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/current")
    public User afterLogin() throws BadRequestException {
        return getCurrentUser();
    }

    @GetMapping("/me")
    public User getCurrentUser() throws BadRequestException {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserUsername(currentUser.getUsername());
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCreateDto dto) {
        userService.createUser(dto);
    }
}
