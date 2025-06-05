package com.wg.gichess.event;

import com.wg.gichess.Event;
import com.wg.gichess.chess.Coord;

public class PutGoWarRoleDE extends Event {
    public PutGoWarRoleRE event;

    public PutGoWarRoleDE(PutGoWarRoleRE event){
        this.event = event;
    }
}
