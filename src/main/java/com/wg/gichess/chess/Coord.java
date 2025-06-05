package com.wg.gichess.chess;

public class Coord {

    public int x;
    public int y;

    public Coord(){
        this.x = 0;
        this.y = 0;
    }

    public Coord(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoord(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void setCoord(Coord coord){
        this.x = coord.x;
        this.y = coord.y;
    }
}
