package com.pm.tictac.service;

import com.pm.tictac.dto.*;
import com.pm.tictac.models.*;
import com.pm.tictac.stratergies.winningStratergies.ColWinningStratergy;
import com.pm.tictac.stratergies.winningStratergies.DiagnolWinningStratergy;
import com.pm.tictac.stratergies.winningStratergies.RowWinningStratergy;
import com.pm.tictac.stratergies.winningStratergies.WinningStratergy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    
    private final Map<String, Game> games = new ConcurrentHashMap<>();
    
    public GameStateResponse startGame(StartGameRequest request) {
        String gameId = UUID.randomUUID().toString();
        
        List<Player> players = new ArrayList<>();
        for (PlayerRequest pr : request.getPlayers()) {
            PlayerType type = PlayerType.valueOf(pr.getPlayerType().toUpperCase());
            players.add(new Player(pr.getName(), type, new Symbol(pr.getSymbol())));
        }
        
        List<WinningStratergy> winningStratergies = List.of(
            new RowWinningStratergy(),
            new ColWinningStratergy(),
            new DiagnolWinningStratergy()
        );
        
        Game game = Game.getBuilder()
            .setDimesnion(request.getDimension())
            .setPlayers(players)
            .setWinningStratergies(winningStratergies)
            .build();
            
        games.put(gameId, game);
        
        return buildGameStateResponse(gameId, game);
    }
    
    public GameStateResponse makeMove(String gameId, MakeMoveRequest request) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found");
        }
        
        Player currentPlayer = game.getPlayers().get(game.getNextPlayerIndex());
        Cell cell = new Cell(request.getRow(), request.getCol());
        Move move = new Move(currentPlayer, cell);
        
        if (!game.validateMove(move)) {
            throw new IllegalArgumentException("Invalid move");
        }
        
        // Execute the move
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell boardCell = game.getBoard().getBoard().get(row).get(col);
        boardCell.setCellState(CellState.FILLED);
        boardCell.setPlayer(currentPlayer);
        Move finalMove = new Move(currentPlayer, boardCell);
        game.getMoves().add(finalMove);
        game.setNextPlayerIndex((game.getNextPlayerIndex() + 1) % game.getPlayers().size());
        
        if (checkWinner(game, finalMove)) {
            game.setWinner(currentPlayer);
            game.setGameState(GameState.ENDED);
        } else if (game.getMoves().size() == game.getBoard().getDimension() * game.getBoard().getDimension()) {
            game.setGameState(GameState.DRAW);
        }
        
        return buildGameStateResponse(gameId, game);
    }
    
    private boolean checkWinner(Game game, Move move) {
        for (WinningStratergy strategy : game.getWinningStratergies()) {
            if (strategy.checkWinner(game.getBoard(), move)) {
                return true;
            }
        }
        return false;
    }
    
    public GameStateResponse getGameState(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException("Game not found");
        }
        return buildGameStateResponse(gameId, game);
    }
    
    private GameStateResponse buildGameStateResponse(String gameId, Game game) {
        List<List<CellResponse>> boardResponse = new ArrayList<>();
        Board board = game.getBoard();
        
        for (int i = 0; i < board.getDimension(); i++) {
            List<CellResponse> row = new ArrayList<>();
            for (int j = 0; j < board.getDimension(); j++) {
                Cell cell = board.getBoard().get(i).get(j);
                Character symbol = null;
                String playerName = null;
                if (cell.getCellState() == CellState.FILLED && cell.getPlayer() != null) {
                    symbol = cell.getPlayer().getSymbol().getaChar();
                    playerName = cell.getPlayer().getName();
                }
                row.add(new CellResponse(
                    i, j, 
                    cell.getCellState().name(),
                    symbol,
                    playerName
                ));
            }
            boardResponse.add(row);
        }
        
        String currentPlayer = null;
        if (game.getGameState() == GameState.IN_PROGRESS) {
            currentPlayer = game.getPlayers().get(game.getNextPlayerIndex()).getName();
        }
        
        String winner = null;
        if (game.getWinner() != null) {
            winner = game.getWinner().getName();
        }
        
        return new GameStateResponse(
            gameId,
            boardResponse,
            game.getGameState().name(),
            currentPlayer,
            winner,
            game.getBoard().getDimension()
        );
    }
}
