package com.wg.gichess.material;

import com.wg.gichess.chessboard.Cell;

import java.util.ArrayList;
import java.util.List;

public class AnimationGroup {
    List<Cell> cells = new ArrayList<>();


    public AnimationGroup(Cell... cells){
        this.cells = List.of(cells);
    }

    public AnimationGroup(List<Cell> cells) {
        this.cells = cells;
    }
}
