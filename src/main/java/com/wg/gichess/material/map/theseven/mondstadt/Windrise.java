package com.wg.gichess.material.map.theseven.mondstadt;

import com.wg.gichess.material.map.Map;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.material.map.Waypoint;

public class Windrise extends Waypoint {
    public Windrise(Map map) {
        super(map.widthh*0.525f, map.heightt*0.393f, PositionType.WINDRISE);
        this.text =
                """
                            §6风起地§r
                           西风骑士团温馨提示：
                           1.此地收到过多起「盗酒」投诉
                           2.当心这里有直达酒馆的风场
                           """;

    }

}
