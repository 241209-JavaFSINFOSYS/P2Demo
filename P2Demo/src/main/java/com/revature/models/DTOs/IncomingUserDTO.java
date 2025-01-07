package com.revature.models.DTOs;

//This is a Data Transfer Object (DTO)
//It will be used to model incoming JSON data for a new User
    //We get to skip the userId, and we can just use an int for the team,
        //(instead of an entire JSON team object)

import java.util.UUID;

//NOTE: DTOs will be converted into their respective model in the Service
public class IncomingUserDTO {

    private String username;
    private String password;
    private String role = "player";
    private UUID teamId;

    //boilerplate-----------------------

    public IncomingUserDTO() {
    }

    public IncomingUserDTO(String username, String password, String role, UUID teamId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.teamId = teamId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "IncomingUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}


/* DTOs are the difference between this:

{
  "userId": 1,
  "name": "John"
  "team": {
    field1,
    field2,
    ...
   }
}

  And this:

{
  "name": "John",
  "teamId": 1
}

  Which one looks easier to work with? */