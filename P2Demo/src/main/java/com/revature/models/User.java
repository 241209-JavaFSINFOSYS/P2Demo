package com.revature.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component //1 of the 4 stereotype annotations (makes the class a Bean)
@Entity //This annotation makes a DB table based on this Class
@Table(name = "users") //This annotation lets us specify properties (like table name)
public class User implements UserDetails {

    @Id //This annotation makes the field a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This makes our PK auto-increment integers
    private int userId;

    //@Column isn't necessary UNLESS you want to set DB column name or constraints

    @Column(nullable = false) //This column MUST have a value on insert
    private String username;

    @Column(nullable = false) //This column MUST have a value on insert
    private String password;

    @Column(nullable = false)
    private String role = "player"; //Every new user will be a player by default

    /* FK to team (every user has a team - many users can belong to one team)
    *
    * fetch - defines when the Dependency is loaded
        * LAZY = loads dependency only when it's called
        * EAGER = loads dependency at runtime (I usually use this)
    * @JoinColumn - defines the column that will be used to link these tables in the DB
        * We have to provide the name of the PK field in Team */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId") //This links our FK to the PK in Team (teamId)
    private Team team;

    //boilerplate code-----------------------no args, all args, getter/setter, toString

    public User() {
    }

    public User(int userId, String username, String password, String role, Team team) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.team = team;
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

    //~~~~~~~~~~~~~~~ UserDetails Overrides ~~~~~~~~~~~~~~~~
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    //~~~~~~~~~~~~~~~ UserDetails Overrides ~~~~~~~~~~~~~~~~

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", team=" + team +
                '}';
    }
}