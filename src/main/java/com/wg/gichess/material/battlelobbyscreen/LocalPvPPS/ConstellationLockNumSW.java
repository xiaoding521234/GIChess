package com.wg.gichess.material.battlelobbyscreen.LocalPvPPS;

import com.wg.gichess.User;
import com.wg.gichess.material.tutorialscreen.SliderWidget;

public class ConstellationLockNumSW extends SliderWidget {

    public ConstellationLockNumSW(float xf,float yf){
        super("命座上限",width*0.25f,xf,yf);
        slider.setStepMode(0,3);
        slider.setValue(User.constellationLockNum);
        slider.setOnValueChanged(
                (integer)->{
                    User.constellationLockNum = integer;
                    slider.s = String.valueOf(integer);

                }
        );


    }

    @Override
    public void reset() {
        slider.setValue(3);
    }
}
