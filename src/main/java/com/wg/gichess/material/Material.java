package com.wg.gichess.material;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.Wg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Material implements AutoCloseable{

    public static GuiGraphics guiGraphics;
    public static float mouseX;
    public static float mouseY;
    public static float delta;
    public static PoseStack poseStack;
    public static int width;
    public static int height;
    public static Font font = Minecraft.getInstance().font;
    boolean isUploaded=false;

    public ResourceLocation resourceLocation;
    public int x;
    public int y;
    public int widthh = 0;
    public int heightt = 0;
    public float xf;
    public float yf;
    public int color = 0;
    // 0xFF808080 灰色

    public int priority = 0;
    public boolean isDragging = false;

    public List<Material> materials = new ArrayList<>();
    public Material shouldRenderM = this;


    public final Function<ResourceLocation, RenderType> RENDER_FUNCTION = id -> {
        if (id.equals(resourceLocation)) {
            return RenderType.guiTextured(resourceLocation);
        }
        return null;
    };

    public Material(){

    }

    public Material(String id,int x,int y){
        this.resourceLocation =  ResourceLocation.tryBuild(Wg.MODID,"gichess/" + id);
        this.x = x;
        this.y = y;
        this.isUploaded = true;
    }

    public Material(String id){
        this.resourceLocation =  ResourceLocation.tryBuild(Wg.MODID,"gichess/" + id);
        this.isUploaded = true;
    }

    public void setShouldRenderM(Material material){
        shouldRenderM = material;
    }

    public void setSize(int width){
        this.widthh = width;
        this.heightt = width;
    }

    public Material setSize(int width, int height){
        this.widthh = width;
        this.heightt = height;
        return this;
    }

    public Material setXY(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public void setXYf(float xf,float yf){
        this.xf = xf;
        this.yf = yf;
    }

    public void setID(String id){
        this.resourceLocation =  ResourceLocation.tryBuild(Wg.MODID,"gichess/" + id);
    }

    public void render(){
        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x,y,
                0,0,
                widthh,heightt,
                widthh,heightt);
    }

    public void render(int color){
        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x,y,
                0,0,
                widthh,heightt,
                widthh,heightt,
                color);
    }

    public void render(int width,int height){
        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x,y,
                0,0,
                width,height,
                width,height
                );

    }

    public void render(int width,int height,int color){
        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                x,y,
                0,0,
                width,height,
                width,height,
                color
        );

    }


    public void render00(){
        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0,
                0,0,
                widthh,heightt,
                widthh,heightt);
    }

    public void render00(int color){
        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0,
                0,0,
                widthh,heightt,
                widthh,heightt,
                color);
    }
    public void render00(int width,int height){

        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0,
                0,0,
                width,height,
                width,height
                );

    }


    public void render00(int width,int height,int color){

        guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                0,0,
                0,0,
                width,height,
                width,height,
                color);

    }


    public void renderf(){
        poseStack.pushPose();
        {
            poseStack.translate(xf,yf,0);
            guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                    0,0,
                    0,0,
                    widthh,heightt,
                    widthh,heightt);
        }
        poseStack.popPose();
    }
    public void renderf(int color){
        poseStack.pushPose();
        {
            poseStack.translate(xf,yf,0);
            guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                    0,0,
                    0,0,
                    widthh,heightt,
                    widthh,heightt,
                    color);
        }
        poseStack.popPose();
    }


    public boolean isMouseOn(){
        return mouseX >= x && mouseX <= x + widthh && mouseY >= y && mouseY <= y + heightt;
    }

    public boolean isMouseOnf(){
        return mouseX >= xf && mouseX <= xf + widthh && mouseY >= yf && mouseY <= yf + heightt;
    }


    public boolean click(){
        return false;
    }

    public void onClick(){

    }

    public boolean release() {
        isDragging = false;
        return false;
    }

    public void close(){
        if (isUploaded) {
            Minecraft.getInstance().execute(() -> {
            TextureManager manager = Minecraft.getInstance().getTextureManager();
            manager.release(resourceLocation);
            });
            isUploaded =false;
        }
    }

    public void scroll(double deltaX, double deltaY){

    }


    public void mouseDragged(int button,double dragX,double dragY){

    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
