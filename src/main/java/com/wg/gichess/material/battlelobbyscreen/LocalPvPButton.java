package com.wg.gichess.material.battlelobbyscreen;

import com.wg.gichess.screen.battlelobbyscreen.LocalPvPPS;
import net.minecraft.client.Minecraft;

public class LocalPvPButton extends BattleLobbyButton {

    public LocalPvPButton (int x,BlackDivider blackDividerL,BlackDivider blackDividerR) {
        super("localpvpbutton","同屏对战",
                ()->{
                    Minecraft.getInstance().setScreen(new LocalPvPPS(true));




                }
                ,x,blackDividerL,blackDividerR );

    }

}
