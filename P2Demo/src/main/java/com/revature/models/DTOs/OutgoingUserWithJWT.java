package com.revature.models.DTOs;

import com.revature.models.Team;

//Here's another very common DTO -
//send user info without password or any other sensitive info
public class OutgoingUserWithJWT {

    private int userId;
    private String username;
    private String role;
    private Team team;
    private String token; //This will hold the User's JWT!

    //boilerplate-------------------------

    public OutgoingUserWithJWT() {
    }

    public OutgoingUserWithJWT(int userId, String username, String role, Team team, String token) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.team = team;
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                ", token='" + token + '\'' +
                '}';
    }
}

