package com.revature.DAOs;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //make the class a bean
public interface UserDAO extends JpaRepository<User, Integer> {

    //Find a List of Users by their Team id FK
    //This property expression will need to dig into the team field of User to find the ID
    public List<User> findByTeam_TeamId(int teamId);

    //Find by Username
    public User findByUsername(String username);

}