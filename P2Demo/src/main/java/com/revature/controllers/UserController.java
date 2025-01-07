package com.revature.controllers;

import com.revature.aspects.AdminOnly;
import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController //Combines @Controller and @ResponseBody
@RequestMapping ("/users")//All HTTP requests ending in /users will be sent here
@CrossOrigin(value = {"http://localhost:5173",
        "http://p2demobucketbjp.s3-website-us-east-1.amazonaws.com"},
        allowCredentials = "true")
public class UserController {

    //autowire the UserService
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //A method that inserts a new User into the DB
    @PostMapping //POST requests ending in /users will invoke this method
    public ResponseEntity<User> insertUser(@RequestBody IncomingUserDTO userDTO){

        //send the UserDTO to the service, which will process it and send it to the DAO
        User user = userService.insertUser(userDTO);

        //send back the User object if all goes well
        return ResponseEntity.status(201).body(user);

    }

    //A method that update a User's password (just one column, so it's a PATCH)
    @PatchMapping("/password/{userId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable UUID userId, @RequestBody String password){

        //one liner - send back a 202 (accepted) with the updated User
        return ResponseEntity.accepted().body(userService.updateUserPassword(userId, password));

    }

    //A method that gets all Users from the DB, returning a List of OutUserDTOs
    @GetMapping
    @AdminOnly //only Admins can access this method (enforced by AuthAspect)
    public ResponseEntity<List<OutgoingUserDTO>> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());

    }

}
