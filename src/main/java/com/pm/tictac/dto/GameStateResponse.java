package com.pm.tictac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStateResponse {
    private String gameId;
    private List<List<CellResponse>> board;
    private String gameState;
    private String currentPlayer;
    private String winner;
    private int dimension;
}
