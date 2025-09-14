package com.maisto.mcp_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Team(@JsonProperty(value = "nome")String name,@JsonProperty(value = "città") String city, @JsonProperty(value = "stadio ufficiale") String stadium, @JsonProperty(value = "allenatore")String coach) { }
