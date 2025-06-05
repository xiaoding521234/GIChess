package com.wg.gichess.material.battlescreen;

import com.wg.gichess.material.Movable;

public class SettingButton extends Movable {
    public SettingButton(int x, int y, boolean isFirst) {
        super("textures/battlescreen/settingbutton.png", x, y, isFirst);
        this.vY = 160;
        setSize((int)(width * 0.04));

    }

    @Override
    public void render() {

        if(isFirst){
            accumulateY(delta);
            if(y >= height * 0.01){
                isFirst = false;
                y= (int) (height * 0.01);
            }
        }

        poseStack.pushPose();
        if(isMouseOn()){
            poseStack.translate(heightt/2f+x,heightt/2f+y,0);
            poseStack.scale(1.2f,1.2f,1);
            poseStack.translate(-heightt/2f,-heightt/2f,0);
        }else{
            poseStack.translate(x,y,0);
        }


        render00();

        poseStack.popPose();
    }

    @Override
    public boolean click() {
        if(isMouseOn()){

            return true;
        }
        return false;
    }
}
