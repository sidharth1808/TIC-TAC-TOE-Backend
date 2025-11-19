package com.pm.tictac.stratergies.winningStratergies;

import com.pm.tictac.models.Board;
import com.pm.tictac.models.Move;

public interface WinningStratergy {

    public boolean checkWinner(Board board, Move move);
}
