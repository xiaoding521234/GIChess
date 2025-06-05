package com.wg.gichess.chess.objectchess;

import com.wg.gichess.chess.BottomChess;
import com.wg.gichess.chess.ChessType;
import com.wg.gichess.chess.Coord;

public class ObjectChess extends BottomChess {
    public String id;

    public ObjectChess(Coord coord, String id) {
        super(coord, ChessType.OBJECT);
        this.id = id;
    }
}
