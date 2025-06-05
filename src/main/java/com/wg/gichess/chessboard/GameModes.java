package com.wg.gichess.chessboard;

public enum GameModes {

    GOWAR(1,"出战模式"),
    DEFAULT(2,"行动模式"),
    SKILL(3,"技能模式"),
    ;




    public final int id;
    public final String displayName;

    GameModes(int id,String displayName){
        this.id = id;
        this.displayName = displayName;
    }

}
