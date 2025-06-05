package com.wg.gichess.material.battlelobbyscreen;

import com.wg.gichess.material.Movable;

public class BlackDivider extends Movable {

    private static final float HOVER_RANGE = 0.2f; // 鼠标影响范围（宽度的 20%）
    private static final float MAX_OFFSET = 0.1f;  // 最大偏移量（宽度的 10%）
    private static final float ANIM_SPEED = 3f;

    public double currentOffset = 0f;
    public double targetOffset = 0f;

    private final int num;



    public BlackDivider (int x,int num ,boolean isFirst) {
        super("1", x, 0, isFirst);
        this.num = num;
    }

    @Override
    public void render() {

        boolean isMouseNearLeft = (mouseX >= x - width * HOVER_RANGE && mouseX < x);
        boolean isMouseNearRight = (mouseX > x && mouseX <= x + width * HOVER_RANGE);

        // 计算目标偏移量
        if (isMouseNearLeft && num != 1) {
            targetOffset = MAX_OFFSET * width; // 向右偏移
        } else if (isMouseNearRight && num != 5) {
            targetOffset = -MAX_OFFSET * width; // 向左偏移
        } else {
            targetOffset = 0f; // 无偏移
        }

        if(Math.abs(targetOffset - currentOffset) <= 2){
            targetOffset = currentOffset;
        }else {
            currentOffset += (targetOffset - currentOffset) * ANIM_SPEED * delta/20;
        }




        poseStack.pushPose();
        {

            poseStack.translate(currentOffset ,0,500);
            guiGraphics.fill(x,0,x+1,height,0xFF000000);

        }
        poseStack.popPose();




    }
}
