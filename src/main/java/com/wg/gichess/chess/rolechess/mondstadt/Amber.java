package com.wg.gichess.chess.rolechess.mondstadt;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.*;
import com.wg.gichess.chess.rolechess.roleregion.Mondstadt;
import com.wg.gichess.material.skill.GI;

import java.util.List;

@AutoRegisterRole(
        key = "amber"
)
public class Amber extends Mondstadt {

    public Amber(Coord coord,int level ,int constellation, int skin) {
        super(coord,level,constellation,skin);

        setMaxResonanceEnergy(3)
                .setBaseStats(20, 2)
                .setBaseStats(5, 0, 3, 0)
                .setTypes(ElementType.PYRO, WeaponType.GAUNTLET, MovementType.WALK)
                .setInformation("amber", "安柏", 3, 3)
                .addTag(RoleTag.ANEMO_BLESSING,RoleTag.NEWBIE,
                        RoleTag.STEALTH_BREAK,RoleTag.LONG_RANGE,RoleTag.SPEED_BUFF)
                .build();

        skills.add(new MoveSkill(this));
        skills.add(new ArcherySkill(this));
        skills.add(new ArrowstormSkill(this));
        skills.add(new SkywardSkill(this));
        skills.add(new BullseyeBarrageSkill(this));
        skills.add(new ReconnaissanceSweepSkill(this));




    }


    @Override
    public String getIntroduce() {
        return """
                         §c安伯 飞行冠军§r
                        """+getTagsDisplay() +"""
                        永远充满活力的女孩，骑士团最优秀，同时也是最后的侦察骑士
                        \s
                        武器：弓
                        属性：火元素
                        攻击力：5
                        生命值：20
                        元素能量：2
                        协奏能量：3
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·神射手§r
                        朝十字方向其一锁定首个敌人，射出一支箭矢，造成100%攻击力的火元素伤害
                        \s
                         §6§o爆发·箭雨§r
                        朝十字方向其一射出大量箭矢，锁定所有敌人，造成100%攻击力的火元素伤害。消耗2点元素能量
                        \s
                         §6§o领袖·飞行冠军§r
                        出战阶段结束时,队伍中所有飞行移动的角色，移速永久提升1
                        \s
                         §6§o延奏·百发百中!§r
                        所选协奏角色武器若为弓，则其攻击力永久增加1。消耗3点协奏能量
                        \s
                         §6§o变奏·全面侦查§r
                        解除全场所有敌人隐身状态
                        \s
                         §b1命 一触即发§r
                        箭矢锁定的敌人若已附着火元素，则额外造成2点伤害
                        \s
                         §b2命 改良箭矢§r
                        队伍中每有一个火元素角色，安伯的基础攻击力就永久增加1
                        \s
                         §b3命 疾如野火§r
                        安伯移速永久提升2点
                        """;
    }



    public static class MoveSkill extends BottomSkill {


        public MoveSkill(RoleChess roleChess) {
            super(roleChess);
            setIcon(new GI(this,"moveskill.png"));
        }

        @Override
        public String getId() {
            return "moveskill";
        }

        @Override
        public String getDisplayName() {
            return "§6§o移动·步行§r";
        }

        @Override
        public String getDescription() {
            return "朝十字方向步行，至多移动3格";
        }

        @Override
        public SkillType getSkillType() {
            return SkillType.MOVEMENT;
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.ACTIVE;
        }

        @Override
        public void init() {

        }

        @Override
        public void skillEffect(Coord coord, List<Coord> coords) {

        }

        @Override
        public void onClick(Coord coord) {

        }

        @Override
        public void skillOver() {

        }

        @Override
        public boolean canWork() {
            return true;
        }

        @Override
        public void onChoose() {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onInterrupt() {

        }

    }

    public static class ArcherySkill extends BottomSkill{
        public ArcherySkill(RoleChess roleChess) {
            super(roleChess);
            setIcon(new GI(this,"archeryskill.png"));
        }

        @Override
        public String getId() {
            return "archeryskill";
        }

        @Override
        public String getDisplayName() {
            return "§6§o战技·神射手§r";
        }

        @Override
        public String getDescription() {
            return "朝十字方向其一锁定首个敌人，射出一支箭矢，造成100%攻击力的火元素伤害";
        }

