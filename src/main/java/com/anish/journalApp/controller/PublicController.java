package com.anish.journalApp.controller;

import com.anish.journalApp.entity.User;
import com.anish.journalApp.dto.UserDTO;
import com.anish.journalApp.service.UserDetailServiceIMPL;
import com.anish.journalApp.service.UserService;
import com.anish.journalApp.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
@Tag(name = "Public APIs" , description = "sign-up, login and health-check")
public class PublicController {

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceIMPL userDetailService;

    @Autowired
    private UserService userService;

    @GetMapping("health-check")
    @Operation(summary = "check if server is live")
    public String HealthCheck(){
        return "ok";
    }

    @PostMapping("/sign-up")
    @Operation(summary = "create a new user or sign up a user")
    public void signUp(@RequestBody UserDTO user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        userService.saveNewUser(newUser);
    }
    @PostMapping("/login")
    @Operation(summary = "login with existing user")
    public ResponseEntity<String> login(@RequestBody UserDTO user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String jwt =jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.error("Exception occurred while creating authenticationToken", e);
            return new ResponseEntity<>("Incorrect username or password ", HttpStatus.BAD_REQUEST);
        }
    }


}
