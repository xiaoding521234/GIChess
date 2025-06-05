package com.wg.gichess.material.bagscreen;



import com.wg.gichess.User;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.RoleFactoryV1;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.material.Button;
import com.wg.gichess.material.animation.screen.PromptBoxA;
import com.wg.gichess.screen.FastScreen;
import net.minecraft.client.Minecraft;

import java.lang.reflect.InvocationTargetException;


public class GoWarButton extends Button {
    public RoleChess roleChess;
    public FastScreen lastScreen;
    public GoWarButton(int x, int y, RoleChess roleChess, FastScreen lastScreen) {
        super(x, y, "出战");
        this.roleChess = roleChess;
        this.lastScreen = lastScreen;
    }

    @Override
    public boolean click() {

        if (!isMouseOn()) {
            return false;
        }

        ChessBoard chessBoard = User.chessBoard;
        if (User.teamType != chessBoard.currentTurnTeamType) {

            User.animationManager.addAnimations(new PromptBoxA("现在还不是你的回合"),null);

            return false;

        }
        if(chessBoard.getCurrentTurnTeam().goWarSTA==0){
            User.animationManager.addAnimations(new PromptBoxA("出战体力耗尽了"),null);
            return false;
        }

        int constellation = Math.min(roleChess.constellation, User.constellationLockNum);
        RoleChess role;
        try {
            role = RoleFactoryV1.create(roleChess.id,
                    roleChess.coord,
                    roleChess.level,
                    constellation,
                    roleChess.skin);
            role.setTeamType(chessBoard.currentTurnTeamType);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        if (chessBoard.goWarRole != null) {
            chessBoard.goWarRole = role;
            chessBoard.getCurrentTurnTeam().roles.set(chessBoard.goWarIndex, chessBoard.goWarRole);
        } else {
            chessBoard.goWarRole = role;
            chessBoard.getCurrentTurnTeam().roles.add(chessBoard.goWarRole);
        }

        Minecraft.getInstance().setScreen(lastScreen);
        return true;


    }
}
