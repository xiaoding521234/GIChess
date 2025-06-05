package com.wg.gichess.material.battlelobbyscreen.LocalPvPPS;

import com.wg.gichess.material.Button;
import com.wg.gichess.material.tutorialscreen.SliderWidget;

import java.util.ArrayList;
import java.util.List;

public class ResetButton extends Button {

    private final List<SliderWidget> sliders;

    public ResetButton(int x, int y, List<SliderWidget> slidersToReset ) {
        super(x, y, "重置");
        setShouldRenderM(this.resetButton);
        this.sliders = slidersToReset;
    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            sliders.forEach(SliderWidget::reset);
            return true;
        }

        return false;
    }
}
