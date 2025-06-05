package com.wg.gichess.material.bagscreen;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.Movable;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.LobbyScreen;
import com.wg.gichess.screen.bagscrren.RoleScreen;
import com.wg.gichess.screen.bagscrren.ValuableScreen;
import net.minecraft.client.Minecraft;

public class ValuableButton extends Movable {
    public int target;
    public int id=2;
    public FastScreen lastScreen;

    public ValuableButton( int x, int y,boolean isFirst,int target,FastScreen lastScreen) {
        super("textures/bagscreen/valuablebutton.png", x, y,isFirst);
        this.lastScreen = lastScreen;
        this.vY = 160;
        widthh = (int) (width*0.04);
        heightt = widthh;
        if(!isFirst){
            this.y=(int) (height*0.01);
        }
        this.target = target;
    }

    @Override
    public void render() {

        if(isFirst){
            accumulateY(delta);
            if(y >= (int) (height*0.01)){
                isFirst = false;
                y =  (int) (height*0.01);

            }
        }

        poseStack.pushPose();
        if(isMouseOn() || target==id){
            poseStack.translate(widthh/2f+x,heightt/2f+y,0);
            poseStack.scale(1.2f,1.2f,1);
            poseStack.translate(-widthh/2f,-heightt/2f,0);
        }
        else{
            poseStack.translate(x,y,0);
        }


        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0,
                0,0,
                widthh,heightt,
                widthh,heightt);

        poseStack.popPose();
    }

    @Override
    public boolean click() {
        if (isMouseOn() && target != id){
            Minecraft.getInstance().setScreen(new ValuableScreen(false,id,lastScreen));
            return true;
        }
        return false;
    }
}
