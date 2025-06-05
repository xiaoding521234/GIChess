package com.wg.gichess.chess;

public enum ChessType {
    ROLE(0,"角色"),
    OBJECT(1,"物体");


    public final int id;
    public final String name;

    ChessType(int id,String name){
        this.id =id;
        this.name = name;
    }
}
