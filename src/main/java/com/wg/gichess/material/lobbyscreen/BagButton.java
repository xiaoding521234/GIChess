package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.Movable;
import com.wg.gichess.screen.LobbyScreen;
import com.wg.gichess.screen.bagscrren.RoleScreen;
import net.minecraft.client.Minecraft;

public class BagButton extends Movable {
    public BagButton(int x, int y, boolean isFirst) {
        super("textures/lobbyscreen/bagbutton.png", x, y, isFirst);
        this.vX = -160;
        setSize( width/10 );
    }

    @Override
    public void render() {


        if(isFirst){
            accumulateX(delta);
            if(x <= width * 0.9){
                isFirst = false;
                x= (int) (width * 0.9);
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


        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0 ,
                0,0,
                widthh,heightt,
                widthh,heightt);

        poseStack.popPose();
    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            Minecraft.getInstance().setScreen(new RoleScreen(true,1,new LobbyScreen(true)));
            return true;
        }
        return false;
    }
}
