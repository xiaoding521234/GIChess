package com.wg.gichess.material.skill;

import com.wg.gichess.User;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.rolechess.BottomSkill;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.SkillType;
import com.wg.gichess.chess.rolechess.TriggerType;

import java.util.List;

public class GIIntrodeceSkill extends BottomSkill {


    public GIIntrodeceSkill(RoleChess roleChess) {
        super(roleChess);
        setIcon(new GI(this,"introduceskill.png"));
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getDisplayName() {
        return "详细信息";
    }

    @Override
    public String getDescription() {

        if(roleChess==null){
            return """
                    地形："""+ User.chessBoard.chooseCell.terrains.getFirst().displayName + """
                    \n\s
                    角色：无
                    """;
        }

        return """
                    地形："""+ User.chessBoard.chooseCell.terrains.getFirst().displayName + """
                    \n\s
                    角色："""+roleChess.displayName+"""
                    \n"""+roleChess.introduceSkill()
                    ;
    }

    @Override
    public SkillType getSkillType() {
        return null;
    }

    @Override
    public TriggerType getTriggerType() {
        return TriggerType.READONLY;
    }

    @Override
    public void init() {

    }

    @Override
    public void skillEffect(Coord coord, List<Coord> coords) {

    }

    @Override
    public void onClick(Coord coord) {

    }

    @Override
    public void skillOver() {

    }

    @Override
    public void onChoose() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onInterrupt() {

    }




}
