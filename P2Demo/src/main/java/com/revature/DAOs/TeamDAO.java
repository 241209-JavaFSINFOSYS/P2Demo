package com.revature.DAOs;

import com.revature.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//Extending JpaRepository gives us access to basic CRUD methods that we don't have to write
    //This includes find all, insert, find by id, update, delete

//JpaRepository takes two generics:
    //-The type of the Entity we're working with
    //-The type of the primary key of that Entity
@Repository //1 of the 4 stereotype annotations (makes a class a bean)
public interface TeamDAO extends JpaRepository<Team, UUID> {

    //We can make custom queries as well, for any CRUD op that isn't premade for us

    //This property expression method will find a List of Teams by their location
    List<Team> findByTeamLocation(String location);

    //NOTE: The method must be named findByXyz
    //where Xyz is the name of the field we're selecting by

}
