package com.wg.gichess.chessboard.mondstadt;

import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.TerrainType;

public class StartingpointCB extends ChessBoard {


    public StartingpointCB(){
        super(2);

        this.builder()
                .setAllCellsT(0, TerrainType.GRASS)
                .setRowCellsT(6,0,TerrainType.SAND)
                .setRectangleCellT(0,7,12,8,0,TerrainType.WATER)
                .setAllCellsT(1,TerrainType.SANDSOIL);



    }



}
