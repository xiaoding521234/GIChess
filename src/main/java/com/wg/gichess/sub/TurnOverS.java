package com.wg.gichess.sub;

import com.wg.gichess.*;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.event.ActStartE;
import com.wg.gichess.event.TurnOverDE;
import com.wg.gichess.event.TurnOverRE;
import com.wg.gichess.event.TurnOverType;
import com.wg.gichess.material.animation.screen.NoticeBoxA;

public class TurnOverS extends Subscribers {

    boolean hasActStart = false;

    public TurnOverS() {
        super(User.chessBoard);

        Subscriber<TurnOverRE> s1 = new Subscriber<TurnOverRE>() {
            @Override
            public void handleEvent(TurnOverRE event) {

                ChessBoard chessBoard = User.chessBoard;
                if(!event.allowWork){
                    System.out.println("事件禁止工作");
                    return;
                }


                //出战回合结束
                if (event.turnOverType == TurnOverType.GOWAR) {


                    if (chessBoard.currentTurnTeamType == TeamType.BLUE) {

                        if(chessBoard.goWarIndex == User.goWarRoleNum-1){
                            System.out.println("行动回合正式开始");
                            User.gameMode = GameModes.DEFAULT;

                        }
                        chessBoard.goWarIndex += 1;
                        chessBoard.redTeam.goWarSTA = 1;
                        chessBoard.blueTeam.goWarSTA = 1;

                    }

                    chessBoard.currentTurnTeamType = chessBoard.currentTurnTeamType.switchTeam();
                    chessBoard.setTeamPriority(chessBoard.currentTurnTeamType);
                    if (!User.isOnline) {
                        User.teamType = chessBoard.currentTurnTeamType;
                    }

                }

                //行动回合结束
                else if (event.turnOverType == TurnOverType.DEFFAULT) {

                    if(chessBoard.currentTurnTeamType == TeamType.BLUE){
                        chessBoard.redTeam.STA = Math.min(chessBoard.redTeam.STA+1,1);
                        chessBoard.blueTeam.STA = Math.min(chessBoard.blueTeam.STA+1,1);
                        chessBoard.currentTurnNum += 1;
                    }


                    chessBoard.currentTurnTeamType = chessBoard.currentTurnTeamType.switchTeam();
                    if (!User.isOnline) {
                        User.teamType = chessBoard.currentTurnTeamType;
                    }

                }

                chessBoard.post(new Event().turnOverDE(event));


            }
        };

        Subscriber<TurnOverDE> s2 = new Subscriber<TurnOverDE>() {
            @Override
            public void handleEvent(TurnOverDE event) {

                ChessBoard chessBoard = User.chessBoard;
                if(!event.allowWork){
                    System.out.println("事件禁止工作");
                    return;
                }



                if(User.gameMode == GameModes.GOWAR){
                    chessBoard.animationManager.addLockAnimations(new NoticeBoxA(chessBoard.currentTurnTeamType.displayName + "方出战回合开始"),null);

                }
                else {
                    if(hasActStart){
                        chessBoard.animationManager.addLockAnimations(new NoticeBoxA(chessBoard.currentTurnTeamType.displayName + "方行动回合开始"),null);
                    } else {
                        hasActStart = true;
                        chessBoard.animationManager.addLockAnimations(new NoticeBoxA(chessBoard.currentTurnTeamType.displayName + "方行动回合开始"),
                                () ->
                                {
                                    User.chessBoard.post(new ActStartE());
                                }
                        );
                    }
                }


            }
        };




        add(s1);
        add(s2);


    }
}
