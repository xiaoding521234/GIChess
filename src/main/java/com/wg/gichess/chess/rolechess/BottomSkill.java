package com.wg.gichess.chess.rolechess;

import com.wg.gichess.User;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.material.Introduce;
import com.wg.gichess.material.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class BottomSkill implements Skill, Introduce ,RightClickCloseHandler {
    public List<Coord> coords = new ArrayList<>();
    public boolean allowWork = true;
    public boolean hasWork = false;
    public RoleChess roleChess;
    public Material icon = new Material() ;
    public int STA=0;


    public BottomSkill(RoleChess roleChess){
        this.roleChess = roleChess;
    }

    @Override
    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon){
        this.icon = icon;
    }

    @Override
    public boolean canWork() {
        return false;
    }

    @Override
    public boolean hasWork(){
        return hasWork;
    };

    @Override
    public boolean allowWork() {
        return allowWork;
    }

    @Override
    public String getIntroduce() {
        return getDescription();
    }

    @Override
    public void onRightClick() {
        User.chessBoard.chooseSkill = null;
        User.gameMode = User.lastGameMode;
        User.shineCBType = User.lastShineCBType;
        this.onCancel();
    }
}
