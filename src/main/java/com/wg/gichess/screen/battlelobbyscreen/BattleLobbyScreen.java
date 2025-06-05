package com.wg.gichess.screen.battlelobbyscreen;

import com.wg.gichess.User;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.battlelobbyscreen.*;
import com.wg.gichess.material.map.LobbyBack;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.LobbyScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class BattleLobbyScreen extends FastScreen {
    public static final int maxMode = 2;
    public int mode = 1;

    public List<Material> materialsByMode = new ArrayList<>();


    public BattleLobbyScreen(boolean isFirst) {
        super(Component.literal("战斗大厅界面"), isFirst);
    }

    @Override
    protected void init() {
        updateScreen();
        materials.clear();

        backGround = new LobbyBack(true);



        materials.add(new CloseButton((int)(width*0.95),(int)(height *0.01)));
        materials.add(new LButton((int)(width*0.01),(int)(height*0.45),this));
        materials.add(new RButton((int)(width*0.95),(int)(height*0.45),this));

        addMaterialsByMode(materialsByMode,mode);

        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        updateGui(guiGraphics,delta);

        backGround.render();
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderMaterials(materialsByMode);

        if(delta/20<0.04){
            fadeProgress = Math.min(fadeProgress + delta * 0.05f, 1.0f);
            if (fadeProgress < 1.0f) {
                int alpha = (int) ((1 - fadeProgress) * 255); // 255 → 0

                int color = (alpha << 24);
                guiGraphics.fill(0, 0, width, height, color);
            }
        }
        User.mouseCursor.render();


    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(button==0){
            clickMaterials(materialsByMode);
            return super.mouseClicked(mouseX, mouseY, button);
        }
        return false;

    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(new LobbyScreen(true));
    }

    public void addMaterialsByMode(List<Material> materialsByMode,int mode){
        materialsByMode.clear();

        switch (mode){
            case 1:{
                BlackDivider blackDivider1 = new BlackDivider((int) (width*0.1),1,true);
                BlackDivider blackDivider2 = new BlackDivider((int) (width*0.3),2,true);
                BlackDivider blackDivider3 = new BlackDivider((int) (width*0.5),3,true);
                BlackDivider blackDivider4 = new BlackDivider((int) (width*0.7),4,true);
                BlackDivider blackDivider5 = new BlackDivider((int) (width*0.9),5,true);
                materialsByMode.add(blackDivider1);
                materialsByMode.add(blackDivider2);
                materialsByMode.add(blackDivider3);
                materialsByMode.add(blackDivider4);
                materialsByMode.add(blackDivider5);
                materialsByMode.add(new LocalPvPButton(0,blackDivider1,blackDivider2));
                materialsByMode.add(new OnlinePvPButton((int) (width*0.2),blackDivider2,blackDivider3));
                materialsByMode.add(new CelestialForgeButton((int) (width*0.4),blackDivider3,blackDivider4));
                materialsByMode.add(new FreePlayButton((int) (width*0.6),blackDivider4,blackDivider5));

              break;
            }
            case 2:{
                BlackDivider blackDivider1 = new BlackDivider((int) (width*0.1),1,true);
                BlackDivider blackDivider2 = new BlackDivider((int) (width*0.3),2,true);
                BlackDivider blackDivider3 = new BlackDivider((int) (width*0.5),3,true);
                materialsByMode.add(blackDivider1);
                materialsByMode.add(blackDivider2);
                materialsByMode.add(blackDivider3);
                materialsByMode.add(new SpiralAbyssButton(0,blackDivider1,blackDivider2));
                materialsByMode.add(new BloodmarkConquestButton((int) (width*0.2),blackDivider2,blackDivider3));




                break;
            }




        }



    }
}
