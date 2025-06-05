package com.wg.gichess.chess.rolechess;


import com.wg.gichess.chess.Coord;
import com.wg.gichess.material.Material;

import java.util.List;

public interface Skill  {


    String getId();
    String getDisplayName();
    String getDescription();
    Material getIcon();
    boolean allowWork();
    boolean hasWork();
    SkillType getSkillType();
    TriggerType getTriggerType();



    void init() ;//执行
    void skillEffect(Coord coord,List<Coord> coords) ;//技能效果
    void onClick(Coord coord);//在棋盘上的点击
    void skillOver();//技能使用完毕
    boolean canWork();//技能能否使用


    void onChoose();//被选中时
    void onCancel();//取消技能
    void onInterrupt();//打断技能









}
