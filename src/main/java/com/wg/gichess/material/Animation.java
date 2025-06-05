package com.wg.gichess.material;

import net.minecraft.util.Mth;

public class Animation extends Material{
    public int currentFrame;      // 当前帧
    public int totalFrames;      // 总帧数
    public double currentTime; // 累积时间
    public double totalTime;  // 总时长
    public int frameRate;        // 帧率


    public Runnable onComplete;

    public AnimationManager animationManager;



    public void renderAnimation(){

    }

    public void OnComplete(){

        if(onComplete==null){
            return;
        }
        onComplete.run();
    }

    public int getNewColor(int color,float alpha){
        // 1. 提取原始Alpha值（0~255）
        int originalAlpha = (color >> 24) & 0xFF;

        // 2. 计算目标Alpha值（0~255），并确保不超过原始Alpha
        int newAlpha = (int) (Mth.clamp(alpha, 0, 1) * 255);
        newAlpha = Math.min(newAlpha, originalAlpha); // 关键点：取两者较小值

        // 3. 合成新颜色（保留RGB通道）
        return (newAlpha << 24) | (color & 0x00FFFFFF);
    }

    public void remove(){
        animationManager.remove(this);
    }

}
