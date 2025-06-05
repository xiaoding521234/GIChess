package com.wg.gichess.chess.rolechess.hilichurls;

import com.wg.gichess.RegionType;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.Element;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.WeaponType;


@AutoRegisterRole(key = "blazingaxemitachurl")
public class BlazingAxeMitachurl extends Hilichurl {
    public BlazingAxeMitachurl(Coord coord, int level , int constellation, int skin) {

        super(coord,level,constellation,skin);
        setBaseStats(20,3)
                .setBaseStats(6,0,3,0)
                .setTypes(ElementType.PYRO,WeaponType.SWORDTWOHAND, MovementType.WALK)
                .setInformation("blazingaxemitachurl","火斧丘丘暴徒",2,1)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                         §c火斧丘丘暴徒§r
                        手持长柄大斧的丘丘人大块头。攻击大开大合，具有强大的破坏力。
                        \s
                        武器：双手剑
                        属性：火元素
                        攻击力：6
                        生命值：20
                        元素能量：3
                        \s
                         §6§o移动§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·二连劈§r
                        朝十字方向其一，对前2格所有敌人劈砍造成100%攻击力的火元素伤害，然后蓄力，下一敌方回合结束，再劈砍一次
                        \s
                         §6§o爆发·旋转攻击§r
                        对5*5范围内所有敌人造成150%攻击力的火元素伤害。消耗3点元素能量
                        \s
                         §6§o领袖·不死诅咒§r
                        我方所有角色永久提升2点最大生命值
                        \s
                         §b1命 冲击波§r
                        §6§o战技·二连劈§r第一段还会击退敌人1格，同时晕眩1个敌方回合
                        \s
                         §b2命 烈刃附魔§r
                        永久提升2点元素精通
                        \s
                         §b3命 不详的面具§r
                        永久提升10点最大生命值
                        """;
    }
}
