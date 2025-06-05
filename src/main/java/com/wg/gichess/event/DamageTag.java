package com.wg.gichess.event;

public enum DamageTag {


    TACTIC(1,"战技"),
    BURST(2,"爆发"),
    ITEM(3,"道具"),

    SINGLE(4,"单体"),
    AOE(5,"群体"),
    ;



    public final int id;
    public final String displayName;

    DamageTag( int id,String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
