package com.wg.gichess.screen;


import com.wg.gichess.User;

import com.wg.gichess.material.lobbyscreen.*;
import com.wg.gichess.material.lobbyscreen.MapButton;
import com.wg.gichess.material.map.Lobby;
import com.wg.gichess.material.map.PositionType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class LobbyScreen extends FastScreen {


    public LobbyScreen(boolean isFirst) {
        super(Component.literal("大厅界面"),isFirst);
    }

    @Override
    protected void init() {
        materials.clear();
        updateScreen();
        //背景素材
        backGround = new Lobby(true);
        if(User.positionType == PositionType.STARTINGPOINT){
            materials.add(new Cliff((int) (width * -0.05), (int) (height * -0.05),true));
            materials.add(new Road(0,0,true));
            materials.add(new Aether(10,15,true));
            materials.add(new Stone((int) (width * -0.6), (int) (height * 0.7),true));
            materials.add(new Wood((int) (width * 0.47), (int) (height * 0.43),true));
        }


        //交互素材
        materials.add(new WishButton((int) (width * 1.0) ,(int) (height * 0.1),true));
        materials.add(new BattleLobbyButton((int) (width * 1.05) ,(int) (height * 0.25),true));
        materials.add(new BagButton((int) (width * 1.1) ,(int) (height * 0.4),true));
        materials.add(new TutorialButton((int) (width * 1.15),(int) (height * 0.55),true));
        materials.add(new MapButton((int) (width * 0.06) ,(int) (height * 0.05)));
        materials.add(new SettingButton((int) (width*0.01), (int) (height*0.01),true));
        materials.add(new TaskButton((int) (width*0.04), (int) (height*0.3)));




        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        updateGui(guiGraphics,delta);

        backGround.render();
        super.render(guiGraphics, mouseX, mouseY, delta);
        if(delta/20<0.04){
            fadeProgress = Math.min(fadeProgress + delta * 0.05f, 1.0f);
            if (fadeProgress < 1.0f) {
                int alpha = (int) ((1 - fadeProgress) * 255); // 255 → 0

                int color = (alpha << 24);
                guiGraphics.fill(0, 0, width, height, color);
            }
        }
        User.mouseCursor.render();


    }
//int color = (alpha << 24) | 0xFFFFFF;

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(button==0){
            return super.mouseClicked(mouseX, mouseY, button);
        }
        return false;

    }

    @Override
    public void onClose() {
        long window = Minecraft.getInstance().getWindow().getWindow();
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        super.onClose();
    }
}
