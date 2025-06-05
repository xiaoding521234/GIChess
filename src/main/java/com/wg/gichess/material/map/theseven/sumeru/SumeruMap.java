package com.wg.gichess.material.map.theseven.sumeru;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.map.Map;

public class SumeruMap extends Map {
    public SumeruMap() {
        super("textures/map/sumeru/sumerumap.png","须弥");
        setSize((int) (2.82* Material.width), (int) (2.82* 0.97* Material.width));
        setScrollXY(widthh/2f,heightt/3f);


    }
}
