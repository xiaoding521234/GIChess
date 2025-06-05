package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.Movable;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.screen.battlelobbyscreen.BattleLobbyScreen;
import net.minecraft.client.Minecraft;

public class BattleLobbyButton extends Movable {
    public BattleLobbyButton(int x, int y, boolean isFirst) {
        super("textures/lobbyscreen/battlelobbybutton.png", x, y,isFirst);
        this.vX = -160;
        widthh = (int) (width*0.1);
        heightt = widthh;
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
        if(isMouseOn()){
                User.chessBoard = PositionType.getCB(User.positionType.materialID);
            Minecraft.getInstance().setScreen(new BattleLobbyScreen(true));
            return true;
        }
        return false;

    }
}
