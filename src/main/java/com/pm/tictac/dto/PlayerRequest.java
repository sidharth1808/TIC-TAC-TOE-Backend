package com.pm.tictac.dto;

import lombok.Data;

@Data
public class PlayerRequest {
    private String name;
    private String playerType; // HUMAN or BOT
    private char symbol;
}
