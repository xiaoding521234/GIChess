package com.wg.gichess.chess.rolechess;

public enum TriggerType {
    ACTIVE("activeskill","主动"),
    PASSIVE("passiveskill","被动"),
    READONLY("readonly","仅读");


    public final String name;
    public final String displayName;

    TriggerType(String name,String displayName){
        this.name = name;
        this.displayName = displayName;
    }
}
