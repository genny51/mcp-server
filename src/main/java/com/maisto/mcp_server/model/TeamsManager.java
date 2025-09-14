package com.maisto.mcp_server.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TeamsManager {

    private final ObjectMapper objectMapper;
    private ConcurrentHashMap<String, Team> teamsMap = new ConcurrentHashMap<>();
    private Teams teams;

    public TeamsManager(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }

    @Tool(name = "maisto-list-teams" , description = "Retrieve all teams of Maisto Championship")
    public List<Team> getSquadre(){
        return this.teams.getTeams();
    }


    @Tool(name = "maisto-find-team", description = "Search team of Maisto Championship by name , if it's not present, you receive a message of team not found")
    public TeamResponse getTeamByName(String name){
        name = firstUpperCase(name);
        Team team = teamsMap.get(name);
        if(team == null)
            return new TeamResponse(team,"Team not found !");
        return new TeamResponse(team,"Team founded ! ");
    }

    @Tool(name = "maisto-add-team", description = "adds a team that has a name,a city, an official stadium and a coach.")
    public TeamResponse addTeam(String name, String città, String stadio, String allenatore){
        if(name == null || città == null || stadio == null || allenatore == null)
            return new TeamResponse(null,"All fields are required");
        TeamResponse teamResponse = getTeamByName(name);
        if(teamResponse.getTeam() != null) {
            teamResponse.setMessage("Team already present !");
            return teamResponse;
        }
        Team team = new Team(firstUpperCase(name),firstUpperCase(città),firstUpperCase(stadio),firstUpperCase(allenatore));
        teamsMap.put(firstUpperCase(name), team);
        return new TeamResponse(team, "Team successfully added");
    }

    @PostConstruct
    public void init(){

      try(InputStream inputStream = TypeReference.class.getResourceAsStream("/squadre.json")){
          this.teams = objectMapper.readValue(inputStream, Teams.class);
          teams.getTeams().stream()
                  .forEach(team -> teamsMap.put(team.name(), team));
      }
      catch (IOException e) {
          throw new RuntimeException("Failed to read JSON data", e);
      }
    }

      private String firstUpperCase(String name){
         return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        }



}
