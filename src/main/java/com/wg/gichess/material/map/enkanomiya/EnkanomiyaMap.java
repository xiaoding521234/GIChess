package com.wg.gichess.material.map.enkanomiya;

import com.wg.gichess.material.map.Map;

public class EnkanomiyaMap extends Map {
    public EnkanomiyaMap() {
        super("textures/map/enkanomiya/enkanomiyamap.png","渊下宫");
        setSize((int) (1.5*width), (int) (1.5*0.98 *width));
        setScrollXY(widthh/4f,heightt/4f);


    }

}
