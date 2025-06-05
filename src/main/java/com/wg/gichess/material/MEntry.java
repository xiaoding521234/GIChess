package com.wg.gichess.material;

import com.wg.gichess.material.bagscreen.RoleMList;

public class MEntry {
    public Material material;

    public MEntry(Material material){
        this.material = material;
    }


    public void render(int x,int y){
        material.x = x;
        material.y = y;
        material.render();
    }

    public void click(){}


}
