package com.wg.gichess.material;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

public class SListV2 {
    public int x;
    public int y;
    public int RWidth;//裁剪渲染范围
    public int RHeight;
    public int widthh;//总范围
    public int heightt;
    public Interval interval;
    public int maxWidthh;
    public int blankLineHeight;
    public boolean isFirst;
    public boolean enableScissor;

    public int currentX=0;//当前目录位置
    public int currentY=0;
    public int maxRowHeight=0;
    public int columnCount =1;
    public int scrollY = 0;
    public Material material = new Material("textures/item/introduce.png");

    public String text;
    public String[] lines ;
    public List<FormattedCharSequence> wrappedLines = new ArrayList<>();

    public SListV2(String text, int x, int y,
                   int RWidth, int RHeight,
                   int widthh,
                   Interval interval,
                   int maxWidthh, int blankLineHeight, boolean isFirst,boolean enableScissor){
        this.text = text;
        this.x = x;
        this.y = y;
        this.RWidth = RWidth;
        this.RHeight = RHeight;
        this.widthh = widthh;
        this.interval = interval;
        this.maxWidthh = maxWidthh;
        this.blankLineHeight = blankLineHeight;
        this.isFirst = isFirst;
        this.enableScissor = enableScissor;

        material.widthh = RWidth;
        material.heightt = RHeight;

        lines = text.split("\n");

    }

    public void render() {

        PoseStack poseStack = Material.poseStack;

        poseStack.pushPose();
        {
            poseStack.translate(x, y, 0);
            material.render();
        }
        poseStack.popPose();

        currentX = x + interval.x ;
        currentY = y + interval.y ;

        if(enableScissor){
            Material.guiGraphics.enableScissor(x,y,x+RWidth,y+RHeight);
        }

        poseStack.pushPose();
        {
            poseStack.translate(currentX, currentY - scrollY, 0);
            poseStack.scale(0.8f, 0.8f, 1f);


            for (String line : lines) {

                // 处理空白行标记
                if (line.trim().equals("\\s")) {
                    currentY += blankLineHeight;
                    poseStack.translate(0, blankLineHeight, 0);
                    continue;
                }

                Component component = Component.literal(line);
                List<FormattedCharSequence> wrappedLines = Minecraft.getInstance().font.split(component, (int) (maxWidthh / 0.8));

                for (FormattedCharSequence wrappedLine : wrappedLines) {
                    Material.guiGraphics.drawString(
                            Minecraft.getInstance().font,
                            wrappedLine,
                            0, 0,
                            0xFF000000,
                            false
                    );
                    poseStack.translate(0, Minecraft.getInstance().font.lineHeight, 0);
                    currentY += Minecraft.getInstance().font.lineHeight;
                }


            }
        }
        poseStack.popPose();
        heightt = (int) ((currentY - y)*0.8);
        currentY=0;
        if(enableScissor){
            Material.guiGraphics.disableScissor();
        }


    }

    public void scroll(double deltaX, double deltaY){
        if(isMouseOn()){

            scrollY += (int) (-deltaY*Material.height/25);
            if(scrollY <0 || (heightt-RHeight) <0){
                scrollY =0;
            }
            else if(scrollY >(heightt-RHeight+interval.y)){
                scrollY = heightt-RHeight;
            }

        }
    }

    public static int getRHeight(String text , int maxWidthh){
        String[] lines  = text.split("\n");
        List<FormattedCharSequence> wrappedLines = new ArrayList<>();
        int size=0;

        for (String line : lines) {

            // 处理空白行标记
            if (line.trim().equals("\\s")) {
                size++;
                continue;
            }

            Component component = Component.literal(line);
            wrappedLines = Minecraft.getInstance().font.split(component, (int) (maxWidthh / 0.8));
            size+=wrappedLines.size();

        }
        return (int) ((size+1)*Material.font.lineHeight*0.8);
    }

//    public static int getRHeight(String text , int maxWidthh){
//        String[] lines  = text.split("\n");
//        List<FormattedCharSequence> wrappedLines = new ArrayList<>();
//        int size=0;
//
//        for (String line : lines) {
//
//            // 处理空白行标记
//            if (line.trim().equals("\\s")) {
//                size++;
//                continue;
//            }
//
//            Component component = Component.literal(line);
//            wrappedLines = Minecraft.getInstance().font.split(component, (int) (maxWidthh / 0.8));
//            size+=wrappedLines.size();
//
//        }
//        return size;
//    }

    public boolean isMouseOn(){
        return Material.mouseX>x && Material.mouseX<x+RWidth &&
                Material.mouseY>y && Material.mouseY<y+RHeight ;
    }

}
