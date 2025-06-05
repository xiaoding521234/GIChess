package com.wg.gichess.chess.rolechess;

public enum MovementType {
    WALK(1,"步行"),
    JUMP(2,"跳跃"),
    FLY(3,"飞行"),
    WALKTELEPORT(4,"着地瞬移"),
    FLYTELEPORT(5,"悬浮瞬移"),
    NULL(6,"虚空")
    ;



    public final int id;
    public final String displayName;

    MovementType(int id, String displayName){
        this.id = id;
        this.displayName = displayName;
    }
}
