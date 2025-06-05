package com.wg.gichess.material.bagscreen;

import com.wg.gichess.material.Material;

public class Bag extends Material {
    public Bag(int x, int y) {
        super("textures/bagscreen/bag.png", x, y);
        widthh = (int)(width * 0.04);
        heightt = widthh;
    }



}
