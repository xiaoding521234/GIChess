package com.wg.gichess.material.battlelobbyscreen;

import com.wg.gichess.material.Material;
import com.wg.gichess.screen.battlelobbyscreen.BattleLobbyScreen;

public class RButton extends Material {
    BattleLobbyScreen screen;
    Material transition = new Material("textures/battlelobbyscreen/transition.png");

    boolean isInTransition = false;
    boolean hasToggleMode = false;
    float transitionStartTime = 0f;


    public RButton(int x, int y, BattleLobbyScreen screen) {
        super("textures/battlelobbyscreen/rbutton.png", x, y);
        setSize((int)(width * 0.04),(int)(width * 0.04));
        this.screen = screen;
        transition.setSize(width *2,height);
    }

    @Override
    public void render() {

        poseStack.pushPose();
        {

            if (isMouseOn()) {
                poseStack.translate(widthh / 2f + x, heightt / 2f + y, 0);
                poseStack.scale(1.1f, 1.1f, 1);
                poseStack.translate(-widthh / 2f, -heightt / 2f, 0);
            } else {
                poseStack.translate(x, y, 0);
            }


            guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                    0, 0,
                    0, 0,
                    widthh, heightt,
                    widthh, heightt);
        }
        poseStack.popPose();

        if(isInTransition){
            poseStack.pushPose();
            {
                poseStack.translate(width - transitionStartTime*width*4 ,0,1000);
                transition.render();

                transitionStartTime += delta/20;

                if(transitionStartTime >= 0.5 && !hasToggleMode){
                    screen.mode = screen.mode < BattleLobbyScreen.maxMode ? screen.mode +1 : 1;
                    screen.addMaterialsByMode(screen.materialsByMode,screen.mode);
                    hasToggleMode = true;
                }
                if(transitionStartTime >= 1){
                    isInTransition = false;
                    hasToggleMode = false;
                    transitionStartTime = 0;
                }


            }
            poseStack.popPose();

        }
    }

    @Override
    public boolean click() {
        if (isMouseOn() && !isInTransition){
            isInTransition = true;
            return true;
        }
        return false;
    }
}
