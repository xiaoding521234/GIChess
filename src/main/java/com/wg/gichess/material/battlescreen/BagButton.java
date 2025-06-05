package com.wg.gichess.material.battlescreen;

import com.wg.gichess.material.Movable;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.bagscrren.RoleScreen;
import net.minecraft.client.Minecraft;

public class BagButton extends Movable {
    public BagButton(int x, int y, boolean isFirst) {
        super("textures/battlescreen/bagbutton.png", x, y, isFirst);
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
            Minecraft.getInstance().setScreen(new RoleScreen(true,1, (FastScreen) Minecraft.getInstance().screen));
            return true;
        }
        return false;
    }
}
