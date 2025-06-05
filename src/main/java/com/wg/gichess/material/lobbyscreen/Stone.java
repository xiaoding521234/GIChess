package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;

public class Stone extends Movable {

    public Stone(int x, int y,boolean isFirst) {
        super("textures/lobbyscreen/stone.png", x, y, isFirst);
    }

    @Override
    public void render() {


        difX =  width/2 - mouseX;
        difY = height/2 - mouseY;

        difX/=4;
        difY/=4;

        poseStack.pushPose();
        poseStack.translate(difX,difY,0);

        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x,y,
                0,0,
                width,height,
                width,height);

        poseStack.popPose();


    }
}
