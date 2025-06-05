package com.wg.gichess.material;

public class Movable extends Material {

    public int vX;
    public int vY;
    public float accX;
    public float accY;
    public float difX;
    public float difY;

    public int vF = 33;
    public float accF;
    public int fCount;
    public int f;

    public boolean isFirst = true;



    public Movable(String id,int x,int y,boolean isFirst) {
        super(id,x,y);
        this.isFirst = isFirst;
    }

    public Movable(String id,boolean isFirst) {
        super(id);
        this.isFirst = isFirst;
    }



    public void accumulateX(float delta){
        if(delta/20<0.04){
            accX += vX * delta / 20;
            x += (int) accX;
            accX -= (int) accX;
        }

    }

    public void accumulateX(int x,float delta){
        if(delta/20<0.04){
            accX += vX * delta / 20;
            x += (int) accX;
            accX -= (int) accX;
        }

    }


    public void accumulateY(float delta){
        if(delta/20<0.04){
            accY += vY * delta / 20;
            y += (int) accY;
            accY -= (int) accY;
        }

    }

    public void accumulateXY(float delta){
        if(delta/20<0.04){
            accX += vX * delta / 20;
            x += (int) accX;
            accX -= (int) accX;

            accY += vY * delta / 20;
            y += (int) accY;
            accY -= (int) accY;
        }



    }

    public void accumulateF(float delta){
        if(delta/20<0.04){
            accF += vF * delta /20  ;
            f += (int) accF;
            accF -= (int) accF;
        }

    }


}
