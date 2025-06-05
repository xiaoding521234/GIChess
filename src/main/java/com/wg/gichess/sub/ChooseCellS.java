package com.wg.gichess.sub;

import com.wg.gichess.Subscriber;
import com.wg.gichess.Subscribers;
import com.wg.gichess.User;
import com.wg.gichess.chessboard.Cell;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.event.ClickE;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.animation.rolechess.ChooseCellA;

public class ChooseCellS extends Subscribers {

    public Animation lastAnimation;


    public ChooseCellS() {

        super(User.chessBoard);


        Subscriber<ClickE> s1 = new Subscriber<ClickE>() {
            @Override
            public void handleEvent(ClickE event) {

                ChessBoard chessBoard = User.chessBoard;


                if(!event.allowWork){
                    System.out.println("事件禁止工作");
                    return;
                }

                if (chessBoard.onCoord == null) {
                    System.out.println("点击不在棋盘上");
                    return;
                }

                if (chessBoard.goWarRole != null) {
                    System.out.println("正在出战角色");
                    return;
                }

                if(chessBoard.viewMode!=0){
                    User.sendPromptBoxA("需要在第一层棋盘上选中");
                    return;
                }

                if(User.gameMode == GameModes.SKILL){
                    System.out.println("使用技能时不能选中格子");
                    return;
                }

                event.allowWork = false;
                event.hasWork = true;

                Cell cell = chessBoard.getCell(chessBoard.onCoord);
                if(lastAnimation!=null){
                    lastAnimation.remove();
                }
                lastAnimation = new ChooseCellA();
                cell.animationManager.addAnimations(lastAnimation,null);


                chessBoard.chooseCell = cell;
                chessBoard.time =0;








            }
        };

        add(s1);



    }


}
