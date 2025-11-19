package com.pm.tictac.controllers;

import com.pm.tictac.models.Game;
import com.pm.tictac.models.GameState;
import com.pm.tictac.models.Player;
import com.pm.tictac.stratergies.winningStratergies.WinningStratergy;

import java.util.List;

public class GameController {


    public Game startGame(int dimension, List<Player> players, List<WinningStratergy> winningStratergies) {
        return Game.getBuilder().setDimesnion(dimension).setPlayers(players).setWinningStratergies(winningStratergies).build();
    }
    public GameState getGameState(Game game) {
        return game.getGameState();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
}
