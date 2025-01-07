package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

//Check the User model comments for notes on each annotation

@Component
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID teamId;

    @Column(nullable=false)
    private String teamName;

    @Column(nullable=false)
    private String teamLocation;

    /*connection to user FK
    *
    * mappedBy - Indicates the FK field in the User class
    *
    * cascade - defines how changes to a Team records will affect dependent User records
        * CascadeType.ALL = any change to a Team record will affect dependent records
    *
    * */
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore //prevents circular reference in our JSON responses
    private List<User> users;

    //boilerplate code-----------------------no args, all args, getter/setter, toString

    public Team() {
    }

    public Team(UUID teamId, String teamName, String teamLocation) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamLocation = teamLocation;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamLocation='" + teamLocation + '\'' +
                '}';
    }
}
