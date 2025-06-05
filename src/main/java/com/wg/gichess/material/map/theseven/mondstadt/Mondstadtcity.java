package com.wg.gichess.material.map.theseven.mondstadt;

import com.wg.gichess.material.map.Map;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.material.map.Waypoint;

public class Mondstadtcity extends Waypoint {
    public Mondstadtcity(Map map) {
        super(map.widthh*0.417f, map.heightt*0.28f, PositionType.MONDSTADTCITY);
        this.text =
                """
                            §6蒙德城内§r
                           99%的旅客都直奔天使的馈赠！
                           （再次点击锚点传送）
                           """;

    }
}
