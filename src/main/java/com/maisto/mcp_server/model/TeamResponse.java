package com.maisto.mcp_server.model;

public class TeamResponse {

    private Team team;
    private String message;

    public TeamResponse(Team team, String message){
        this.team = team;
        this.message=message;
    }

    public Team getTeam() {
        return team;
    }

    public String getMessage() {
        return message;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
