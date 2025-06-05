package com.wg.gichess.chess.rolechess.hilichurls;

import com.wg.gichess.RegionType;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.Element;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.AutoRegisterRole;
import com.wg.gichess.chess.rolechess.MovementType;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.WeaponType;
@AutoRegisterRole(
        key = "hilichurlberserker"
)
public class HilichurlBerserker extends Hilichurl {

    public HilichurlBerserker(Coord coord, int level , int constellation, int skin) {

        super(coord,level,constellation,skin);
        setBaseStats(10,2)
                .setBaseStats(3,0,3,0)
                .setTypes(ElementType.PYRO,WeaponType.POLEARM , MovementType.WALK)
                .setInformation("hilichurlberserker","冲锋丘丘人",1,1)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                         §c冲锋丘丘人§r
                        提瓦特荒野中游荡的原始住民。为了击退来犯的冒险者，会挥舞着熊熊燃烧的棒槌，一往无前地奋勇冲锋。
                        \s
                        武器：长柄武器
                        属性：火元素
                        攻击力：3
                        生命值：10
                        元素能量：2
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·轻挥§r
                        朝十字方向其一，对第一格敌人轻挥造成50%攻击力的火元素伤害
                        \s
                         §6§o爆发·冲锋§r
                        朝十字方向其一冲锋，直到有地形或角色阻挡，若为敌方角色，对其造成50%火元素伤害。消耗2点元素能量
                        \s
                         §6§o领袖·不死诅咒§r
                        我方所有角色永久提升1点最大生命值
                        \s
                         §b1命 强力轻挥§r
                        §6§o战技·轻挥§r倍率提升为100%
                        \s
                         §b2命 强力冲锋§r
                        §6§o爆发·冲锋§r倍率提升为100%
                        \s
                         §b3命 不详的面具§r
                        永久提升5点最大生命值
                        """;
    }
}
