package com.pm.tictac.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);

    public Player(String name, PlayerType playerType, Symbol symbol){
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public Move makeMove(Board board){
        System.out.println("Enter the row index");
       int row= scanner.nextInt();
        System.out.println("Enter the column index");
        int col = scanner.nextInt();
        return new Move(this,new Cell(row,col));
    }
}
