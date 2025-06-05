package com.wg.gichess.chessboard;

import com.wg.gichess.chess.rolechess.MovementType;

import java.util.EnumSet;

public enum TerrainType {

    GRASS(1, "草地", "textures/terrain/grass.png", EnumSet.of(
            MovementType.WALK,
            MovementType.JUMP,
            MovementType.FLY,
            MovementType.WALKTELEPORT,
            MovementType.FLYTELEPORT
    )),

    STONEGROUND(2, "石路", "textures/terrain/stoneground.png", EnumSet.of(
            MovementType.WALK,
            MovementType.JUMP,
            MovementType.FLY,
            MovementType.WALKTELEPORT,
            MovementType.FLYTELEPORT
    )),

    WATER(3, "水域", "textures/terrain/water.png", EnumSet.of(
            MovementType.FLY,
            MovementType.FLYTELEPORT
    )),

    AIR(4, "虚空", "textures/terrain/air.png", EnumSet.of(
            MovementType.NULL
    )),

    SAND(5, "沙子", "textures/terrain/sand.png", EnumSet.of(
            MovementType.WALK,
            MovementType.JUMP,
            MovementType.FLY,
            MovementType.WALKTELEPORT,
            MovementType.FLYTELEPORT
    )),
    SANDSOIL(6,"砂土","textures/terrain/sandsoil.png", EnumSet.of(
            MovementType.WALK,
            MovementType.JUMP,
            MovementType.FLY,
            MovementType.WALKTELEPORT,
            MovementType.FLYTELEPORT
    )),
    GRASSGROUND(7,"草路","textures/terrain/grassground.png", EnumSet.of(
            MovementType.WALK,
            MovementType.JUMP,
            MovementType.FLY,
            MovementType.WALKTELEPORT,
            MovementType.FLYTELEPORT
    )),
    TREE(8, "树", "textures/terrain/tree.png", EnumSet.of(
            MovementType.JUMP,
            MovementType.FLY,
            MovementType.WALKTELEPORT,
            MovementType.FLYTELEPORT
    ))
    ;





    public final int id;
    public final String displayName;
    public final String materialId;
    public final EnumSet<MovementType> allowedMovements; // 允许的移动方式


    TerrainType(int id, String displayName, String materialId, EnumSet<MovementType> allowedMovements){
     this.id = id;
     this.displayName = displayName;
     this.materialId = materialId;
        this.allowedMovements = allowedMovements;
    }

    public boolean allowsMovement(MovementType movement) {
        return allowedMovements.contains(movement);
    }


}
