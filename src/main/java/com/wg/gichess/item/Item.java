package com.wg.gichess.item;

import com.wg.gichess.material.Material;

public class Item {
    public String id;
    public int count;
    public int star;
    public Material material;


    public Item(String id, int count, int star, Material material){
        this.id = id;
        this.count = count;
        this.star = star;
        this.material = material;
    }



}
