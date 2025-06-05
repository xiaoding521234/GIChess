package com.wg.gichess.screen.battlelobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.battlelobbyscreen.LocalPvPPS.*;
import com.wg.gichess.material.map.LobbyBack;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.material.tutorialscreen.SliderWidget;
import com.wg.gichess.screen.FastScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.List;

public class LocalPvPPS extends FastScreen {



    public LocalPvPPS(boolean isFirst) {
        super(Component.literal("同屏对战准备界面"), isFirst);
    }


    @Override
    protected void init() {
        updateScreen();
        materials.clear();

        backGround = new LobbyBack(true);

        User.chessBoard = PositionType.getCB(User.positionType);
        User.chessBoard.initUpdate();

        float w = width*0.05f;
        float h = height*0.1f;
        float s = height*0.06f;

        List<SliderWidget> sliderWidgets = Arrays.asList(
                new WarRoleNumSW(w, h + 0 * s),
                new CelestialJudgmentRoundSW(w, h + 1 * s),
                new ConstellationLockNumSW(w, h + 2 * s)
        );

        materials.addAll(sliderWidgets);
        materials.add(new ResetButton((int)(width*0.05),(int)(height*0.9),sliderWidgets));

        materials.add(new CloseButton((int) (width*0.95), (int) (height*0.01)));
        materials.add(new MapButton((int) (width*0.6), (int) (height*0.48),this));
        materials.add(new ViewModeButton((int) (width*0.66), (int) (height*0.48)));

        materials.add(new StartButton((int)(width*0.82),(int)(height*0.9)));





        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        updateGui(guiGraphics,delta);

        backGround.render();
        super.render(guiGraphics, mouseX, mouseY, delta);
        User.chessBoard.renderPreview(width*0.6f,height*0.1f);
        fadeIn();
        User.mouseCursor.render();





    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(new BattleLobbyScreen(true));
    }

}
