package com.revature.models.DTOs;

import com.revature.models.Team;

import java.util.UUID;

//Here's another very common DTO -
//send user info without password or any other sensitive info
public class OutgoingUserDTO {

    private UUID userId;
    private String username;
    private String role;
    private Team team;

    //boilerplate-------------------------


    public OutgoingUserDTO() {
    }

    public OutgoingUserDTO(UUID userId, String username, String role, Team team) {
        this.userId = userId;
        this.team = team;
        this.role = role;
        this.username = username;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                '}';
    }
}
