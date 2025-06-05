package com.wg.gichess.screen.tutorialscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.Interval;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.bagscreen.CloseButton;
import com.wg.gichess.material.bagscreen.DarkenTop;
import com.wg.gichess.material.map.LobbyBack;
import com.wg.gichess.material.tutorialscreen.*;
import com.wg.gichess.material.tutorialscreen.fundamentals.Preface;
import com.wg.gichess.screen.FastScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class AnnouncementScreen extends FastScreen {

    public int target;
    public FastScreen lastScreen;


    public AnnouncementScreen(boolean isFirst,int target,FastScreen lastScreen) {
        super(Component.literal("公告界面"), isFirst);
        this.target = target;
        this.lastScreen = lastScreen;
    }

    @Override
    protected void init() {

        materials.clear();
        MLists.clear();
        TutorialMList.target = null;

        updateScreen();
        backGround = new LobbyBack(true);
        materials.add(new DarkenTop());
        materials.add(new Tutorial((int) (width*0.04), 1));
        materials.add(new Material(null){
            @Override
            public void render() {
                guiGraphics.drawString(font,
                        Component.literal("公告"),
                        (int) (width*0.1),(int)(height*0.02),
                        0xFFAA00);
            }
        });

        int w=(int) (width*0.35);
        int h=(int) (-height*0.2);
        int i=1;
        Interval interval = new Interval(0,0,(int) (-height*0.05),(int) (width*0.09));

        materials.add(new FundamentalsButton(w,h,isFirst,target,lastScreen));
        materials.add(new ElementalSystemButton(w + i*interval.column, h + i*interval.row,isFirst,target,lastScreen));
        i++;
        materials.add(new AdvancedTechniquesButton(w + i*interval.column, h + i*interval.row,isFirst,target,lastScreen));
        i++;
        materials.add(new AnnouncementButton(w + i*interval.column, h + i*interval.row,isFirst,target,lastScreen));
        materials.add(new Material(null){
            @Override
            public void render() {
                guiGraphics.fill( w+ (target-1)* interval.column, height/11-1, (int) (width*0.04 + w + (target-1)* interval.column), height/11 , 0xFFFFFF00);
            }
        });

        materials.add(new CloseButton((int) (width*0.95), (int) (height*0.01),lastScreen));

        TutorialMList tutorialMList = new TutorialMList(true);
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());
        tutorialMList.MEntrys.add(new Preface());


        MLists.add(tutorialMList);

        super.init();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        return super.mouseScrolled(mouseX, mouseY, deltaX, deltaY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        backGround.render();
        super.render(guiGraphics, mouseX, mouseY, delta);
        User.mouseCursor.render();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            return super.mouseClicked(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(lastScreen);
    }
}
