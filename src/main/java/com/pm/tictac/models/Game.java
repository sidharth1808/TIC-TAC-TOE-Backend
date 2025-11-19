package com.pm.tictac.models;

import com.pm.tictac.exceptions.InvalidBotCountException;
import com.pm.tictac.exceptions.InvalidPlayerCountException;
import com.pm.tictac.stratergies.winningStratergies.WinningStratergy;

import java.util.ArrayList;
import java.util.List;



public class Game {

    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStratergy> winningStratergies;
    private Game(int dimension,List<Player> players,List<WinningStratergy> winningStratergies) {
        this.players = players;
        this.winningStratergies = winningStratergies;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();

    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public void printBoard(){
        board.print();
    }
    public void makeMove(){
     Player currentPlayer = players.get(nextPlayerIndex);
     System.out.println("It is " + currentPlayer.getName()+"'s turn");
     //ask where the player want to make the move
        Move move = currentPlayer.makeMove(board);
        if(!validateMove(move)){
            throw new InvalidBotCountException("ss");
        }
        // execute the move
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);
        Move finalMove = new Move(currentPlayer,cell);
        moves.add(finalMove);
        nextPlayerIndex=(nextPlayerIndex + 1)%players.size();
        if(checkWinner(finalMove)){
            winner = currentPlayer;
            gameState = GameState.ENDED;
        }else if(moves.size() == board.getDimension()*board.getDimension()){
            gameState = GameState.DRAW;

        }

    }
    public boolean validateMove(Move move){
    Player player = move.getPlayer();
    Cell cell = move.getCell();
    int row = cell.getRow();
    int col = cell.getCol();
    if(row<0|| row>=board.getDimension()||col<0|| col>=board.getDimension()||!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
    return false;
    }
    return true;

    }
    public boolean checkWinner(Move move){
        for(WinningStratergy winningStratergy : winningStratergies){
            winningStratergy.checkWinner(board,move);
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<WinningStratergy> getWinningStratergies() {
        return winningStratergies;
    }

    public void setWinningStratergies(List<WinningStratergy> winningStratergies) {
        this.winningStratergies = winningStratergies;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }


    public static class Builder{
        private List<Player> players;
        private int dimesnion;
        private List<WinningStratergy> winningStratergies;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStratergies(List<WinningStratergy> winningStratergies) {
            this.winningStratergies = winningStratergies;
            return this;
        }

        public Builder setDimesnion(int dimesnion) {
            this.dimesnion = dimesnion;
            return this;
        }
        private void validatePlayerCount(){
            if(players.size()!= dimesnion -1){
                throw new InvalidPlayerCountException("dimesnion less than 1 player allowed");
            }
        }

        private void validateBotCount(){
            int botCount=0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
                if(botCount>1){
                    throw new InvalidBotCountException("Only one bot allowed");
                }
            }
        }

        private void validate(){
         validateBotCount();
            validatePlayerCount();
        }
        public Game build(){
            validate();
            return new Game(dimesnion,players,winningStratergies);
        }

    }
}
