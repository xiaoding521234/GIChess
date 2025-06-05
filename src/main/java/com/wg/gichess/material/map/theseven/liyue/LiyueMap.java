package com.wg.gichess.material.map.theseven.liyue;

import com.wg.gichess.material.map.Map;

public class LiyueMap extends Map {
    public LiyueMap() {
        super("textures/map/liyue/liyuemap.png","璃月");
        setSize((int) (2.66* width), (int) (2.66* 0.82* width));
        setScrollXY(widthh/3f,heightt/3f);

    }
}
