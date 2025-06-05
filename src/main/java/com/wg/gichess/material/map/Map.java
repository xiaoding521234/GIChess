package com.wg.gichess.material.map;

import com.wg.gichess.RegionType;
import com.wg.gichess.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Map extends Material {

    public static int mode= Map.DEFAULT;
    public static final int DEFAULT =1;
    public static final int CHOOSE = 2;

    public List<Waypoint> waypoints = new ArrayList<>();
    public String name;
    public Material chooseMapButton;
    public Material closeButton;
    public MapMList mapMList ;

    public float scrollX =0f;
    public float scrollY =0f;
    public float scale = 1f;
    public float screenCenterX;
    public float screenCenterY;
    public float mapOffsetX ;
    public float mapOffsetY ;


    public Map(String id,String name) {
        super(id);
        mode = DEFAULT;
        this.name = name;
        this.chooseMapButton = new ChooseMapButton ((int)(width*0.93),(int)(height*0.93),this);
        this.closeButton = new CloseButton((int) (width*0.95), (int) (height*0.01));
        mapMList = new MapMList(true);

        List<RegionType> regionsWithMap = new ArrayList<>();
        for (RegionType region : RegionType.values()) {
            if (region.mapSupplier != null) {
                regionsWithMap.add(region);
            }
        }

        for(RegionType region : regionsWithMap){
            mapMList.MEntrys.add(new MapMEntry(region));
        }


    }

    @Override
    public void render() {

        screenCenterX = width / 2f;
        screenCenterY = height / 2f;

        mapOffsetX = -widthh / 2f + screenCenterX - scrollX;
        mapOffsetY = -heightt / 2f + screenCenterY - scrollY;

        poseStack.pushPose();
        {
            poseStack.translate(screenCenterX, screenCenterY, 0);
            poseStack.scale(scale, scale, 1.0f);
            poseStack.translate(-width / (2f * scale) - scrollX, -height / (2f * scale) - scrollY, 0);
            this.render00();
            for(Waypoint waypoint : waypoints){
                waypoint.renderOnMap(scale,getMouseXYOnMap());
            }


        }
        poseStack.popPose();

        closeButton.render();

        switch (mode){
            case DEFAULT : {
                poseStack.pushPose();
                {
                    poseStack.translate(chooseMapButton.x, chooseMapButton.y, 0);
                    chooseMapButton.render();
                    Font font = Minecraft.getInstance().font;
                    poseStack.translate(-font.width(name) - 3, (chooseMapButton.heightt - font.lineHeight) / 2f, 0);
                    guiGraphics.drawString(Minecraft.getInstance().font,
                            Component.literal(name),
                            0, 0,
                            0xFFFFFFFF);
                }
                poseStack.popPose();
                break;
            }
            case CHOOSE : {
                poseStack.pushPose();{
                    poseStack.translate(0,0,20);
                    mapMList.render();
                }
                poseStack.popPose();

                break;

            }

        }

    }



    public void mouseDragged(int button,double dragX,double dragY){

        if (button == 0 && mode == Map.DEFAULT) {
            scrollX -= (float) (dragX*1.2/scale);
            scrollY -= (float) (dragY*1.2/scale);

            clampScroll();
        }

    }

    @Override
    public boolean click() {
        closeButton.onClick();
        switch (mode){
            case DEFAULT :{
                chooseMapButton.click();
                for(Waypoint waypoint : waypoints){
                    waypoint.clickWithMap();
                }
                break;

            }
            case CHOOSE:{
                mapMList.click();
                break;
            }

        }
        return false;
    }

    @Override
    public void scroll(double deltaX, double deltaY) {
        switch (mode){

            case DEFAULT :{
                float oldScale = scale;
                scale *= (float) (1+deltaY * 0.08); // 更新缩放因子
                scale = Mth.clamp(scale,0.6f,1.8f);
                float centerMapX = scrollX + width / (2f * oldScale);
                float centerMapY = scrollY + height / (2f * oldScale);

                // 缩放后，保持该地图坐标对准屏幕中心
                scrollX = centerMapX - width / (2f * scale);
                scrollY = centerMapY - height / (2f * scale);
                break;
            }
            case CHOOSE:{
                mapMList.scroll(deltaX,deltaY);
                break;
            }

        }

    }

    public void clampScroll() {
        scrollX = Mth.clamp(scrollX, -width/2f ,(widthh + width )*scale);
        scrollY = Mth.clamp(scrollY, -height/2f, (heightt +height)* scale);
    }

    public void setScrollXY(float scrollX,float scrollY){
        this.scrollX = scrollX;
        this.scrollY = scrollY;
    }

    public Vector2f getMouseXYOnMap(){
        float relX  =  ((mouseX - screenCenterX) / scale + scrollX + width/(2*scale));
        float relY  = ((mouseY - screenCenterY) / scale + scrollY + height/(2*scale));
        return new Vector2f(relX, relY);
    }
}
