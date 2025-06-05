package com.wg.gichess.material.battlelobbyscreen.LocalPvPPS;

import com.wg.gichess.User;
import com.wg.gichess.material.tutorialscreen.SliderWidget;

public class WarRoleNumSW extends SliderWidget {
    public WarRoleNumSW(float xf,float yf){
        super("角色出战数",width*0.25f,xf,yf);
        slider.setStepMode(1,6);
        slider.setValue(User.goWarRoleNum);
        slider.setOnValueChanged(
                (integer)->{
                    User.goWarRoleNum = integer;
                    slider.s = String.valueOf(integer);

                }
        );

    }

    @Override
    public void reset() {
        slider.setValue(4);
    }
}
