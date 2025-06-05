package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;

public class Aether extends Movable {

    public Aether(int x, int y,boolean isFirst) {
        super("textures/lobbyscreen/aether.png", x, y, isFirst);
    }

    @Override
    public void render() {

        difX =  width/2 - mouseX;
        difY = height/2 - mouseY;

        difX/=10;
        difY/=10;

        poseStack.pushPose();
        poseStack.translate(difX,difY,0);

        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x ,y + 10,
                0,0,
                width,height,
                width,height);

        poseStack.popPose();
    }
}
