package com.wg.gichess.chess.rolechess;

public enum SkillType {

    MOVEMENT(1,"移动"),
    TACTIC(2,"战技"),
    BURST(3,"爆发"),
    LEADER(4,"领袖"),
    ITEM(5,"道具"),
    ECHO(6,"延奏"),
    VARIATION(7,"变奏"),
    CONTRACT(8,"契约");

    public final int id;
    public final String displayName;

    SkillType( int id,String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}

