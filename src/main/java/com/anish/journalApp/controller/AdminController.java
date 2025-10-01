package com.anish.journalApp.controller;

import com.anish.journalApp.cache.AppCache;
import com.anish.journalApp.entity.User;
import com.anish.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs", description = "get all users, create admin user and clears app-cache")
public class AdminController {

    @Autowired
    AppCache appCache;
    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    @Operation(summary = "get all existing users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    @Operation(summary = "create a new admin user")
    public void createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("/clear-app-cache")
    @Operation(summary = "initializes the external api url as soon as the app starts ")
    public void clearAppCache(){
        appCache.init();
    }
}
