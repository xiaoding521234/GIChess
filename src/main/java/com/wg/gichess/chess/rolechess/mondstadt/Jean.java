package com.wg.gichess.chess.rolechess.mondstadt;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.roleregion.Mondstadt;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;

@AutoRegisterRole(
        key = "jean"
)
public class Jean extends Mondstadt {

    public Jean(Coord coord, int level , int constellation, int skin) {
        super(coord,level,constellation,skin);

        setMaxResonanceEnergy(5)
                .setBaseStats(20, 2)
                .setBaseStats(4, 0, 5, 0)
                .setTypes(ElementType.ANEMO, WeaponType.SWORDONEHAND, MovementType.WALK)
                .setInformation("jean", "琴", 4, 3)
                .build();

    }

    @Override
    public String getIntroduce() {
        return """
                         §3琴 蒲公英骑士§r
                        正直严谨的蒲公英骑士，蒙德西风骑士团的代理团长。
                        \s
                        武器：单手剑
                        属性：风元素
                        攻击力：4
                        生命值：20
                        元素能量：2
                        协奏能量：5
                        \s
                         §6§o移动·引领之风§r
                        朝十字方向步行，至多移动5格，琴的§6§o战技·风压剑§r§0增加1点专属体力
                        \s
                         §6§o战技·风压剑§r
                        朝十字方向其一释放微型的风暴，对第一格的敌人造成50%攻击力的风元素伤害，并击退1格
                        \s
                         §6§o爆发·蒲公英之风§r
                        永久减少5*5范围内所有敌人1点防御力。消耗3点元素能量
                        \s
                         §6§o领袖·守护众人的坚盾§r
                        出战阶段结束时,我方所有角色获得1点护盾
                        \s
                         §6§o延奏·听凭风引§r
                        琴的§6§o战技·风压剑§r增加1点专属体力
                        \s
                         §6§o变奏·顺风而行§r
                        琴的§6§o战技·风压剑§r增加1点专属体力
                        
                        \s
                         §b1命 流转剑脊的暴风§r
                        §6§o战技·风压剑§r击退距离提升为2格
                        \s
                         §b2命 流转剑脊的暴风Ⅱ§r
                        §6§o战技·风压剑§r击退距离提升为3格
                        \s
                         §b3命 流转剑脊的暴风Ⅲ§r
                        §6§o战技·风压剑§r击退距离提升为5格
                        """;
    }
}
