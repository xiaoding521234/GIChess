package com.wg.gichess.material.bagscreen;

import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.Material;

public class SkinButton extends Material{

    public Material buttonShine = new Material("textures/bagscreen/skinbuttonshine.png");
    public RoleChess roleChess;

    public SkinButton(int x, int y, RoleChess roleChess) {
        super("textures/bagscreen/skinbutton.png", x, y);
        this.widthh = (int) (width * 0.035);
        this.heightt = widthh;
        this.buttonShine.setSize(this.widthh,this.heightt);
        this.roleChess = roleChess;
    }

    @Override
    public void render() {

        poseStack.pushPose();
        {
        poseStack.translate(this.x,this.y,0);
            guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                    0, 0,
                    0, 0,
                    widthh, heightt,
                    widthh, heightt);


            if (isMouseOn()) {
                buttonShine.render();
            }
        }
        poseStack.popPose();
    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            ++roleChess.skin;
            if(roleChess.skin >= roleChess.skins.length){
                roleChess.skin = 0;
            }
            return true;
        }
        return false;

    }
}
