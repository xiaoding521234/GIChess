package com.wg.gichess.material.battlelobbyscreen;

import com.wg.gichess.material.Material;

public class OnlinePvPButton extends BattleLobbyButton {
    public OnlinePvPButton(int x, BlackDivider blackDividerL, BlackDivider blackDividerR) {
        super("onlinepvpbutton", "联机对战",
                () -> {


                }
                , x, blackDividerL, blackDividerR);

    }
}
