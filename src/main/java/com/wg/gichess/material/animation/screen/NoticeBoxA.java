package com.wg.gichess.material.animation.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.wg.gichess.User;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.Material;
import net.minecraft.client.gui.GuiGraphics;

public class NoticeBoxA extends Animation {

    private static final int BG_COLOR = 0x99000000;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private final String text;


    public NoticeBoxA(String text){
        this.text = text;

        totalTime = 1;
    }

    @Override
    public void renderAnimation() {

        currentTime = Math.min(currentTime + Material.delta / 20, totalTime);
        float progress = (float) (currentTime / totalTime);
        User.clockClick = true;

        PoseStack poseStack = Material.poseStack;
        GuiGraphics guiGraphics = Material.guiGraphics;
        poseStack.pushPose();
        {

            int width = Material.width;
            int height = Material.height;
            // 定位到屏幕中心
            int centerX = width / 2;
            int centerY = height / 2;
            poseStack.translate(centerX, centerY, 500);

            // 三阶段动画控制
            float rotationAngle;
            if (progress < 0.3f) {
                // 阶段1：展开 (0-30%时间)
                rotationAngle = 90 * (1 - progress / 0.3f);
            } else if (progress < 0.7f) {
                // 阶段2：停留 (30-70%时间)
                rotationAngle = 0;
            } else {
                // 阶段3：收回 (70-100%时间)
                rotationAngle = 90 * ((progress - 0.7f) / 0.3f);
            }
            poseStack.mulPose(Axis.XP.rotationDegrees(rotationAngle));

            // 渲染面板
            guiGraphics.fill(
                    -width/2, -height/10,
                    width/2, height/10,
                    BG_COLOR
            );

            // 文字渲染
            guiGraphics.drawString(
                    Material.font,
                    text,
                    -Material.font.width(text) / 2,
                    -Material.font.lineHeight / 2,
                    TEXT_COLOR,
                    true
            );
        }
        poseStack.popPose();



        if(currentTime == totalTime){
            User.clockClick = false;
            OnComplete();
            animationManager.remove(this);
        }



    }
}
