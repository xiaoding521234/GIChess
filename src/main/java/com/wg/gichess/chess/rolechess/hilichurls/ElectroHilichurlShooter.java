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
        key = "electrohilichurlshooter"
)
public class ElectroHilichurlShooter extends Hilichurl {
    public ElectroHilichurlShooter(Coord coord, int level , int constellation, int skin) {

        super(coord,level,constellation,skin);
        setBaseStats(20,2)
                .setBaseStats(4,0,3,0)
                .setTypes(ElementType.ELECTRO,WeaponType.BOW , MovementType.WALK)
                .setInformation("electrohilichurlshooter","雷箭丘丘人",1,1)
                .build();

    }

    @Override
    public String getIntroduce() {
        return """
                         §d雷箭丘丘人§r
                        提瓦特荒野中游荡的原始住民。手持简陋弩机的射手，箭头以电气水晶的碎片磨削而成，以便命中时造成电击。
                        \s
                        武器：弓
                        属性：雷元素
                        攻击力：4
                        生命值：10
                        元素能量：2
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·丘丘射击§r
                        朝十字方向其一，锁定首个敌人射出箭矢造成50%攻击力的雷元素伤害
                        \s
                         §6§o爆发·多重射击§r
                        朝十字方向其一，锁定首个敌人射出三支箭矢造成3次50%攻击力的雷元素伤害。消耗2点元素能量
                        \s
                         §6§o领袖·不死诅咒§r
                        我方所有角色永久提升1点最大生命值
                        \s
                         §b1命 强力射击§r
                        §6§o战技·丘丘射击§r倍率提升为100%
                        \s
                         §b2命 超多重射击§r
                        §6§o爆发·多重射击§r射出箭矢提升为4支，造成4次伤害
                        \s
                         §b3命 不详的面具§r
                        永久提升5点最大生命值
                        """;
    }
}
