package com.wg.gichess.chess.rolechess.mondstadt;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.roleregion.Mondstadt;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;
@AutoRegisterRole(
        key = "lisa"
)
public class Lisa extends Mondstadt {

    public Lisa(Coord coord, int level , int constellation, int skin) {
        super(coord,level,constellation,skin);

        setMaxResonanceEnergy(3)
                .setBaseStats(20, 3)
                .setBaseStats(4, 0, 3, 0)
                .setTypes(ElementType.ELECTRO, WeaponType.CATALYST, MovementType.FLY)
                .setInformation("lisa", "丽莎", 3, 3)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                         §d丽莎 蔷薇魔女§r
                        慵懒而博学的图书管理员，须弥教令院「两百年一见」的天才毕业生。
                        \s
                        武器：法器
                        属性：雷元素
                        攻击力：4
                        生命值：20
                        元素能量：3
                        协奏能量：3
                        \s
                         §6§o移动·飞行§r
                        朝十字方向飞行，至多移动3格
                        \s
                         §6§o战技·苍雷§r
                        对3*3范围内所有敌人造成100%攻击力的雷元素伤害
                        \s
                         §6§o爆发·蔷薇的雷光§r
                        进入爆发状态，对3*3范围内所有敌人造成100%攻击力的雷元素伤害，每次敌方回合结束时，再触发一次，当丽莎的元素能量达到上限的50%时，解除爆发状态。消耗3点元素能量，脱手技
                        \s
                         §6§o领袖·无限的电回路§r
                        出战阶段结束时,我方所有角色获得1点元素能量
                        \s
                         §6§o延奏·电感余震§r
                        晕眩所选协奏角色3*3范围内所有敌人1个敌方回合，所选协奏角色若为自己，范围提升为5*5
                        \s
                         §6§o变奏·脉冲的魔女§r
                        晕眩3*3范围内所有敌人1个敌方回合
                        \s
                         §b1命 等离态的落雷§r
                        §6§o战技·苍雷§r范围提升为5*5
                        \s
                         §b2命 等离态的落雷Ⅱ§r
                        §6§o战技·苍雷§r范围提升为7*7
                        \s
                         §b3命 等离态的落雷Ⅲ§r
                        §6§o战技·苍雷§r范围提升为9*9
                        """;
    }





}
