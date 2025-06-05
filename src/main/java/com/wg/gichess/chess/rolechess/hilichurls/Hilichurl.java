package com.wg.gichess.chess.rolechess.hilichurls;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.WeaponType;
import com.wg.gichess.chess.rolechess.roleregion.Hilichurls;


@AutoRegisterRole(
        key = "hilichurl"
)
public class Hilichurl extends Hilichurls {
    public Hilichurl(Coord coord, int level , int constellation, int skin) {

        super(coord,level,constellation,skin);
        setBaseStats(10,2)
                .setBaseStats(2,0,3,0)
                .setTypes(ElementType.PHYSICAL,WeaponType.GAUNTLET ,MovementType.WALK)
                .setInformation("hilichurl","普通丘丘人",1,1)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                         普通丘丘人
                        提瓦特荒野中游荡的原始住民。与人类轮廓相似，却失去了智能与灵性。据记载，在大地上出没超过千年，却没有历史与文明。
                        \s
                        武器：臂铠
                        属性：物理
                        攻击力：2
                        生命值：10
                        元素能量：2
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·挥拳§r
                        朝十字方向其一锁定第一格敌人，挥拳造成50%攻击力的物理伤害
                        \s
                         §6§o爆发·投掷§r
                        锁定5*5范围内一个敌人，投掷捡来的石子，造成50%攻击力的物理伤害。消耗2点元素能量
                        \s
                         §6§o领袖·不死诅咒§r
                        我方所有角色永久提升1点最大生命值
                        \s
                         §b1命 强力挥拳§r
                        §6§o战技·挥拳§r倍率提升为100%
                        \s
                         §b2命 强力投掷§r
                        §6§o爆发·投掷§r倍率提升为100%
                        \s
                         §b3命 不详的面具§r
                        永久提升5点最大生命值
                        """;
    }
}
