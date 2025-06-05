package com.wg.gichess.chessboard;

public enum CoverType {

    LEAF(1,"树叶","textures/cover/leaf.png"),
    ;





    public final int id;
    public final String displayName;
    public final String materialId;


    CoverType(int id, String displayName, String materialId){
        this.id = id;
        this.displayName = displayName;
        this.materialId = materialId;
    }
}
