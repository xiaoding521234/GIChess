package com.wg.gichess.material;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimationManager {
    public List<Animation> animations = new CopyOnWriteArrayList<>();
    public List<Animation> lockAnimations = new CopyOnWriteArrayList<>();

    public void renderAnimations(){
        if(animations.isEmpty()){
            return;
        }
        animations.forEach(Animation::renderAnimation);
    }

    public void renderLockAnimations(){
        if(lockAnimations.isEmpty()){
            return;
        }
        lockAnimations.getFirst().renderAnimation();
    }


    public void renderAll(){
        renderAnimations();
        renderLockAnimations();
    }

    public boolean remove(Animation animation) {
        if (animation == null) {
            return false;
        }
        boolean removed = false;
        if (animations != null) {
            removed = animations.remove(animation);
        }
        if (lockAnimations != null) {
            removed = removed || lockAnimations.remove(animation);
        }
        return removed;
    }

    public void addAnimations(Animation animation,Runnable onComplete){
        animation.onComplete = onComplete;
        animation.animationManager = this;
        animations.add(animation);
    }

    public void addAnimations(Animation animation){
        addAnimations(animation,null);
    }


    public void addLockAnimations(Animation animation,Runnable onComplete){
        animation.onComplete = onComplete;
        animation.animationManager = this;
        lockAnimations.add(animation);
    }

    public void addLockAnimations(Animation animation){
        addLockAnimations(animation,null);
    }

}
