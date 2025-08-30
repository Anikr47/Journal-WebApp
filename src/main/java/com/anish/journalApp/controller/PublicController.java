package com.anish.journalApp.controller;

import com.anish.journalApp.cache.AppCache;
import com.anish.journalApp.entity.User;
import com.anish.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;
    @GetMapping("health-check")
    public String HealthCheck(){
        return "ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


}