        @Override
        public SkillType getSkillType() {
            return SkillType.TACTIC;
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.ACTIVE;
        }

        @Override
        public void init() {

        }


        @Override
        public void skillEffect(Coord coord, List<Coord> coords) {

        }

        @Override
        public void onClick(Coord coord) {

        }

        @Override
        public void skillOver() {

        }

        @Override
        public void onChoose() {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onInterrupt() {

        }


    }

    public static class ArrowstormSkill extends BottomSkill {
        public ArrowstormSkill(RoleChess roleChess) {
            super(roleChess);
            setIcon(new GI(this,"arrowstormskill.png"));
        }

        @Override
        public String getId() {
            return "arrowstormskill";
        }

        @Override
        public String getDisplayName() {
            return "§6§o爆发·箭雨§r";
        }

        @Override
        public String getDescription() {
            return "朝十字方向其一射出大量箭矢，锁定所有敌人，造成100%攻击力的火元素伤害。消耗2点元素能量";
        }

        @Override
        public SkillType getSkillType() {
            return SkillType.BURST;
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.ACTIVE;
        }

        @Override
        public void init() {

        }


        @Override
        public void skillEffect(Coord coord, List<Coord> coords) {

        }

        @Override
        public void onClick(Coord coord) {

        }

        @Override
        public void skillOver() {

        }

        @Override
        public void onChoose() {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onInterrupt() {

        }

    }

    public static class SkywardSkill extends BottomSkill {
        public SkywardSkill(RoleChess roleChess) {
            super(roleChess);
            setIcon(new GI(this,"soarskill.png"));
        }

        @Override
        public String getId() {
            return "skywardskill";
        }

        @Override
        public String getDisplayName() {
            return "§6§o领袖·飞行冠军§r";
        }

        @Override
        public String getDescription() {
            return "出战阶段结束时,队伍中所有飞行移动的角色，移速永久提升1";
        }

        @Override
        public SkillType getSkillType() {
            return SkillType.LEADER;
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.PASSIVE;
        }

        @Override
        public void init() {

        }

        @Override
        public void skillEffect(Coord coord, List<Coord> coords) {

        }

        @Override
        public void onClick(Coord coord) {

        }

        @Override
        public void skillOver() {

        }

        @Override
        public void onChoose() {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onInterrupt() {

        }

    }

    public static class BullseyeBarrageSkill extends BottomSkill {
        public BullseyeBarrageSkill(RoleChess roleChess) {
            super(roleChess);
            setIcon(new GI(this,"preciseskill.png"));
        }

        @Override
        public String getId() {
            return "bullseyebarrageskill";
        }

        @Override
        public String getDisplayName() {
            return "§6§o延奏·百发百中!§r";
        }

        @Override
        public String getDescription() {
            return "所选协奏角色武器若为弓，则其攻击力永久增加1。消耗3点协奏能量";
        }

        @Override
        public SkillType getSkillType() {
            return SkillType.ECHO;
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.ACTIVE;
        }

        @Override
        public void init() {

        }

        @Override
        public void skillEffect(Coord coord, List<Coord> coords) {

        }

        @Override
        public void onClick(Coord coord) {

        }

        @Override
        public void skillOver() {

        }

        @Override
        public void onChoose() {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onInterrupt() {

        }

    }

    public static class ReconnaissanceSweepSkill extends BottomSkill {
        public ReconnaissanceSweepSkill(RoleChess roleChess) {
            super(roleChess);
            setIcon(new GI(this,"revealskill.png"));
        }

        @Override
        public String getId() {
            return "reconnaissancesweepskill";
        }

        @Override
        public String getDisplayName() {
            return "§6§o变奏·全面侦查§r";
        }

        @Override
        public String getDescription() {
            return "解除全场所有敌人隐身状态量";
        }

        @Override
        public SkillType getSkillType() {
            return SkillType.VARIATION;
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.PASSIVE;
        }

        @Override
        public void init() {

        }

        @Override
        public void skillEffect(Coord coord, List<Coord> coords) {

        }

        @Override
        public void onClick(Coord coord) {

        }

        @Override
        public void skillOver() {

        }

        @Override
        public void onChoose() {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onInterrupt() {

        }

    }


}
