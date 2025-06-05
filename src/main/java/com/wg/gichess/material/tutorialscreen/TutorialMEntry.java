package com.wg.gichess.material.tutorialscreen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.material.Introduce;
import com.wg.gichess.material.MEntry;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.bagscreen.RoleMList;
import com.wg.gichess.material.map.MapMList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class TutorialMEntry extends MEntry implements Introduce {
    public Material introMaterial;
    public String displayName;
    public Material shineMaterial = new Material("textures/tutorialscreen/tutorialmentrybuttonshine.png");

    public TutorialMEntry(String displayName,Material introMaterial) {
        super(new Material("textures/tutorialscreen/tutorialmentrybutton.png"));
        this.material.setSize((int) (Material.width*0.15), (int) (Material.width*0.15*0.28));
        shineMaterial.setSize((int) (Material.width*0.15), (int) (Material.width*0.15*0.28));
        this.displayName = displayName;
        this.introMaterial = introMaterial;
        introMaterial.setSize((int) (Material.width*0.45),(int) (Material.width*0.45*0.66));
    }

    @Override
    public String getIntroduce() {
        return "";
    }

    @Override
    public void render(int x, int y) {

        material.setXY(x,y);
        shineMaterial.setXY(x,y);


        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            if ((isMouseOn() && TutorialMList.isMouseOn) || TutorialMList.target == this) {
                poseStack.translate(material.widthh / 2f + x, material.heightt / 2f + y, 0);
                poseStack.scale(1.1f, 1.1f, 1);
                poseStack.translate(-material.widthh / 2f, -material.heightt / 2f, 0);
            } else {
                poseStack.translate(x, y, 0);
            }

            if(TutorialMList.target != this){

                material.render00();
                Font font = Minecraft.getInstance().font;
                poseStack.translate(material.widthh*0.1,(material.heightt-font.lineHeight)/2f,0);
                Material.guiGraphics.drawString(font,
                        Component.literal(displayName),
                        0, 0,
                        0xFFFFFFFF);

            } else{
                shineMaterial.render00();
                Font font = Minecraft.getInstance().font;
                poseStack.translate(material.widthh*0.1,(material.heightt-font.lineHeight)/2f,0);
                Material.guiGraphics.drawString(font,
                        Component.literal(displayName),
                        0, 0,
                        0xFF000000);

            }




        }
        poseStack.popPose();


    }


    @Override
    public void click() {
        if(isMouseOn() && TutorialMList.target!=this){
            TutorialMList.target = this;
            TutorialMList.isNew = true;
        }
    }


    public boolean isMouseOn(){
        return material.isMouseOn() ;
    }
}
