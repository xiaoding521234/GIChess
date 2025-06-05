package com.wg.gichess.material.map.theseven.fontaine;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.map.Map;

public class FontaineMap extends Map {
    public FontaineMap() {
        super("textures/map/fontaine/fontainemap.png","枫丹");
        setSize((int) (1.618* Material.width), (int) (1.618* 1.55* Material.width));
        setScrollXY(widthh/4f,heightt/4f);

    }
}
