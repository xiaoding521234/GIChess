package com.wg.gichess.chess.rolechess.mondstadt;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.roleregion.Mondstadt;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;
@AutoRegisterRole(
        key = "noelle"
)
public class Noelle extends Mondstadt {

    public Noelle(Coord coord, int level , int constellation, int skin) {
        super(coord,level,constellation,skin);

        setMaxResonanceEnergy(3)
                .setBaseStats(20, 3)
                .setBaseStats(6, 0, 1, 0)
                .setTypes(ElementType.GEO, WeaponType.SWORDTWOHAND, MovementType.WALK)
                .setInformation("noelle", "诺艾尔", 3, 2)
                .build();

    }

    @Override
    public String getIntroduce() {
        return """
                         §6诺艾尔 未授勋之花§r
                        「西风骑士团」的可靠女仆，梦想有天能成为正式的骑士。
                        \s
                        武器：双手剑
                        属性：岩元素
                        攻击力：6
                        生命值：20
                        元素能量：3
                        协奏能量：3
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动1格
                        \s
                         §6§o战技·大扫除§r
                        朝十字方向其一蓄力，1次敌方回合结束后，对前方3*3范围内所有敌人造成150%攻击力的岩元素伤害
                        \s
                         §6§o爆发·要一尘不染才行§r
                        3*3范围内所有我方角色增加1点护盾值
                        \s
                         §6§o领袖·全心全意§r
                        出战阶段结束时，在3*3范围内的我方角色，增加1点韧性
                        \s
                         §6§o延奏·干净利落§r
                        如果此时处于§6§o大扫除§r的蓄力状态，立刻猛击
                        \s
                         §6§o变奏·护心铠§r
                        诺艾尔增加1点护盾值
                        \s
                         §b1命 女仆不会受伤§r
                        诺艾尔永久增加1点防御力
                        \s
                         §b2命 骑士团扫除专家§r
                        §6§o战技·大扫除§r倍率提升为250%
                        \s
                         §b3命 之后会扫干净的§r
                        诺艾尔具有的护盾每次被击破，还会为自己增加1点协奏能量
                        """;
    }
}
