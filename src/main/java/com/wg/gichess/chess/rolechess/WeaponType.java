package com.wg.gichess.chess.rolechess;

public enum WeaponType {

    SWORDONEHAND(1,"单手剑"),
    SWORDTWOHAND(2,"双手剑"),
    POLEARM(3,"长柄武器"),
    GAUNTLET(4,"臂铠"),

    BOW(5,"弓"),
    CATALYST(6,"法器"),
    Gun(7,"枪械");

    public final int id;
    public final String displayName;

    WeaponType(int id,String displayName){
        this.id = id;
        this.displayName = displayName;
    }


}
