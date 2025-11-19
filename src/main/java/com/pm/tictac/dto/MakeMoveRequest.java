package com.pm.tictac.dto;

import lombok.Data;

@Data
public class MakeMoveRequest {
    private int row;
    private int col;
}
