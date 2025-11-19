package com.pm.tictac.stratergies.winningStratergies;

import com.pm.tictac.models.Board;
import com.pm.tictac.models.Move;
import com.pm.tictac.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStratergy implements WinningStratergy{

    private Map<Integer,Map<Symbol,Integer>> colCount = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!colCount.containsKey(col)){
            colCount.put(col, new HashMap<>());
        }
        Map<Symbol, Integer> colMap = colCount.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol, 1);
        }
        colMap.put(symbol,colMap.get(symbol)+1);
        if(colMap.get(symbol)==board.getDimension()){
            return true;
        }
        return false;
    }

}
