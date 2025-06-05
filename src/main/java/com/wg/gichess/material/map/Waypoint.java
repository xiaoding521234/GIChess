package com.wg.gichess.material.map;

import com.wg.gichess.User;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.lobbyscreen.MapButton;
import net.minecraft.client.Minecraft;
import org.joml.Vector2f;

public class Waypoint extends Material {
    public boolean isMouseOn = false;
    public boolean isSelected = false;
    public PositionType positionType;
    public MapSList mapSList;
    public String text;


    public Waypoint(float xf, float yf, PositionType positionType) {
        super("textures/map/waypoint.png");
        setXYf(xf,yf);
        setSize((int) (width*0.03), (int) (width*0.03));
        this.positionType = positionType;

    }

    public void renderOnMap(float scale, Vector2f vector2f){
        poseStack.pushPose();
        {
            poseStack.translate(xf,yf ,0);
            poseStack.scale(1/scale,1/scale,1f);
            isMouseOn = isMouseOnWithMap(scale,vector2f);
            if(isMouseOn || isSelected){
                poseStack.scale(1.1f,1.1f,1f);
            }
            poseStack.translate(-widthh/2f,-widthh/2f,0);
            render00();

            if(isSelected){
                poseStack.translate((widthh-mapSList.RWidth)/2f,-5,10);
                guiGraphics.fill(0,0,mapSList.RWidth,2,0x80002D5A);
                poseStack.translate(0,-mapSList.RHeight,0);
                mapSList.render();
                poseStack.translate(0,-2,0);
                guiGraphics.fill(0,0,mapSList.RWidth,2,0x80002D5A);


            }
        }
        poseStack.popPose();
    }

    public boolean isMouseOnWithMap(float scale, Vector2f vector2f){
        return vector2f.x > (xf -widthh/2f/scale) && vector2f.x < (xf +widthh/2f/scale) &&
                vector2f.y >(yf -heightt/2f/scale) && vector2f.y <(yf +heightt/2f/scale);
    }

    public void clickWithMap(){
        if(isSelected && isMouseOn){
            updataPosition(this.positionType);
            Minecraft.getInstance().setScreen(User.lastScreen);
        }else{
            isSelected = isMouseOn;
            if(isSelected){
                this.mapSList = new MapSList(text,true);
            }
        }

    }

    public static void updataPosition(PositionType positionType){
        Material lobby = new Lobby(true);
        lobby.close();
        Material lobbyBack = new LobbyBack(true);
        lobbyBack.close();
        Material mapButton = new MapButton(0,0);
        mapButton.close();

        User.positionType = positionType;
    }

}
