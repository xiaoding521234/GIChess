package com.wg.gichess.material.map.theseven.inazuma;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.map.Map;

public class InazumaMap extends Map {
    public InazumaMap() {
        super("textures/map/inazuma/inazumamap.png","稻妻");
        setSize((int) (2.96* Material.width), (int) (2.96* 1.08* Material.width));
        setScrollXY(widthh/3f,heightt/3f);

    }
}
