package com.wg.gichess.material.map.theseven.mondstadt;

import com.wg.gichess.material.map.Map;

public class MondstadtMap extends Map {
    public MondstadtMap() {
        super("textures/map/mondstadt/mondstadtmap.png","蒙德");
        setSize(width*2, (int) (width*2*0.77));
        setScrollXY(widthh/4f,heightt/4f);
        waypoints.add(new Startingpoint(this));
        waypoints.add(new Mondstadtgate(this));
        waypoints.add(new Mondstadtcity(this));
        waypoints.add(new Windrise(this));

    }


}
