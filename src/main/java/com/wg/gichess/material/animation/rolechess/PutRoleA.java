package com.wg.gichess.material.animation.rolechess;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.wg.gichess.User;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.Material;


public class PutRoleA extends Animation {
    RoleChess roleChess;


    // 动画阶段参数
    private final double pauseDuration = 0.2; // 初始停顿时间比例
    private final double dropDuration = 0.6;   // 下落阶段时间比例
    private final double bounceDuration = 0.2; // 弹跳阶段时间比例

    // 动画效果参数
    private final float maxScale = 1.5f;      // 最大缩放（初始大小）
    private final float minScale = 0.9f;      // 最小缩放（弹跳时）
    private final float rotationAngle = 270f; // 旋转总角度
    private final float maxAlpha = 0.3f;      // 初始透明度



    public PutRoleA(RoleChess roleChess ){
        this.roleChess = roleChess;

        totalTime = 1.2;
    }


    @Override
    public void renderAnimation() {
        currentTime = Math.min(currentTime + Material.delta/20,totalTime);
        double progress = currentTime / totalTime;
        roleChess.shouldRender = false;
        User.clockClick = true;

        int cellSize = User.chessBoard.cellSize;
        int roleSize = User.chessBoard.roleSize;

        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            // 初始停顿阶段（只旋转和半透明）
            if (progress < pauseDuration) {
                float pauseProgress = (float) (progress / pauseDuration);
                float alpha = maxAlpha + (1 - maxAlpha) * pauseProgress;
                float rotation = rotationAngle * pauseProgress;
                float scale = maxScale;

                applyTransformations(poseStack,cellSize ,alpha, rotation, scale);
            }
            // 下落阶段
            else if (progress < pauseDuration + dropDuration) {
                float dropProgress = (float) ((progress - pauseDuration) / dropDuration);
                // 使用三次缓出函数使下落更自然
                dropProgress = (float) easeOutCubic(dropProgress);

                float alpha = 1f; // 完全显示
                float rotation = rotationAngle + 90f * dropProgress; // 继续旋转
                float scale = maxScale - (maxScale - 1f) * dropProgress; // 缩放到正常大小

                applyTransformations(poseStack, cellSize,alpha, rotation, scale);
            }
            // 弹跳阶段
            else {
                float bounceProgress = (float) ((progress - pauseDuration - dropDuration) / bounceDuration);
                // 使用正弦函数模拟弹跳
                float bounceFactor = (float) Math.sin(bounceProgress * Math.PI);

                float alpha = 1f;
                float rotation = rotationAngle + 90f + 15f * bounceFactor; // 轻微晃动
                float scale = 1f - (1f - minScale) * bounceFactor; // 轻微缩放模拟弹跳

                applyTransformations(poseStack,cellSize, alpha, rotation, scale);
            }

            // 渲染棋子
            roleChess.renderChess(cellSize, roleSize, false);

        }
        poseStack.popPose();

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        if(currentTime == totalTime){
            roleChess.shouldRender = true;
            User.clockClick = false;
            OnComplete();
            animationManager.remove(this);
        }
    }

    private void applyTransformations(PoseStack poseStack, int cellSize,float alpha, float rotation, float scale) {
        // 中心点变换（确保缩放和旋转以棋子中心为准）
        poseStack.translate(cellSize/2f, cellSize/2f, 0);
        poseStack.scale(scale, scale, 1f);

        // 计算最终旋转角度（确保动画结束时归零）
        float finalRotation = calculateFinalRotation(rotation);
        poseStack.mulPose(Axis.ZP.rotationDegrees(finalRotation));

        poseStack.translate(-cellSize/2f, -cellSize/2f, 0);

        // 设置透明度
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, alpha);
    }

    private float calculateFinalRotation(float progressRotation) {
        // 动画结束时（currentTime >= totalTime）返回0，否则返回当前旋转角度
        if (currentTime >= totalTime) {
            return 0f;
        }
        return progressRotation;
    }

    // 三次缓出函数
    private double easeOutCubic(double t) {
        return 1 - Math.pow(1 - t, 3);
    }




}
