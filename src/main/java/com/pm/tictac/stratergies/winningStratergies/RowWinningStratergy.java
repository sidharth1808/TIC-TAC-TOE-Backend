package com.pm.tictac.stratergies.winningStratergies;

import com.pm.tictac.models.Board;
import com.pm.tictac.models.Move;
import com.pm.tictac.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStratergy implements WinningStratergy {
private Map<Integer,Map<Symbol,Integer>> rowCount = new HashMap<>();

@Override
    public boolean checkWinner(Board board, Move move) {
    int row = move.getCell().getRow();
    int col = move.getCell().getCol();
    Symbol symbol = move.getPlayer().getSymbol();
    if(!rowCount.containsKey(row)){
        rowCount.put(row, new HashMap<>());
    }
    Map<Symbol,Integer> rowMap = rowCount.get(row);
    if(!rowMap.containsKey(symbol)){
        rowMap.put(symbol, 1);
    }
    rowMap.put(symbol,rowMap.get(symbol)+1);
    if(rowMap.get(symbol)== board.getDimension()){
        return true;
    }
    return false;
}

}
