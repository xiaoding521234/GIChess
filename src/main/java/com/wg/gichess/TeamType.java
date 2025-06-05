package com.wg.gichess;



public enum TeamType {

    NEU(-1,"neu","白"),
    RED(0,"red","红"),
    BLUE(1,"blue","蓝");



    public final int id;
    public final String name;
    public final String displayName;

    TeamType(int id ,String name ,String displayName){
        this.id = id;
        this.name = name;
        this.displayName = displayName;
    }




    public TeamType switchTeam() {
        return switch (this) {
            case RED -> BLUE;
            case BLUE -> RED;
            default -> this;
        };
    }
}
