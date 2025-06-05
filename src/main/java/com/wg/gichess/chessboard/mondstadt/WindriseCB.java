package com.wg.gichess.chessboard.mondstadt;

import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.CoverType;
import com.wg.gichess.chessboard.TerrainType;

public class WindriseCB extends ChessBoard {

    public WindriseCB(){
        super(2);

        this.builder()
                .setAllCellsT(0, TerrainType.GRASS)
                .setCellT(6,4,0,TerrainType.TREE)
                .setRectangleCellC(5,3,7,5, CoverType.LEAF)
                .setAllCellsT(1,TerrainType.SANDSOIL);



    }
}
