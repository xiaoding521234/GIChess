package com.wg.gichess.screen;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;



public class TestScreen extends FastScreen {


    protected TestScreen(boolean isFirst) {
        super(Component.literal("123"), isFirst);
    }

    @Override
    protected void init() {

        


        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        updateGui(guiGraphics,delta);


        super.render(guiGraphics,mouseX,mouseY,delta);


    }
}
