package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;

public class SettingButton extends Movable {
    public SettingButton(int x, int y, boolean isFirst) {
        super("textures/lobbyscreen/settingbutton.png", x, y, isFirst);
        this.fCount = 50;
        this.vF = 25;
    }

    @Override
    public void render() {

        widthh = (int) (width * 0.05) * fCount;
        heightt = (int) (width * 0.05);

        if(f >= fCount){
            f = 0;
        }

        poseStack.pushPose();
        {
            if (isMouseOn()) {
                poseStack.translate(heightt / 2f + x, heightt / 2f + y, 0);
                poseStack.scale(1.2f, 1.2f, 1);
                poseStack.translate(-heightt / 2f, -heightt / 2f, 0);
            } else {
                poseStack.translate(x, y, 0);
            }

            guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                    0, 0,
                    f * heightt, 0,
                    heightt, heightt,
                    widthh, heightt);

            accumulateF(delta);
        }
        poseStack.popPose();
    }

    @Override
    public boolean isMouseOn() {
        return mouseX >= x && mouseX <= x + heightt && mouseY >= y && mouseY <= y + heightt;
    }
}
