package com.wg.gichess.event;

import com.wg.gichess.Event;

public class TurnOverRE extends Event {
    public TurnOverType turnOverType;

    public TurnOverRE(TurnOverType turnOverType){
        this.turnOverType = turnOverType;
    }

}
