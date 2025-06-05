package com.wg.gichess.chess.rolechess.liyue;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.roleregion.Liyue;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;


@AutoRegisterRole(
        key = "xiangling"
)
public class Xiangling extends Liyue {
    public Xiangling(Coord coord,int level ,int constellation, int skin) {
        super(coord,level,constellation,skin);


        setBaseStats(20, 3)
                .setBaseStats(4, 0, 1, 0)
                .setTypes(ElementType.PYRO, WeaponType.POLEARM, MovementType.FLY)
                .setInformation("xiangling", "香菱", 3, 1)
                .build();

    }

    @Override
    public String getIntroduce() {
        return """
                         §c香菱 万民百味§r
                        来自璃月，名声在外的少女厨师，对料理之道极具热情，拿手麻辣菜肴堪称一绝。
                        \s
                        武器：长柄武器
                        属性：火元素
                        攻击力：4
                        生命值：20
                        元素能量：3
                        \s
                         §6§o移动·飞行§r
                        朝X字方向飞行，至多移动3格
                        \s
                         §6§o战技·锅巴出击§r
                        朝十字方向其一唤出喷火的锅巴，对前3格所有敌人造成100%攻击力的火元素伤害
                        \s
                         §6§o爆发·旋火轮§r
                        甩出风火轮，对7*7至9*9范围上的所有敌人造成100%攻击力的火元素伤害。消耗3点元素能量
                        \s
                         §6§o领袖·万民堂大厨§r
                        我方回合结束时,如果香菱在本次我方回合没有消耗行动点，香菱烹饪美食，为距离最近的我方角色增加§6§o道具·万民堂水煮鱼§r：主动技能，增加1点生命值和永久1点攻击力。同一角色不能持有多个§6§o道具·万民堂水煮鱼§r
                        \s
                         §6§o契约·文火慢煨§r
                        内容：被契约者3个敌方回合结束前不能使用元素爆发
                        惩罚：被契约者受到其40%最大生命值的火元素伤害
                        奖赏：被契约者若为火元素角色，永久提升2点攻击力
                        \s
                         §b1命 外酥里嫩§r
                        §6§o锅巴出击§r对第一格的敌人造成150%攻击力的火元素伤害
                        \s
                         §b2命 武火急烹§r
                        第一个我方回合内，香菱移速提升10
                        \s
                         §b3命 大火宽油§r
                        §6§o战技·锅巴出击§r，§6§o爆发·旋火轮§r的火元素伤害提升1层
                        """;
    }
}
