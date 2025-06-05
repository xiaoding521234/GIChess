package com.wg.gichess.material;

public class MouseCursor extends Material{
    public MouseCursor() {
        super("textures/mousecursor.png");

    }

    @Override
    public void render() {
        setSize((int) (width*0.02), (int) (width*0.02));
        poseStack.pushPose();
        {
            poseStack.translate(mouseX,mouseY,2000);
            render00();
        }
        poseStack.popPose();




    }
}
