package com.wg.gichess.chessboard.mondstadt;

import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.TerrainType;

public class MondstadtcityCB extends ChessBoard {

    public MondstadtcityCB(){
        super(2);

        this.builder()
                .setAllCellsT(0, TerrainType.STONEGROUND)
                .setCellT(6,4,0,TerrainType.WATER)
                .setRectangleCellT(5,2,7,2,0,TerrainType.WATER)
                .setRectangleCellT(5,6,7,6,0,TerrainType.WATER)
                .setRectangleCellT(4,3,4,5,0,TerrainType.WATER)
                .setRectangleCellT(8,3,8,5,0,TerrainType.WATER)
                .setAllCellsT(1, TerrainType.SANDSOIL);



    }
}
