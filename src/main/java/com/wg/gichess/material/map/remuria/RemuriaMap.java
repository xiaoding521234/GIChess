package com.wg.gichess.material.map.remuria;

import com.wg.gichess.material.map.Map;

public class RemuriaMap extends Map {
    public RemuriaMap() {
        super("textures/map/remuria/remuriamap.png","旧日之海");
        setSize((int) (0.94* width), (int) (0.94 * 0.449 *width));
        setScrollXY(0f,-heightt/4f);
    }
}
