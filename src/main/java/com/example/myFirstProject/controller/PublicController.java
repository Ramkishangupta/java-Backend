package com.example.myFirstProject.controller;


import com.example.myFirstProject.entry.User;
import com.example.myFirstProject.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserEntryService userService;
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

}
