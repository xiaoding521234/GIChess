package com.wg.gichess.screen.bagscrren;

import com.wg.gichess.User;
import com.wg.gichess.material.Interval;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.bagscreen.*;
import com.wg.gichess.material.map.LobbyBack;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.LobbyScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class ValuableScreen extends FastScreen {
    public int target;
    public FastScreen lastScreen;


    public ValuableScreen(boolean isFirst,int target,FastScreen lastScreen) {
        super(Component.literal("珍贵背包界面"), isFirst);
        this.target = target;
        this.lastScreen = lastScreen;
    }


    @Override
    protected void init() {

        materials.clear();
        MLists.clear();

        updateScreen();
        materials.add(new LobbyBack(true));
        materials.add(new DarkenTop());
        materials.add(new Bag((int) (width*0.04), 1));
        materials.add(new Material(null){
            @Override
            public void render() {
                guiGraphics.drawString(font,
                        Component.literal("珍藏"),
                        (int) (width*0.1),(int)(height*0.02),
                        0xFFAA00);
            }
        });


        int w=(int) (width*0.4);
        int h=(int) (-height*0.2);
        int i=1;
        Interval interval = new Interval(0,0,(int) (-height*0.05),(int) (width*0.07));

        materials.add(new RoleButton(w,h,isFirst,target,lastScreen));
        materials.add(new ValuableButton(w + i*interval.column, h + i*interval.row,isFirst,target,lastScreen));
        i++;
        materials.add(new PropButton(w + i*interval.column, h + i*interval.row,isFirst,target,lastScreen));



        materials.add(new Material(null){
            @Override
            public void render() {
                guiGraphics.fill((int) (width*0.4 + (target-1)* width*0.07), height/11-1, (int) (width*0.44 + (target-1)* width*0.07), height/11 , 0xFFFFFF00);
            }
        });




        materials.add(new CloseButton((int) (width*0.95), (int) (height*0.01),lastScreen));
        super.init();
    }
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        return super.mouseScrolled(mouseX, mouseY, deltaX, deltaY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
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
