package com.wg.gichess.screen;

import com.wg.gichess.User;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.lobbyscreen.*;
import com.wg.gichess.material.map.PositionType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OpenScreen extends FastScreen{
    private static final float FADE_IN_DURATION = 1.5f;  // 白→透明时长
    private static final float HOLD_DURATION = 1f;     // 透明停留时长
    private static final float FADE_OUT_DURATION = 1.5f; // 透明→白时长
    private float animTimer = FADE_IN_DURATION;

    public boolean fastOpen = false;

    public OpenScreen(boolean isFirst,boolean fastOpen) {
        super(Component.literal("启动界面"),isFirst);
        this.fastOpen = fastOpen;

    }

    @Override
    protected void init() {

        long window = Minecraft.getInstance().getWindow().getWindow();
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);

        materials.clear();
        updateScreen();

        materials.add(new Material("textures/other/gicopen.png",0,0));
        materials.getFirst().setSize(Material.width,Material.height);


        if(User.positionType == PositionType.STARTINGPOINT){
            ExecutorService executor = Executors.newFixedThreadPool(1);

            Thread t1 = new Thread(() -> {
                Material material1 = new Cliff(0,0,false);
                Material material2 = new Road(0,0,false);
                Material material3 = new Aether(0,0,false);
                Material material4 = new Stone(0,0,false);
                Material material5 = new Wood(0,0,false);
            });
            executor.execute(t1);
            executor.shutdown();
        }




        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {


        updateGui(guiGraphics,delta);

        super.render(guiGraphics, mouseX, mouseY, delta);

        animTimer = Math.max(animTimer - delta/20, -FADE_OUT_DURATION - HOLD_DURATION);

        float alpha = calculateSinAlpha();
        int color = ((int)(alpha * 255) << 24) | 0xFFFFFF;
        guiGraphics.fill(0, 0, width, height, color);

        if (fastOpen ||  animTimer <= -FADE_OUT_DURATION - HOLD_DURATION ) {
            Minecraft.getInstance().setScreen(new LobbyScreen(true));
        }

        User.mouseCursor.render();


    }

    @Override
    public void onClose() {
    }

    private float calculateSinAlpha() {
        if (animTimer >= 0) {
            // 第一阶段：白→透明（animTimer从FADE_IN_DURATION降到0）
            float progress = animTimer / FADE_IN_DURATION;
            return (float) Math.sin(progress * Math.PI / 2);
        }
        else if (animTimer >= -HOLD_DURATION) {
            // 第二阶段：完全透明
            return 0f;
        }
        else {
            // 第三阶段：透明→白（animTimer从-HOLD_DURATION降到-FADE_OUT_DURATION-HOLD_DURATION）
            float progress = (animTimer + HOLD_DURATION) / -FADE_OUT_DURATION;
            return (float) Math.sin(progress * Math.PI / 2);
        }
    }
}
