package com.wg.gichess.material.animation.cell;

import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.Animation;
import com.wg.gichess.material.AnimationGroup;

public class PromptCellA extends Animation {
    public AnimationGroup animationGroup;


    public PromptCellA(AnimationGroup animationGroup){
        this.animationGroup = animationGroup;

        totalTime = 1.2;
    }
}
