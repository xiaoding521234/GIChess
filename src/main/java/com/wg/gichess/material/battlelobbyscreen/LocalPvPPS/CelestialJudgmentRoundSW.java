package com.wg.gichess.material.battlelobbyscreen.LocalPvPPS;

import com.wg.gichess.User;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.tutorialscreen.SliderWidget;

public class CelestialJudgmentRoundSW extends SliderWidget {
    public CelestialJudgmentRoundSW(float xf,float yf){
        super("天理裁决回合",width*0.25f,xf,yf);
        slider.setStepMode(10,100);
        slider.setValue(User.celestialJudgmentRound);
        slider.setOnValueChanged(
                (integer)->{
                    User.celestialJudgmentRound = integer;
                    slider.s = String.valueOf(integer);

                }
        );


    }

    @Override
    public void reset() {
        slider.setValue(30);
    }
}
