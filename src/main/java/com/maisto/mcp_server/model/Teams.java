package com.maisto.mcp_server.model;

import java.util.List;

public class Teams {

    private List<Team> teams;

    public Teams() {
    }

    public Teams(List<Team> teams) {
        this.teams = teams;
    }


    public List<Team> getTeams() {
        return teams;
    }

    public void setSquadre(List<Team> teams) {
        this.teams = teams;
    }

}
