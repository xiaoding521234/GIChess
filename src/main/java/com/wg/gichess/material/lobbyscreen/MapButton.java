package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.Material;
import com.wg.gichess.screen.LobbyScreen;
import com.wg.gichess.screen.MapScreen;
import net.minecraft.client.Minecraft;

public class MapButton extends Material {
    public MapButton(int x, int y) {
        super("textures/map/"+ User.positionType.regionType.id +  "/"+ User.positionType.materialID + "map.png", x, y);
        this.widthh = (int)(width * 0.15);
        this.heightt = widthh;
    }

    @Override
    public void render() {

        poseStack.pushPose();

        if(isMouseOn()){
            poseStack.translate(widthh/2f+x,heightt/2f+y,0);
            poseStack.scale(1.1f,1.1f,1);
            poseStack.translate(-widthh/2f,-heightt/2f,0);
        }
        else{
            poseStack.translate(x,y,0);
        }


        this.render00();

        poseStack.popPose();
    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            User.lastScreen = new LobbyScreen(true);
            Minecraft.getInstance().setScreen(new MapScreen(User.positionType.regionType,true));
            return true;
        }
        return false;
    }
}
