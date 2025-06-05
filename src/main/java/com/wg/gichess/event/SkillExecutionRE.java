package com.wg.gichess.event;

import com.wg.gichess.Event;
import com.wg.gichess.chess.rolechess.BottomSkill;

public class SkillExecutionRE extends Event {
        BottomSkill bottomSkill;


        public SkillExecutionRE(BottomSkill bottomSkill){
            this.bottomSkill = bottomSkill;
        }

    }