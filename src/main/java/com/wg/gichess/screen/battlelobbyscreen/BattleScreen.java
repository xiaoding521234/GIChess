package com.wg.gichess.screen.battlelobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.material.battlescreen.*;
import com.wg.gichess.material.map.LobbyBack;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.LobbyScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class BattleScreen extends FastScreen {

    ChessBoard chessBoard;

    public BattleScreen( boolean isFirst) {
        super(Component.literal("战斗界面"), isFirst);
        this.chessBoard = User.chessBoard;
    }


    @Override
    protected void init() {
        updateScreen();
        materials.clear();

        backGround = new LobbyBack(true);


        materials.add(new SettingButton((int) (width * 0.01), (int) (height * -0.2),true));
        materials.add(new ViewModeButton((int) (width * 0.06), (int) (height * -0.3),true ));

        materials.add(new TurnOverButton((int) (width * 0.475), (int) (height *0.01)));

        materials.add(new TutorialButton((int) (width * (1-0.1)), (int) (height* -0.3),true ));
        materials.add(new BagButton((int) (width * (1-0.05)), (int) (height* -0.2),true ));

        chessBoard.initUpdate();

        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        updateGui(guiGraphics,delta);
        backGround.render();

        chessBoard.render();
        super.render(guiGraphics, mouseX, mouseY, delta);

        fadeIn();
        User.mouseCursor.render();


    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(User.clockClick){
            return false;
        }
        chessBoard.click(button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        super.mouseMoved(mouseX, mouseY);

        chessBoard.mouseMoved();

    }

    @Override
    public void onClose() {
        if(User.clockClick){
            User.sendPromptBoxA("动画结束才可退出");
            return;
        }
        if(User.gameMode == GameModes.SKILL){
            User.sendPromptBoxA("正在使用技能");
            return;
        }
        Minecraft.getInstance().setScreen(new LobbyScreen(true));
    }
}
