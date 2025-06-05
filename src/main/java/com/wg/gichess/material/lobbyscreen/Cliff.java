package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;

public class Cliff extends Movable {

    public Cliff(int x, int y,boolean isFirst) {
        super("textures/lobbyscreen/cliff.png", x, y,isFirst);
    }



    @Override
    public void render() {

        int widthh = (int)(width * 0.3);
        int heightt = widthh;

        difX =  width/2 - mouseX;
        difY = height/2 - mouseY;

        difX/=20;
        difY/=20;

        poseStack.pushPose();
        poseStack.translate(difX,difY,0);

        guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                x, y,
                0, 0,
                widthh,heightt,
                widthh,heightt);

        poseStack.popPose();
    }
}
