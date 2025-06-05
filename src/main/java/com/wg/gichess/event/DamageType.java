package com.wg.gichess.event;

public enum DamageType {


    ;



    public final int id;
    public final String displayName;

    DamageType(int id,String displayName){
        this.id = id;
        this.displayName = displayName;
    }
}
