package com.wg.gichess.material.tutorialscreen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.material.*;
import net.minecraft.client.Minecraft;

public class TutorialMList extends MList {

    public static boolean isMouseOn = false;
    public static boolean isNew = false;
    public static TutorialMEntry target = null;

    public SListV1 SList = null;
    public float CRHeight;

    public TutorialMList(boolean isFirst) {
        super((int) (Material.width *0.05),Material.height/10,
                (int) (Material.width*0.4), (int) (Material.height*0.8),
                Material.width/10,Material.height/10,
                2,
                new Interval(Material.width/40,Material.height/30,Material.height/40,Material.width/40),
                isFirst);
    }

    @Override
    public void render() {
        isMouseOn = isMouseOn();
        if(CRHeight<y+RHeight && Material.delta/20f <0.04){
            CRHeight+=Material.height*Material.delta/10f;
        }
        if(CRHeight>y+RHeight){
            CRHeight=y+RHeight;
        }
        Material.guiGraphics.enableScissor(x,y,x+RWidth, (int) CRHeight);

        currentX = x + interval.x ;
        currentY = y + interval.y ;

        for (MEntry entry : MEntrys) {
            // 获取当前图片的实际尺寸
            int imgWidth = entry.material.widthh;
            int imgHeight = entry.material.heightt;

            // 如果当前行已满，换到下一行
            if (columnCount > column) {
                currentY += maxRowHeight + interval.row;
                currentX = x + interval.x;
                columnCount = 1;
                maxRowHeight = 0;
            }

            // 渲染当前图片
            entry.render(currentX, currentY-scrollY);

            // 更新下一个图片的位置
            currentX += imgWidth + interval.column;
            columnCount++;

            // 更新当前行最大高度
            if (imgHeight > maxRowHeight) {
                maxRowHeight = imgHeight;
            }


        }
        heightt = currentY + maxRowHeight - y;
        currentX=0;
        currentY=0;
        columnCount = 1;
        maxRowHeight = 0;
        Material.guiGraphics.disableScissor();



        if(isNew){

            Material material =  target.introMaterial;

            SList = new SListV1(target.getIntroduce(),
                    Material.width/2,this.y+material.heightt+1,
                    material.widthh,this.RHeight- material.heightt-1,
                    material.widthh,
                    new Interval(0,0,0,0),
                    material.widthh,
                    Minecraft.getInstance().font.lineHeight,
                    true);

            isNew = false;

        }
        if(target!=null){

            Material material =  target.introMaterial;

            PoseStack poseStack = Material.poseStack;
            poseStack.pushPose();
            {
                poseStack.translate(Material.width/2f, this.y, 0);
                material.render00();
                poseStack.translate(0, material.heightt, 0);
                Material.guiGraphics.fill(0,0,material.widthh,1,0xFF000000);
            }
            poseStack.popPose();

            SList.render();

        }


    }

    @Override
    public void scroll(double deltaX, double deltaY) {
        if(isMouseOn()){

            scrollY += (int) (-deltaY*Material.height/25);
            if(scrollY <0 || (heightt-RHeight) <0){
                scrollY =0;
            }
            else if(scrollY >(heightt-RHeight+interval.y)){
                scrollY = heightt-RHeight;
            }

        }
        if(target != null){
            SList.scroll(deltaX,deltaY);
        }
    }

    @Override
    public void click() {

        if(isMouseOn){
            MEntrys.forEach(MEntry::click);
        }

    }



}
