package com.pm.tictac;

import com.pm.tictac.controllers.GameController;
import com.pm.tictac.models.*;
import com.pm.tictac.stratergies.winningStratergies.ColWinningStratergy;
import com.pm.tictac.stratergies.winningStratergies.DiagnolWinningStratergy;
import com.pm.tictac.stratergies.winningStratergies.RowWinningStratergy;
import com.pm.tictac.stratergies.winningStratergies.WinningStratergy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TicTacApplication {

    public static void main(String[] args) {

        SpringApplication.run(TicTacApplication.class, args);
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Sid", PlayerType.HUMAN,new Symbol('X')));
        players.add(new Player("giji",PlayerType.HUMAN,new Symbol('Y')));

        List<WinningStratergy> winningStratergies = List.of(
                new RowWinningStratergy(),
                new ColWinningStratergy(),
                new DiagnolWinningStratergy()
        );
        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension, players, winningStratergies);
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);
            gameController.makeMove(game);
        }
        if(game.getGameState().equals(GameState.ENDED)){
            System.out.println(gameController.getWinner(game).getName() + "has won the game");
        }else{
            System.out.println("Game Draw");
        }
    }

}
