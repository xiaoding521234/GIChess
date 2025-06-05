package com.wg.gichess.screen;

import com.wg.gichess.RegionType;
import com.wg.gichess.User;
import com.wg.gichess.material.map.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;


public class MapScreen extends FastScreen{

    public RegionType regionType;
    public static Map map;

    public MapScreen(RegionType regionType, boolean isFirst) {
        super(Component.literal("地图界面"), isFirst);
        this.regionType = regionType;
    }

    @Override
    protected void init() {
        materials.clear();
        MLists.clear();

        updateScreen();
        backGround = new MapBack();

        if(map != null){
            map.close();
        }

        Map.mode = Map.DEFAULT;
        map =  regionType.getMap();


        super.init();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY){

        map.mouseDragged(button,dragX,dragY);
        return true;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        updateGui(guiGraphics,delta);

        backGround.render();
        map.render();
        super.render(guiGraphics, mouseX, mouseY, delta);
        fadeIn();
        User.mouseCursor.render();

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        map.click();
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void onClose() {
        switch(Map.mode){
            case Map.DEFAULT :{
                map.close();
                Minecraft.getInstance().setScreen(User.lastScreen);
                break;
            }
            case Map.CHOOSE:{
                map.mapMList.CRHeight = map.mapMList.y;
                Map.mode = Map.DEFAULT;
                break;
            }
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        map.scroll(deltaX,deltaY);
        return true;
    }
}
