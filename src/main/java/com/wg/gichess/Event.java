package com.wg.gichess;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.rolechess.BottomSkill;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.event.*;

import java.util.ArrayList;
import java.util.List;

public class Event {


    public boolean allowWork = true;
    public boolean hasWork = false;

    public boolean isPaused = false;
    public boolean isActive = true;

    public Event parentEvent = null;
    public List<Event> childEvents = new ArrayList<>();
    public int activeChild =0;
    public Subscribers currentSubscribers = null;
    public EventBus eventBus = null;




    @SuppressWarnings("unchecked")
    private <T extends Event> void handleSafely(Subscriber<T> subscriber, Event event) {

        subscriber.handleEvent((T) event);
    }



    /**
     * 点击事件
     */
    public ClickE clickE(){
        return new ClickE();
    }
    /**
     * 放置出战角色请求事件
     */
    public PutGoWarRoleRE putGoWarRoleRE(Coord coord){
        return new PutGoWarRoleRE(coord);
    }
    /**
     * 放置出战角色完成事件
     */
    public PutGoWarRoleDE putGoWarRoleDE(PutGoWarRoleRE event){
        return new PutGoWarRoleDE(event);
    }
    /**
     * 回合结束请求事件
     */
    public TurnOverRE turnOverRE(TurnOverType turnOverType){
        return new TurnOverRE(turnOverType);
    }
    /**
     * 回合结束完成事件
     */
    public TurnOverDE turnOverDE(TurnOverRE turnOverRE){
        return new TurnOverDE(turnOverRE);
    }



    /**
     * 技能执行请求事件
     */
    public SkillExecutionRE skillExecutionRE(BottomSkill bottomSkill){
        return new SkillExecutionRE(bottomSkill);
    }
    /**
     * 伤害请求事件
     */
    public DamageRE damageRE(RoleChess source, RoleChess target){
        //return new DamageRE(source,target);
        return null;
    }



}
