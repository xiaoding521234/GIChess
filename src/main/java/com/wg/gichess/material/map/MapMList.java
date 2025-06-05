package com.wg.gichess.material.map;

import com.wg.gichess.material.*;

public class MapMList extends MList implements Introduce {
    public static boolean isMouseOn = false;
    public static MapMEntry target = null;

    public float CRHeight;

    public MapMList(boolean isFirst) {
        super((int) (Material.width*0.59), (int) (Material.height*0.01),
                (int) (Material.width*0.4), (int) (Material.height*0.9),
                Material.width/10,Material.height/10,
                2,
                new Interval(Material.width/40,Material.height/30,Material.height/40,Material.width/40),
                isFirst);
        this.CRHeight = y;
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
    }

    @Override
    public String getIntroduce() {
        return "";
    }

    @Override
    public void click(){
        if(isMouseOn()){
            MEntrys.forEach(MEntry::click);
        }
        else{
            Map.mode = Map.DEFAULT;
        }
    }
}
