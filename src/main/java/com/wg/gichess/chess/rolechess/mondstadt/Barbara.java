package com.wg.gichess.chess.rolechess.mondstadt;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.roleregion.Mondstadt;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;

@AutoRegisterRole(
        key = "barbara"
)
public class Barbara extends Mondstadt {

    public Barbara(Coord coord, int level , int constellation, int skin) {
        super(coord,level,constellation,skin);

        setMaxResonanceEnergy(3)
                .setBaseStats(20, 7)
                .setBaseStats(1, 0, 3, 0)
                .setTypes(ElementType.HYDRO, WeaponType.CATALYST, MovementType.WALK)
                .setInformation("barbara", "芭芭拉", 3, 5)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                         §9芭芭拉 闪耀偶像§r
                        蒙德城的大家都喜欢芭芭拉。「偶像」这个词是她从一本杂志里看到的。
                        \s
                        武器：法器
                        属性：水元素
                        攻击力：1
                        生命值：20
                        元素能量：7
                        协奏能量：3
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·演唱，开始♪§r
                        被动技能。每次我方回合结束，为3*3范围内所有我方角色增加1点生命值，所有敌方角色造成100%攻击力的水元素伤害
                        \s
                         §6§o爆发·将一切美好献给你§r
                        将场上一个已倒下的我方角色复苏，并为其增加1点生命值。消耗5点元素能量
                        \s
                         §6§o领袖·明日之星§r
                        出战阶段结束时,我方所有角色除芭芭拉外，治疗加成提升1点
                        \s
                         §6§o延奏·心意注入§r
                        所选协奏角色增加2点生命值
                        \s
                         §6§o变奏·元气迸发§r
                        全场所有我方角色增加2点生命值
                        \s
                         §b1命 彩色歌谣§r
                        §6§o战技·演唱，开始♪§r每次触发，芭芭拉增加1点元素能量，1点协奏能量
                        \s
                         §b2命 努力即魔法§r
                        队伍中每有一个水元素角色，芭芭拉生命值上限永久提升5点,同时增加5点生命值
                        \s
                         §b3命 纯真的羁绊§r
                        §6爆发·将一切美好献给你§r§0还会为芭芭拉增加1点生命值§r
                        """;
    }
}
