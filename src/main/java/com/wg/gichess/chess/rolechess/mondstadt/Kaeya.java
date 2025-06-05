package com.wg.gichess.chess.rolechess.mondstadt;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.roleregion.Mondstadt;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;
@AutoRegisterRole(
        key = "kaeya"
)
public class Kaeya extends Mondstadt {

    public Kaeya(Coord coord, int level , int constellation, int skin) {
        super(coord,level,constellation,skin);

        setMaxResonanceEnergy(2)
                .setBaseStats(20, 3)
                .setBaseStats(4, 0, 3, 0)
                .setTypes(ElementType.CRYO, WeaponType.SWORDONEHAND, MovementType.WALK)
                .setInformation("kaeya", "凯亚", 3, 2)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                 §b凯亚 寒风剑士§r
                异国面容的剑斗士，西风骑士团的头脑派人物。
                \s
                武器：单手剑
                属性：冰元素
                攻击力：4
                生命值：20
                元素能量：3
                协奏能量：2
                \s
                 §6§o移动·步行§r
                朝十字方向步行，至多移动3格
                \s
                 §6§o战技·霜袭§r
                朝十字方向其一刺出寒气，对前两格所有敌人造成100%攻击力的冰元素伤害
                \s
                 §6§o爆发·凛冽轮舞§r
                进入爆发状态，立刻对3*3范围内所有敌人造成50%攻击力的冰元素伤害，每次敌方回合结束，再触发一次，当凯亚的元素能量达到上限的50%时，解除爆发状态。消耗3点元素能量，脱手技
                \s
                 §6§o领袖·隐藏的实力§r
                出战阶段结束时，队伍中所有步行移动的角色，移速永久提升1
                \s
                 §6§o延奏·隐藏的实力§r
                所选协奏角色若为步行，移速提升2，持续1个我方回合
                \s
                 §6§o变奏·冷血之剑§r
                凯亚增加2点生命值
                \s
                 §b1命 卓越的血脉§r
                §6§o变奏·冷血之剑§r增加生命值提升为4
                \s
                 §b2命 卓越的血脉Ⅱ§r
                §6§o变奏·冷血之剑§r增加生命值提升为6
                \s
                 §b3命 轮旋的冰凌§r
                §6§o爆发·凛冽轮舞§r范围提升为5*5
                """;
    }

}
