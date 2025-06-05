package com.wg.gichess.material.animation.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.User;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.Material;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class PromptBoxA extends Animation {


    private static final int BORDER_COLOR = 0xFFF8D070; // 原神风格金色边框
    private static final int BG_COLOR = 0x99000000;    // 半透明黑底
    private static final int TEXT_COLOR = 0xFFFFFFFF;  // 白色文字

    private final String text;

    public PromptBoxA(String text){
        this.text = text;

        totalTime = 2;
    }

    @Override
    public void renderAnimation() {

        currentTime = Math.min(currentTime + Material.delta / 20, totalTime);
        float progress = (float) (currentTime / totalTime);

        PoseStack poseStack = Material.poseStack;
        GuiGraphics guiGraphics = Material.guiGraphics;
        poseStack.pushPose();
        {
            // 动画阶段控制
            float alpha, offsetY;
            if (progress < 0.25f) {
                // 阶段1：淡入 + 下滑 (0~0.5秒)
                float stageProgress = progress / 0.25f;
                alpha = Mth.lerp(stageProgress, 0f, 1f);
                offsetY = Mth.lerp(easeOutCubic(stageProgress), -30f, 20f);
            } else if (progress < 0.75f) {
                // 阶段2：保持显示 (0.5~1.5秒)
                alpha = 1f;
                offsetY = 20f;
            } else {
                // 阶段3：淡出 (1.5~2秒)
                float stageProgress = (progress - 0.75f) / 0.25f;
                alpha = Mth.lerp(stageProgress, 1f, 0.05f);
                offsetY = 20f;
            }

            // 定位到屏幕顶部中央
            int screenX = Material.width / 2;
            poseStack.translate(screenX, 30 + offsetY, 500);

            // 渲染提示框
            renderGenshinStyleBox(guiGraphics,alpha);
        }
        poseStack.popPose();


        // 动画结束时触发回调
        if (currentTime >= totalTime) {
            OnComplete();
            animationManager.remove(this);

        }
    }

    private void renderGenshinStyleBox(GuiGraphics guiGraphics,float alpha) {
        int width = (int) (Material.width*0.6);
        int height = (int) (Material.height*0.1);
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        // 半透明背景
        guiGraphics.fill(-halfWidth, -halfHeight, halfWidth, halfHeight, getNewColor(BG_COLOR,alpha));
        // 金色边框（仅上下两条线）
        int borderSize = 2;
        guiGraphics.fill(-halfWidth, -halfHeight, halfWidth, -halfHeight + borderSize, getNewColor(BORDER_COLOR,alpha)); // 上边框
        guiGraphics.fill(-halfWidth, halfHeight - borderSize, halfWidth, halfHeight, getNewColor(BORDER_COLOR,alpha));  // 下边框

        // 文字（居中+阴影）

        guiGraphics.drawString(
                Material.font,
                text,
                -Material.font.width(text) / 2,
                -Material.font.lineHeight / 2,
                getNewColor(TEXT_COLOR,alpha),
                true // 启用阴影
        );


    }

    // 缓动函数：平滑减速
    private float easeOutCubic(float x) {
        return 1 - (float) Math.pow(1 - x, 3);
    }


}
