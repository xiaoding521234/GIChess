package com.wg.gichess.material.battlelobbyscreen.LocalPvPPS;

import com.wg.gichess.User;
import com.wg.gichess.material.Button;
import com.wg.gichess.material.tutorialscreen.SliderWidget;
import com.wg.gichess.screen.battlelobbyscreen.BattleScreen;
import net.minecraft.client.Minecraft;

import java.util.List;

public class StartButton extends Button {

    public StartButton(int x, int y) {
        super(x, y, "开始");
    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            User.chessBoard.start();
            Minecraft.getInstance().setScreen(new BattleScreen(true));
            return true;
        }

        return false;
    }
}
