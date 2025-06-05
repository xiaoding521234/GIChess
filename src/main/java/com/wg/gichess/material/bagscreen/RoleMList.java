package com.wg.gichess.material.bagscreen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.User;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.material.*;
import com.wg.gichess.screen.FastScreen;
import net.minecraft.client.Minecraft;

public class RoleMList extends MList {

    public static boolean isMouseOn = false;
    public static boolean isNew = false;
    public static RoleMEntry target = null;

    public Material starIntroduce = null;
    public SListV1 SList = null;
    public RoleChess roleChess = null;
    public GoWarButton goWarButton = null;
    public SkinButton skinButton =null;
    public FastScreen lastScreen;

    public float CRHeight;


    public RoleMList(FastScreen lastScreen, boolean isFirst) {
        super(Material.width /16,Material.height/10,
                (int) (Material.width*0.65), (int) (Material.height*0.8),
                Material.width/10,Material.height/10,
                7,
                new Interval(Material.width/40,Material.height/30,Material.height/40,Material.width/40),
                isFirst);
        this.lastScreen = lastScreen;
    }

    @Override
    public void render() {
        isMouseOn = isMouseOn();
        if(CRHeight<y+RHeight && Material.delta/20f <0.04){
            CRHeight+=Material.height*Material.delta/20f;
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
            this.roleChess = target.roleChess;

            Material material =  roleChess.skins[roleChess.skin];

            starIntroduce = new Material("textures/item/" + roleChess.star + "starintroduce.png");
            starIntroduce.widthh = material.widthh;
            starIntroduce.heightt = material.heightt;

            SList = new SListV1(roleChess.getIntroduce(),
                    (int) (this.x+ this.RWidth + Material.width/50f),this.y+material.heightt+1,
                    material.widthh,this.RHeight- material.heightt-1,
                    material.widthh,
                    new Interval(0,0,0,0),
                    material.widthh,
                    Minecraft.getInstance().font.lineHeight,
                    true);


            skinButton = new SkinButton((int) (this.x+ this.RWidth + Material.width/50f), (int) (this.y+this.RHeight+Material.height*0.02),roleChess);
            if(User.gameMode == GameModes.GOWAR){
                goWarButton = new GoWarButton((int) (Material.width*0.85), (int) (this.y+this.RHeight+Material.height*0.02),roleChess,lastScreen);
            }

            isNew = false;

        }
        if(target!=null){

            Material material =  roleChess.skins[roleChess.skin];

            PoseStack poseStack = Material.poseStack;
            poseStack.pushPose();
            {
                poseStack.translate((int) (this.x + this.RWidth + Material.width / 50f), this.y, 0);
                starIntroduce.render();
                material.render();
                poseStack.translate(0, material.heightt, 0);
                Material.guiGraphics.fill(0,0,material.widthh,1,0xFF000000);
            }
            poseStack.popPose();

            skinButton.render();
            if(goWarButton!=null){
                goWarButton.render();
            }
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
        }else if(target!=null) {
            skinButton.click();
            if(goWarButton != null){
                goWarButton.click();
            }
        }


    }
}
