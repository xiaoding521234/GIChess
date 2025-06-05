package com.wg.gichess.material.bagscreen;

import com.wg.gichess.material.Material;

public class DarkenTop extends Material {
    public DarkenTop() {
        super("textures/bagscreen/darken.png");
        widthh = width;
        heightt = height/11;
    }


    @Override
    public void render() {

        guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                x, y,
                0, 0,
                widthh,heightt,
                widthh,heightt);


    }
}
