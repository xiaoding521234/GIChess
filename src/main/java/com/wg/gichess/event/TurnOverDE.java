package com.wg.gichess.event;

import com.wg.gichess.Event;

public class TurnOverDE extends Event {

    public TurnOverRE event;
    public TurnOverDE(TurnOverRE event){
        this.event = event;
    }
}
