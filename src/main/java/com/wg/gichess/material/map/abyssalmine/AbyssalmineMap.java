package com.wg.gichess.material.map.abyssalmine;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.map.Map;

public class AbyssalmineMap extends Map {
    public AbyssalmineMap() {
        super("textures/map/abyssalmine/abyssalminemap.png","巨渊矿区");
        setSize((int) (width*0.864), (int) (width*0.864*0.819));
        setScrollXY(0f,0f);
    }
}
