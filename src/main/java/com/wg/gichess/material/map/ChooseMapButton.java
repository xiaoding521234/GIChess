package com.wg.gichess.material.map;

import com.wg.gichess.material.Material;

public class ChooseMapButton extends Material {

    public Map map;
    public ChooseMapButton( int x, int y,Map map) {
        super("textures/map/choosemapbutton.png", x, y);
        setSize((int) (width*0.04), (int) (width*0.04));
        this.map = map;
    }


    @Override
    public void render() {

        poseStack.pushPose();
        {

            if (isMouseOn()) {
                poseStack.translate(widthh / 2f, heightt / 2f, 0);
                poseStack.scale(1.2f, 1.2f, 1);
                poseStack.translate(-widthh / 2f, -heightt / 2f, 0);
            }


            guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                    0, 0,
                    0, 0,
                    widthh, heightt,
                    widthh, heightt);
        }
        poseStack.popPose();



    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            Map.mode =Map.CHOOSE;
            return true;
        }
        return false;
    }
}
