package com.wg.gichess.material.battlelobbyscreen.LocalPvPPS;

import com.wg.gichess.User;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.material.Material;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.MapScreen;
import net.minecraft.client.Minecraft;

public class ViewModeButton extends Material {


    public ViewModeButton(int x, int y) {
        super("textures/battlelobbyscreen/viewmodebutton.png", x, y);
        setSize((int)(width * 0.04));
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


        render00();

        poseStack.popPose();
    }

    @Override
    public boolean click() {
        if (isMouseOn()){
            User.chessBoard.changeViewMode();
            return true;
        }
        return false;
    }
}
