package com.wg.gichess.material.battlescreen;

import com.wg.gichess.Event;
import com.wg.gichess.TeamType;
import com.wg.gichess.User;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.event.TurnOverType;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.animation.screen.NoticeBoxA;

public class TurnOverButton extends Material {


    public TurnOverButton(int x, int y) {
        super("textures/battlescreen/redturnbutton.png", x, y);
        materials.add(this.setSize((int)(width * 0.05),(int)(width * 0.05 *0.83)));
        materials.add(new Material("textures/battlescreen/blueturnbutton.png").setSize((int)(width * 0.05),(int)(width * 0.05 *0.83)).setXY(x,y));

    }

    @Override
    public void render() {

        if(User.chessBoard.currentTurnTeamType == TeamType.RED){
            shouldRenderM = materials.getFirst();

        } else {
            shouldRenderM = materials.get(1);
        }



        if(isMouseOn()){
            color = 0xFF808080;
        }else{
            color = 0xFFFFFFFF;
        }

        shouldRenderM.render(color);

    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            ChessBoard chessBoard = User.chessBoard;
            if(User.teamType != chessBoard.currentTurnTeamType){
                User.sendPromptBoxA("现在不是你的回合");
                return false;
            }
            if(chessBoard.goWarRole != null){
                User.sendPromptBoxA("出战你的角色");
                return false;
            }
            if( User.gameMode == GameModes.GOWAR && chessBoard.getCurrentTurnTeam().goWarSTA >= 1){
                User.sendPromptBoxA("还有出战体力未使用");
                return false;
            }
            if(User.gameMode == GameModes.SKILL){
                User.sendPromptBoxA("正在发动技能中");
                return false;
            }




            TurnOverType turnOverType;
            if(chessBoard.goWarIndex == User.goWarRoleNum){
                turnOverType = TurnOverType.DEFFAULT;
            } else {
                turnOverType = TurnOverType.GOWAR;
            }

            if(!User.isOnline){

                chessBoard.animationManager.addLockAnimations(new NoticeBoxA( chessBoard.currentTurnTeamType.displayName + "方" +turnOverType.displayName),
                        ()->
                        {
                            chessBoard.post(new Event().turnOverRE(turnOverType));
                        }
                        );



            }

        }


        return false;
    }
}
