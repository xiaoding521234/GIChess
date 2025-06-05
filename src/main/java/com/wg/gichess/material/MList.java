package com.wg.gichess.material;


import com.wg.gichess.material.map.Map;

import java.util.ArrayList;
import java.util.List;

public class MList {
    public List<MEntry> MEntrys = new ArrayList<>();
    public int x;
    public int y;
    public int RWidth;//裁剪渲染范围
    public int RHeight;
    public int widthh;//总范围
    public int heightt;
    public int column = 1;//列数
    public Interval interval;
    public boolean isFirst;


    public int currentX=0;//当前目录位置
    public int currentY=0;
    public int maxRowHeight=0;
    public int columnCount =1;
    public int scrollY = 0;



    public MList(int x,int y,int RWidth,int RHeight,int widthh,int heightt,int column,Interval interval,boolean isFirst){
        this.x = x;
        this.y = y;
        this.RWidth = RWidth;
        this.RHeight = RHeight;
        this.widthh = widthh;
        this.heightt = heightt;
        this.column = column;
        this.interval = interval;
        this.isFirst = isFirst;
    }

    public void render() {

        Material.guiGraphics.enableScissor(x,y,x+RWidth,y+RHeight);

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

    public boolean isMouseOn(){
        return Material.mouseX>x && Material.mouseX<x+RWidth &&
                Material.mouseY>y && Material.mouseY<y+RHeight ;
    }

    public void click(){
        if(isMouseOn()){
            MEntrys.forEach(MEntry::click);
        }
    }
}
