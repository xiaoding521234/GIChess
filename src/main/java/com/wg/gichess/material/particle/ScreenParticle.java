package com.wg.gichess.material.particle;

import com.wg.gichess.material.Material;

public class ScreenParticle extends Material {

    public float x;
    public float y;
    public float z = 1;
    public int widthh;
    public int heightt;
    public float scale = 1;
    public int color = -1;
    public Material material = null ;
    public double currentTime; // 累积时间
    public double totalTime;  // 总时长

    public Runnable onRender = this::standardRender;;

    public ScreenParticle(float x,float y,int widthh,int heightt,float scale,int color){
        this.x= x;
        this.y = y;
        this.widthh = widthh;
        this.heightt = heightt;
        this.scale = scale;
        this.color = color;

    }

    public ScreenParticle(float x,float y,int widthh,int heightt,float scale,Material material){
        this.x= x;
        this.y = y;
        this.widthh = widthh;
        this.heightt = heightt;
        this.scale = scale;
        this.material = material;

    }

    public ScreenParticle(float x,float y,int widthh,int heightt,float scale,int color,Material material){
        this.x= x;
        this.y = y;
        this.widthh = widthh;
        this.heightt = heightt;
        this.scale = scale;
        this.color = color;
        this.material = material;
    }

    public void setOnRender(Runnable onRender){
        this.onRender = onRender;
    }

    public void renderScreenParticle(){

        if(onRender!=null){
            onRender.run();
        }

    }

    public void renderParticle(){
        if(material==null){
            guiGraphics.fill(0,0, widthh, heightt,color);
            return;
        }
        if(color==-1){
            material.render00( widthh,heightt);
            return;
        }
        material.render00( widthh,heightt,color);

    }

    public void standardRender(){
        currentTime = Math.min(currentTime + delta/20,totalTime);
        poseStack.pushPose();
        {
            poseStack.translate(x+widthh/2f,y+heightt/2f,z);
            poseStack.scale(scale,scale,1f);
            poseStack.translate(-widthh/2f,-heightt/2f,0);
            renderParticle();


        }
        poseStack.popPose();
    }


}