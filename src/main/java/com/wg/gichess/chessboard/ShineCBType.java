package com.wg.gichess.chessboard;

public enum ShineCBType {
    ONROWORCOLUMN(1,"十字型"),
    ONDIAGONAL(2,"x字型"),
    ONCOORD(3,"仅所指");

    public final int id;
    public final String displayName;

    ShineCBType(int id, String displayName){
        this.id = id;
        this.displayName = displayName;
    }

}
