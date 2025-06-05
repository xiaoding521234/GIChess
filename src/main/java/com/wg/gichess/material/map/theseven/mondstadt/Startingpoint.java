package com.wg.gichess.material.map.theseven.mondstadt;

import com.wg.gichess.material.map.Map;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.material.map.Waypoint;

public class Startingpoint extends Waypoint {

    public Startingpoint(Map map) {
        super(map.widthh*0.623f, map.heightt*0.15f, PositionType.STARTINGPOINT);
        this.text =
                """
                            §6坠星山谷§r
                           旅行者醒来后隐居的地方，不远处就是钓到派蒙的沙滩，也是一切的开始
                           （再次点击锚点传送）
                           """;

    }

}
