package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;
import com.wg.gichess.screen.LobbyScreen;
import com.wg.gichess.screen.bagscrren.RoleScreen;
import com.wg.gichess.screen.tutorialscreen.FundamentalsScreen;
import net.minecraft.client.Minecraft;

public class TutorialButton extends Movable {
    public TutorialButton(int x, int y, boolean isFirst) {
        super("textures/lobbyscreen/tutorialbutton.png", x, y, isFirst);
        this.vX = -160;
    }

    @Override
    public void render() {

        widthh = width/10;
        heightt = width/10;

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
            Minecraft.getInstance().setScreen(new FundamentalsScreen(true,1,new LobbyScreen(true)));
            return true;
        }
        return false;
    }
}
