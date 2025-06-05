package com.wg.gichess.chessboard.mondstadt;

import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.TerrainType;

public class MondstadtgateCB extends ChessBoard {

    public MondstadtgateCB(){
        super(2);

        this.builder()
                .setAllCellsT(0, TerrainType.WATER)
                .setColumnCellsT(12,0,TerrainType.STONEGROUND)
                .setColumnCellsT(11,0,TerrainType.STONEGROUND)
                .setColumnCellsT(10,0,TerrainType.STONEGROUND)
                .setRectangleCellT(0,0,2,8,0,TerrainType.GRASS)
                .setColumnCellsT(3,0,TerrainType.SAND)
                .setRectangleCellT(3,2,9,6,0,TerrainType.STONEGROUND)
                .setAllCellsT(1,TerrainType.SANDSOIL)
                .setRectangleCellT(4,0,9,8,1,TerrainType.WATER);
    }
}
