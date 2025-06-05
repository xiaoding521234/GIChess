package com.wg.gichess.material.lobbyscreen;

import com.wg.gichess.material.Movable;

public class Road extends Movable {


    public Road(int x, int y,boolean isFirst) {
        super("textures/lobbyscreen/road.png", x, y,isFirst);
    }



    @Override
    public void render() {


        int widthh = (int)(width * 1.4);
        int heightt = (int)(height * 1.4);


        guiGraphics.blit(RENDER_FUNCTION, resourceLocation,
                (int)(x-width*0.2),(int)(y-height*0.34),
                0, 0,
                widthh,heightt,
                widthh,heightt);

    }
}
