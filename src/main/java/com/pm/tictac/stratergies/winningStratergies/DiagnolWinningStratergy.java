package com.pm.tictac.stratergies.winningStratergies;

import com.pm.tictac.models.Board;
import com.pm.tictac.models.Move;
import com.pm.tictac.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStratergy implements WinningStratergy{
private Map<Symbol,Integer> leftDia = new HashMap<>();
private Map<Symbol,Integer> rightDia = new HashMap<>();
@Override
    public boolean checkWinner(Board board, Move move){
    int row = move.getCell().getRow();
    int col = move.getCell().getCol();
    Symbol symbol = move.getPlayer().getSymbol();
    if(row==col){
        if(!leftDia.containsKey(symbol)){
            leftDia.put(symbol,1);
        }
        leftDia.put(symbol,leftDia.get(symbol)+1);
        return leftDia.get(symbol).equals(board.getDimension());
    }
    if(row + col == board.getDimension() - 1){
        if(!rightDia.containsKey(symbol)){
            rightDia.put(symbol,1);
        }
        rightDia.put(symbol,rightDia.get(symbol)+1);
        return rightDia.get(symbol).equals(board.getDimension());
    }

    return false;
}
}
