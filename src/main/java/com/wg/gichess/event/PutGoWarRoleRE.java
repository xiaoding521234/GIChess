package com.wg.gichess.event;

import com.wg.gichess.Event;
import com.wg.gichess.chess.Coord;

public class PutGoWarRoleRE extends Event {

    public Coord coord;
    public PutGoWarRoleRE(Coord coord){
        this.coord = coord;
    }
}
