package com.pm.tictac.stratergies.botPlayingStratergies;

import com.pm.tictac.models.Board;
import com.pm.tictac.models.Cell;
import com.pm.tictac.models.CellState;
import com.pm.tictac.models.Move;

import java.util.List;

public class EasyBotPlayingStratergy implements BotPlayingStratergy{

    @Override
    public Move makeMove(Board board) {
        for(List<Cell> cells : board.getBoard()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(null,cell);
                }
            }
        }
        return null;
    }
}
