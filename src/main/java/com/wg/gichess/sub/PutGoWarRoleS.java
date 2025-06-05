package com.wg.gichess.sub;

import com.wg.gichess.*;
import com.wg.gichess.chess.rolechess.BottomSkill;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.event.ClickE;
import com.wg.gichess.event.PutGoWarRoleRE;
import com.wg.gichess.material.animation.rolechess.PutRoleA;

public class PutGoWarRoleS extends Subscribers {

    public PutGoWarRoleS(){

        super(User.chessBoard);


        Subscriber<ClickE> s1 = new Subscriber<ClickE>() {

            @Override
            public void handleEvent(ClickE event) {


                ChessBoard chessBoard = User.chessBoard;

                if(!event.allowWork){
                    System.out.println("事件禁止工作");
                    return;
                }
                if (User.teamType != chessBoard.currentTurnTeamType) {
                    System.out.println("不是你的出战回合");
                    return;
                }
                if(User.gameMode != GameModes.GOWAR){
                    System.out.println("不在出战模式");
                    return;
                }
                if (chessBoard.goWarRole == null) {
                    System.out.println("没有出战角色");
                    return;
                }
                if (chessBoard.onCoord == null) {
                    System.out.println("点击不在棋盘上");
                    return;
                }
                if(chessBoard.viewMode!=0){
                    User.sendPromptBoxA("需要在第一层棋盘上放置");
                    return;
                }

                if(chessBoard.currentTurnTeamType == TeamType.RED){
                    if(chessBoard.onCoord.x>=5){
                        User.sendPromptBoxA("只能放置在你的出战区域");
                        return;
                    }
                }
                else if(chessBoard.onCoord.x<=7){
                    User.sendPromptBoxA("只能放置在你的出战区域");
                    return;
                }

                if(chessBoard.clickCell.roleChess != null){
                    User.sendPromptBoxA("此格子已被其它角色占据");
                    return;
                }

                if(!chessBoard.clickCell.terrains.getFirst().allowsMovement(chessBoard.goWarRole.movementType)){
                    User.sendPromptBoxA("此地形该角色不能占据");
                    return;
                }
                System.out.println("发送请求放置角色事件");


                if (!User.isOnline) {
                    event.allowWork = false;
                    event.hasWork = true;
                    chessBoard.post(new Event().putGoWarRoleRE(chessBoard.clickCell.coord));
                }




            }
        } ;



        Subscriber<PutGoWarRoleRE> s2 = new Subscriber<PutGoWarRoleRE>() {
            @Override
            public void handleEvent(PutGoWarRoleRE event) {

                ChessBoard chessBoard = User.chessBoard;
                if(!event.allowWork){
                    System.out.println("事件禁止工作");
                    return;
                }
                System.out.println("放置成功");


                RoleChess roleChess = chessBoard.goWarRole;

                chessBoard.getCell(event.coord).roleChess = roleChess;
                roleChess.index = chessBoard.goWarIndex;
                roleChess.animationManager.addLockAnimations(new PutRoleA(roleChess),null);
                roleChess.coord.setCoord(event.coord);
                roleChess.skills.forEach(BottomSkill::init);

                chessBoard.goWarRole = null;
                chessBoard.getCurrentTurnTeam().goWarSTA-=1;


                chessBoard.post(new Event().putGoWarRoleDE(event));


            }
        };



        add(s1);
        add(s2);


    }
}
