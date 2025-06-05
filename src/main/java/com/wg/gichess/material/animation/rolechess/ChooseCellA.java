package com.wg.gichess.material.animation.rolechess;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.User;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.Material;
import net.minecraft.client.gui.GuiGraphics;

public class ChooseCellA extends Animation {
    private static final int GOLD_COLOR = 0xFFF8D070; // 金色ARGB
    private static final float CORNER_THICKNESS = 1;    // 拐角线条粗细
    private static final float CORNER_LENGTH = 0.2f;      // 拐角长度

    private int cellSize;


    public ChooseCellA() {
        this.totalTime = 0.3;
    }

    @Override
    public void renderAnimation() {
        currentTime = Math.min(currentTime + Material.delta / 20, totalTime);
        float progress = (float) (currentTime / totalTime);

        this.cellSize = User.chessBoard.cellSize;

        // 弹性缩放参数
        float scale = calculateElasticScale(progress);

        PoseStack poseStack = Material.poseStack;
        GuiGraphics guiGraphics = Material.guiGraphics;
        poseStack.pushPose();
        {
            // 绘制四个金色拐角
            renderGoldenCorners(guiGraphics,poseStack,scale);
        }
        poseStack.popPose();


    }

    private float calculateElasticScale(float progress) {

        if (progress < 0.6f) return 1.2f - (progress / 0.6f) * 0.4f;
        return 0.8f + ((progress - 0.6f) / 0.4f) * 0.2f;
    }


    private void renderGoldenCorners(GuiGraphics guiGraphics,PoseStack poseStack,float scale) {
        int adjustedThickness = (int) CORNER_THICKNESS;
        int adjustedLength = (int)(CORNER_LENGTH * cellSize);
        int color = GOLD_COLOR;

        poseStack.translate(cellSize/2f,cellSize/2f,300);
        poseStack.scale(scale,scale,1f);
        poseStack.translate(-cellSize/2f,-cellSize/2f,0);
        // 左上角（正常L型）
        guiGraphics.fill(0, 0, adjustedLength, adjustedThickness, color); // 横线
        guiGraphics.fill(0, adjustedThickness, adjustedThickness, adjustedLength, color); // 竖线

        // 右上角（旋转90度）
        guiGraphics.fill(cellSize - adjustedLength, 0, cellSize, adjustedThickness, color);
        guiGraphics.fill(cellSize - adjustedThickness, adjustedThickness, cellSize, adjustedLength, color);

        // 右下角（旋转180度）
        guiGraphics.fill(cellSize - adjustedLength, cellSize - adjustedThickness, cellSize, cellSize, color);
        guiGraphics.fill(cellSize - adjustedThickness, cellSize - adjustedLength, cellSize, cellSize - adjustedThickness, color);

        // 左下角（旋转270度）
        guiGraphics.fill(0, cellSize - adjustedThickness, adjustedLength, cellSize, color);
        guiGraphics.fill(0, cellSize - adjustedLength, adjustedThickness, cellSize - adjustedThickness, color);
    }

}

