package com.wg.gichess.screen.battlelobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.map.LobbyBack;
import com.wg.gichess.screen.FastScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class OnlineScreen extends FastScreen {
    public OnlineScreen(boolean isFirst) {
        super(Component.literal("联机界面"), isFirst);
    }

    @Override
    protected void init() {
       updateScreen();
       materials.add(new LobbyBack(true));




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
}
