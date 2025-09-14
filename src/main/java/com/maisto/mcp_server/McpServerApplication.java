package com.maisto.mcp_server;

import com.maisto.mcp_server.model.TeamsManager;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/*We want to create an mcp server to handle a simple list of football teams.
* Some team's name will be invented
* */

@SpringBootApplication
public class McpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

	//this is the way application communicates with the client providing it its tools
	@Bean
	public List<ToolCallback> springIOSquadreTools(TeamsManager teamsManager){
		return List.of(ToolCallbacks.from(teamsManager)) ;
	}
}
