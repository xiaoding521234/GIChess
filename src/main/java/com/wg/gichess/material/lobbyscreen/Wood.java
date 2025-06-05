package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;

public class Wood extends Movable {

    public Wood(int x, int y,boolean isFirst) {
        super("textures/lobbyscreen/wood.png", x, y, isFirst);
    }

    @Override
    public void render() {

        int widthh = (int) (width * 1.1);
        int heightt = widthh;

        difX =  width/2 - mouseX;
        difY = height/2 - mouseY;

        difX/=6;
        difY/=6;

        poseStack.pushPose();
        poseStack.translate(difX,difY,0);

        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x,y,
                0,0,
                widthh,heightt,
                widthh,heightt);

        poseStack.popPose();


    }
}
