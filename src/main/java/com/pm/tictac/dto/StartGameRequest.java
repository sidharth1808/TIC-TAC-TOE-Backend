package com.pm.tictac.dto;

import lombok.Data;

import java.util.List;

@Data
public class StartGameRequest {
    private int dimension;
    private List<PlayerRequest> players;
}
