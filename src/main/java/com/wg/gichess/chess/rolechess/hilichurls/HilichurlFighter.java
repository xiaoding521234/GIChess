package com.wg.gichess.chess.rolechess.hilichurls;

import com.wg.gichess.RegionType;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.WeaponType;

@AutoRegisterRole(
        key = "hilichurlfighter"
)
public class HilichurlFighter extends Hilichurl {

    public HilichurlFighter(Coord coord, int level , int constellation, int skin) {

        super(coord,level,constellation,skin);
        setBaseStats(10,2)
                .setBaseStats(2,0,3,0)
                .setTypes(ElementType.PHYSICAL,WeaponType.POLEARM , MovementType.WALK)
                .setInformation("hilichurlfighter","打手丘丘人",1,1)
                .build();

    }

    @Override
    public String getIntroduce() {
        return """
                         打手丘丘人
                        提瓦特荒野中游荡的原始住民，有着最基础文明形态的人形魔物。坏脾气的部落战士，朴实地信奉着肌肉的力量。
                        \s
                        武器：长柄武器
                        属性：物理
                        攻击力：3
                        生命值：10
                        元素能量：2
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·重击§r
                        朝十字方向其一，对第一格敌人重击造成50%攻击力的物理伤害
                        \s
                         §6§o爆发·跳劈§r
                        朝十字方向其一，对第一格敌人重击造成100%攻击力的物理伤害，晕眩一个敌方回合。消耗2点元素能量
                        \s
                         §6§o领袖·不死诅咒§r
                        我方所有角色永久提升1点最大生命值
                        \s
                         §b1命 强力重击§r
                        §6§o战技·重击§r倍率提升为100%
                        \s
                         §b2命 强力跳劈§r
                        §6§o爆发·跳劈§r倍率提升为200%
                        \s
                         §b3命 不详的面具§r
                        永久提升1点最大生命值
                        """;
    }
}
