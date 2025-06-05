package com.wg.gichess.material.map.theseven.mondstadt;


import com.wg.gichess.material.map.Map;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.material.map.Waypoint;

public class Mondstadtgate extends Waypoint {

    public Mondstadtgate(Map map) {
        super(map.widthh*0.48f, map.heightt*0.32f, PositionType.MONDSTADTGATE);
        this.text =
                """
                            §6蒙德城大门外§r
                           猎鹿人餐馆的香味已经飘到门口了！
                           （再次点击锚点传送）
                           """;

    }

}