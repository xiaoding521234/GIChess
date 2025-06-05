package com.wg.gichess.material.map;

import com.wg.gichess.User;
import com.wg.gichess.material.Movable;

public class LobbyBack extends Movable {
    public int i =-1;

    public LobbyBack(boolean isFirst) {
        super("textures/map/"+ User.positionType.regionType.id +  "/" + User.positionType.materialID + "back.png",isFirst);
        if(User.positionType == PositionType.STARTINGPOINT){
            setSize((int)(width * 1.2),(int)(height * 1.2));
        }
        else{
            setSize((int)(width * 1.1), (int) (height*1.1));
            i=1;
        }
    }



    @Override
    public void render() {

        difX =  width/2 - mouseX;
        difY = height/2 - mouseY;

        difX/=15;
        difY/=15;

        poseStack.pushPose();
        {
            poseStack.translate(i*difX + (width - widthh) / 2f, i*difY + (height - heightt) / 2f, 0);
            render00();
        }
        poseStack.popPose();

    }
}
