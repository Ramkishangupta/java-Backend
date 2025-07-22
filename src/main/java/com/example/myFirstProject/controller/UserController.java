package com.example.myFirstProject.controller;

import com.example.myFirstProject.Repository.UserEntryRepository;
import com.example.myFirstProject.api.response.WheatherApiResponse;
import com.example.myFirstProject.entry.User;
import com.example.myFirstProject.service.PostService;
import com.example.myFirstProject.service.QuoteService;
import com.example.myFirstProject.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntryService userService;
    @Autowired
    private UserEntryRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll(); // now works correctly
    }
    @Autowired
    private QuoteService quoteService;

    @Autowired
    private PostService postService;


    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User userInDb = userService.findByUserName(user.getUserName());
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WheatherApiResponse weatherResponse = quoteService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
    @PostMapping("/post-service")
    public ResponseEntity<?> postMethod(){
        postService.postMethod();
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}