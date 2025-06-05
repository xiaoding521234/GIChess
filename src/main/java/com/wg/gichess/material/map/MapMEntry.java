package com.wg.gichess.material.map;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.RegionType;
import com.wg.gichess.material.MEntry;
import com.wg.gichess.material.Material;
import com.wg.gichess.screen.MapScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public class MapMEntry extends MEntry {


    public RegionType regionType;

    public MapMEntry(RegionType regionType) {
        super(new Material("textures/map/mapmentrybutton.png"));
        material.setSize((int) (Material.width*0.15), (int) (Material.width*0.15*0.34));
        this.regionType = regionType;
    }


    @Override
    public void render(int x, int y) {

        material.setXY(x,y);
        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            if ((isMouseOn() && MapMList.isMouseOn) || MapMList.target == this) {
                poseStack.translate(material.widthh / 2f + x, material.heightt / 2f + y, 0);
                poseStack.scale(1.1f, 1.1f, 1);
                poseStack.translate(-material.widthh / 2f, -material.heightt / 2f, 0);
            } else {
                poseStack.translate(x, y, 0);
            }

            material.render00();
            Font font = Minecraft.getInstance().font;
            poseStack.translate(material.widthh*0.15,(material.heightt-font.lineHeight)/2f,0);
            Material.guiGraphics.drawString(font,
                    Component.literal(regionType.displayName),
                    0, 0,
                    0xFFFFFFFF);


        }
        poseStack.popPose();


    }

    @Override
    public void click() {
        if(isMouseOn()){
            Minecraft.getInstance().setScreen(new MapScreen(regionType,true));

        }
    }

    public boolean isMouseOn(){
        return material.isMouseOn() ;
    }
}
