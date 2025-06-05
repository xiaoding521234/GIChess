package com.wg.gichess.material.bagscreen;

import com.wg.gichess.material.Material;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.LobbyScreen;
import net.minecraft.client.Minecraft;

public class CloseButton extends Material {
    public FastScreen lastScreen;
    public CloseButton(int x, int y,FastScreen lastScreen) {
        super("textures/bagscreen/closebutton.png", x, y);
        setSize((int)(width * 0.04),(int)(width * 0.04));
        this.lastScreen = lastScreen;
    }

    @Override
    public void render() {

        poseStack.pushPose();

        if(isMouseOn()){
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
        if (isMouseOn()){
            Minecraft.getInstance().setScreen(lastScreen);
            return true;
        }
        return false;
    }
}
