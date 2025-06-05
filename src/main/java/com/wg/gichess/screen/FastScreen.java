package com.wg.gichess.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.User;
import com.wg.gichess.material.MList;
import com.wg.gichess.material.Material;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FastScreen extends Screen{

    public boolean isFirst = true;
    public float fadeProgress = 0.7f;

    public List<Button> buttons = new ArrayList<>();
    public List<Material> materials = new ArrayList<>();
    public List<MList> MLists = new ArrayList<>();
    public Material backGround ;

    protected FastScreen(Component component,boolean isFirst) {
        super(component);
        this.isFirst = isFirst;
    }

    public void addButtons(List<Button> buttons){
        if(!buttons.isEmpty()){
            buttons.forEach(this::addWidget);
        }
    }

    public void renderMaterials(List<Material> materials){
        if(!materials.isEmpty()){
            materials.forEach(Material::render);
        }
    }

    public void renderMLists(List<MList> MLists){
        if(!MLists.isEmpty()){
            for(MList MList : MLists){
                MList.render();
            }
        }
    }

    public void scrollMLists(List<MList> MLists,double deltaX,double deltaY){
        if(!MLists.isEmpty()){
            for(MList MList : MLists){
                MList.scroll(deltaX,deltaY);
            }
        }
    }

    public void clickMaterials(List<Material> materials){
        if(materials.isEmpty()){
            return;
        }

        List<Material> sortedMaterials = materials.stream()
                .sorted(Comparator.comparingInt(Material::getPriority).reversed())
                .toList();


        for(Material material : sortedMaterials) {
            if(material.click()) {

                break;
            }
        }
    }

    public void releaseMaterials(List<Material> materials){
        if(materials.isEmpty()){
            return;
        }

        for(Material material : materials) {
            material.release();
        }


    }

    public void clickMLists(List<MList> MLists){
        if(!MLists.isEmpty()){
            for(MList MList : MLists){
                MList.click();
            }
        }
    }

    public void dragMaterials(List<Material> materials,int button, double dragX, double dragY){

        if(!materials.isEmpty()){
            for(Material material : materials){
                material.mouseDragged(button,dragX,dragY);
            }
        }

    }

    @Override
    protected void init() {
        addButtons(buttons);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        renderMaterials(materials);
        renderMLists(MLists);
        User.animationManager.renderAll();

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        scrollMLists(MLists,deltaX,deltaY);
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        clickMaterials(materials);
        clickMLists(MLists);
        return true;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        Material.mouseX = (float) mouseX;
        Material.mouseY = (float) mouseY;
        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        dragMaterials(materials,button,dragX,dragY);


        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {

        releaseMaterials(materials);

        return super.mouseReleased(mouseX, mouseY, button);
    }

    public void updateScreen(){
        Material.width = width;
        Material.height = height;
    }

    public void updateGui(GuiGraphics guiGraphics, float delta){
            Material.guiGraphics = guiGraphics;
            Material.poseStack = guiGraphics.pose();
            Material.delta = delta;
    }

    public void fadeIn(){
        if(Material.delta/20<0.04){
            fadeProgress = Math.min(fadeProgress + Material.delta * 0.05f, 1.0f);
            if (fadeProgress < 1.0f) {
                int alpha = (int) ((1 - fadeProgress) * 255); // 255 → 0

                int color = (alpha << 24);
                PoseStack poseStack = Material.poseStack;
                poseStack.pushPose();
                {
                    poseStack.translate(0,0,1800);
                    Material.guiGraphics.fill(0, 0, width, height, color);
                }
                poseStack.popPose();
            }
        }
    }



}
