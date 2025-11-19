package com.pm.tictac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CellResponse {
    private int row;
    private int col;
    private String state; // EMPTY or FILLED
    private Character symbol;
    private String playerName;
}
