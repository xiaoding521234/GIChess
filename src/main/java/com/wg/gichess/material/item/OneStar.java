package com.wg.gichess.material.item;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.bagscreen.RoleMList;

public class OneStar extends Material {
    public OneStar() {
        super("textures/item/1star.png");
    }

    @Override
    public void render() {

        widthh = width/15;
        heightt = (int) (widthh * 1.36);

        poseStack.pushPose();
        if(isMouseOn() && RoleMList.isMouseOn){
            poseStack.translate(heightt/2f+x,heightt/2f+y,0);
            poseStack.scale(1.2f,1.2f,1);
            poseStack.translate(-heightt/2f,-heightt/2f,0);
        }else{
            poseStack.translate(x,y,0);
        }

        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0 ,
                0,0,
                widthh,heightt,
                widthh,heightt);

        poseStack.popPose();
    }
}
