package com.wg.gichess.chess.rolechess.roleregion;

import com.wg.gichess.RegionType;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.rolechess.RoleChess;

public class Hilichurls extends RoleChess {
    public Hilichurls(Coord coord, int level, int constellation, int skin) {
        super(coord, level, constellation ,skin);
        setRegionType(RegionType.HILICHURLS);
    }
}
