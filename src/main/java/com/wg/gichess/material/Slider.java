package com.wg.gichess.material;

import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class Slider extends Material{

    int filledTrackColor = 0xFFFFF7F3;
    int unfilledTrackColor = 0xFF4D4D4D;

    float xf;
    float yf;
    public int maxLength;
    int heightt;

    float scrollX = 0f;
    private float dragStartX = 0f;
    Material thumb = new Material("textures/other/thumb.png");
    boolean isTarget = false;


    private int minValue = 0;
    private int maxValue = 100;
    private int stepCount = 0;       // 0表示连续滑动
    private float stepSize = 0;      // 每个步进的长度


    public String s = "0";
    private Consumer<Integer> onValueChanged = (integer) -> {
        s = String.valueOf(integer);
    };


    public Slider(float xf,float yf,int maxLength,int heightt){
        this.xf = xf;
        this.yf = yf;
        this.maxLength = maxLength;
        this.heightt = heightt;

        thumb.setSize(heightt*3);


    }

    @Override
    public void render(){

        poseStack.pushPose();
        {

            poseStack.translate( xf,yf,10);
            guiGraphics.fill(
                    0,0,maxLength,heightt,
                    unfilledTrackColor
            );
            guiGraphics.fill(
                    0,0, (int) scrollX,heightt,
                    filledTrackColor
            );

            poseStack.translate((int)scrollX -thumb.widthh/2f,heightt/2f- thumb.heightt/2f,1);

            thumb.render();

        }
        poseStack.popPose();

    }

    @Override
    public boolean click() {
        isTarget = isMouseOn();
        if (isTarget) {
            dragStartX = mouseX - xf;
            scrollX = dragStartX;
            snapToStep();
            notifyValueChanged();

        }
        return false;
    }


    @Override
    public void mouseDragged(int button,double dragX,double dragY){

        if (button == 0 && isTarget) {

            float newScrollX = (mouseX - xf);
            if (stepCount > 0) {

                float totalDrag = newScrollX - dragStartX;

                int stepsMoved = (int)(totalDrag / (stepSize/2));

                if (stepsMoved != 0) {
                    scrollX = Mth.clamp(
                            dragStartX + stepsMoved * stepSize,
                            0,
                            maxLength
                    );
                    dragStartX = scrollX;
                    snapToStep();
                    notifyValueChanged();
                }
            }
            else {
                scrollX = Mth.clamp(newScrollX, 0, maxLength);
                notifyValueChanged();
            }
        }

    }


    @Override
    public boolean isMouseOn(){
        return Material.mouseX >=xf - thumb.widthh/2f && Material.mouseX < xf + maxLength + thumb.widthh/2f &&
                Material.mouseY >= yf+heightt/2f- thumb.heightt/2f && Material.mouseY < yf + heightt/2f + thumb.heightt/2f;

    }


    public float getPercentage(){
        return scrollX/maxLength;
    }

    public void setColors(int filledColor, int unfilledColor) {
        this.filledTrackColor = filledColor;
        this.unfilledTrackColor = unfilledColor;
    }

    public void setOnValueChanged(Consumer<Integer> callback) {
        this.onValueChanged = callback;
    }

    public void setStepMode(int min, int max) {

        this.minValue = min;
        this.maxValue = max;
        this.stepCount = max - min;
        this.stepSize = stepCount > 0 ? (float)maxLength / stepCount : 0;
    }

    public void setValue(int value){
        scrollX = Mth.clamp((value - minValue)*stepSize,0,maxLength);
        s = String.valueOf(value);
        notifyValueChanged();
    }

    private void snapToStep() {
        if (stepCount > 0) {
            int step = Math.round(scrollX / stepSize);
            scrollX = Mth.clamp(step * stepSize, 0, maxLength);
        }
    }


    public int getCurrentValue() {
        if (stepCount > 0) {
            return minValue + Math.round(scrollX / stepSize);
        }
        return (int)(getPercentage() * 100);
    }

    private void notifyValueChanged() {
        if (onValueChanged != null) {
            onValueChanged.accept(getCurrentValue());
        }
    }



}
