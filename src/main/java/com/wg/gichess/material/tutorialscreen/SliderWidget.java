package com.wg.gichess.material.tutorialscreen;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.Slider;
import net.minecraft.client.Minecraft;

public class SliderWidget extends Material {
    public float xf;
    public float yf;

    public String displayName;
    public Slider slider;
    public float widthh;

    public int color = 0xFFc6c2bd;



    public SliderWidget(String displayName,float widthh,float xf, float yf, int maxLength, int heightt) {
        this.displayName = displayName;
        this.xf = xf;
        this.yf = yf;

        this.widthh = widthh + 3;
        slider = new Slider(xf+widthh,yf+ (font.lineHeight-heightt)/2f,maxLength,heightt);
    }

    public SliderWidget(String displayName,float xf, float yf, int maxLength, int heightt) {
        this.displayName = displayName;
        this.xf = xf;
        this.yf = yf;

        this.widthh = font.width(displayName) + 3;
        slider = new Slider(xf+widthh,yf+ (font.lineHeight-heightt)/2f,maxLength,heightt);
    }

    public SliderWidget(String displayName,float xf, float yf) {
        this.displayName = displayName;
        this.xf = xf;
        this.yf = yf;

        this.widthh = font.width(displayName) + 3;
        slider = new Slider(xf+widthh,yf+ (font.lineHeight-(int) (height*0.01))/2f,(int) (width*0.15),(int) (height*0.01));
    }

    public SliderWidget(String displayName,float widthh,float xf, float yf) {
        this.displayName = displayName;
        this.xf = xf;
        this.yf = yf;

        this.widthh = widthh + 3;
        slider = new Slider(xf+widthh,yf+ (font.lineHeight-(int) (height*0.01))/2f,(int) (width*0.15),(int) (height*0.01));
    }



    @Override
    public void render() {


        poseStack.pushPose();
        {
            poseStack.translate(xf,yf,10);
            guiGraphics.drawString(font,
                    displayName ,
                    0,0,
                    0xFF495366,
                    false
                    );
            poseStack.pushPose();
            {
                poseStack.translate(widthh - 15 - font.width(String.valueOf(slider.getCurrentValue()))/2f,0,10);
                guiGraphics.drawString(font,
                        String.valueOf(slider.s),
                        0,0,
                        0xFF495366,
                        false
                );

            }
            poseStack.popPose();



            poseStack.translate(-3,-1,-2);
            guiGraphics.fill(0,0,
                    (int) (6+widthh+slider.maxLength),2+ font.lineHeight,
                    color);
            poseStack.translate(0,-1,0);
            guiGraphics.fill(0,0,
                    (int) (6+widthh+slider.maxLength),1,
                    0x80002D5A);
            poseStack.translate(0,2+ font.lineHeight+1,0);
            guiGraphics.fill(0,0,
                    (int) (6+widthh+slider.maxLength),1,
                    0x80002D5A);


        }
        poseStack.popPose();

        slider.render();

    }

    @Override
    public boolean click() {
        return slider.click();
    }

    @Override
    public void mouseDragged(int button, double dragX, double dragY) {
        slider.mouseDragged(button, dragX, dragY);
    }

    public void reset(){

    }


}
