package com.wg.gichess.event;

public enum TurnOverType {

    GOWAR(1,"出战回合结束"),
    DEFFAULT(2,"行动回合结束"),
    ;


    public final int id;
    public final String displayName;

    TurnOverType(int id,String displayName){
        this.id = id;
        this.displayName = displayName;
    }
}
