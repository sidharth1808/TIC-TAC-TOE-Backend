package com.pm.tictac.models;

public class Cell {

    private int row;
    private int col;
    private CellState cellState;
    private Player player;
    public Cell(int i,int j){
        this.row = i;
        this.col = j;
        this.cellState = CellState.EMPTY;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
