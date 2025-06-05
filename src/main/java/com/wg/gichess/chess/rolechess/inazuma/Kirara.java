package com.wg.gichess.chess.rolechess.inazuma;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.*;
import com.wg.gichess.chess.rolechess.roleregion.Inazuma;

@AutoRegisterRole(
        key = "kirara"
)
public class Kirara extends Inazuma {
    public Kirara(Coord coord, int level , int constellation, int skin) {
        super(coord,level,constellation,skin);

        setBaseStats(20,7)
                .setBaseStats(4,0,2,0)
                .setTypes(ElementType.DENDRO,WeaponType.GAUNTLET, MovementType.JUMP)
                .setInformation("kirara","绮良良",3,3)
                .build();



    }

    @Override
    public String getIntroduce() {
        return """
                         §a绮良良 檐宇猫游§r
                        稻妻快递公司「狛荷屋」的送货员，一只超爱工作、超向往人类世界的「猫又」喵~
                        \s
                        武器：臂铠
                        属性：草元素
                        攻击力：4
                        生命值：20条命喵！
                        元素能量：7颗小鱼干
                        永恒领域：神天
                        \s
                         §6§o移动·跳跃§r
                        可以跳到5*5格子内的空地喵~但是开启大招时会变成胖猫猫跳不动啦！
                        \s
                         §6§o战技·呜喵町飞足§r
                        被动技能喵~永远不会摔伤，猫有九条命可不是假的喵！
                        \s
                         §6§o爆发·火力大喵，正义执行§r
                        变身超凶快递猫猫！获得20点护甲喵~永久增加1点防御，要是在神乐同心阵里还能多10点护盾喵！每个坏蛋回合结束都会发射激光biubiubiu~,最近的那个坏蛋要挨两下150%攻击力的猫猫激光！护甲破了就会变回普通猫猫。消耗7颗小鱼干喵~
                        \s
                         §6§o领袖·祟祟猫步§r
                        开局全队隐身喵~能躲1个坏蛋回合。要是在天狐幻戏阵里还能多躲1回合喵！
                        \s
                         §b1命 喵喵步§r
                        在天狐幻戏阵里喵，+1点移速喵！
                        \s
                         §b2命 不要吃我的分喵！§r
                        §6§o爆发·火力大喵，正义执行§r展开纸箱还会让猫猫更抗揍，永久+1点防御喵~
                        \s
                         §b3命 似了喵§r
                        本猫似了，还会为3*3范围内所有角色附着草元素喵~
                        """;
    }
}
