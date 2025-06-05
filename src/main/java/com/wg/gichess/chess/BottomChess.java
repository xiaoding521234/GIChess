package com.wg.gichess.chess;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.TeamType;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.RoleTag;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.AnimationManager;
import com.wg.gichess.material.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BottomChess {

    public Coord coord = new Coord() ;
    public TeamType teamType = TeamType.NEU;
    public ChessType chessType = ChessType.ROLE;

    public int index=0;

    public AnimationManager animationManager = new AnimationManager();


    public boolean shouldRender = true;


    public BottomChess(int x, int y, ChessType chessType) {
        this.coord = new Coord(x,y);
        this.chessType = chessType;
    }

    public BottomChess(Coord coord,ChessType chessType){
        if(coord!=null){
            this.coord.setCoord(coord);
        }
        this.chessType = chessType;
    }

    public void setTeamType(TeamType teamType) {
        this.teamType = teamType;
    }






}
