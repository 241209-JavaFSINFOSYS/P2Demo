package com.revature.controllers;

import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.DTOs.OutgoingUserWithJWT;
import com.revature.models.User;
//import com.revature.services.AuthService;
import com.revature.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = {"http://localhost:5173",
        "http://p2demobucketbjp.s3-website-us-east-1.amazonaws.com"},
        allowCredentials = "true")
public class AuthController {

    //autowire the AuthenticationManager and the JwtTokenUtil
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){

        //NOTE: No more HTTP sessions! We're using JWTs now!
        //No calls to the service layer either!

        //Attempt to log in
        try{
            //The AuthenticationManager from Spring Security is now in charge of checking username/password
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            //If auth is successful, build a user based on the logged in user data
            User user = (User) auth.getPrincipal();

            //Finally, generate a JWT for the user to use in subsequent requests
            String token = jwtTokenUtil.generateAccessToken(user);

            //Return the OutgoingUserDTO info to the client
            //NOTE: in a real app, we could just send the JWT as it has all the info we need
                //But I'm going to return all the user info just for clarity in in our returns
            return ResponseEntity.ok(new OutgoingUserWithJWT(
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getTeam(),
                    token
            ));

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }

}
