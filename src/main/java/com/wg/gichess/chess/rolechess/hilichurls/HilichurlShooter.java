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
        key = "hilichurlshooter"
)
public class HilichurlShooter extends Hilichurl {

    public HilichurlShooter(Coord coord, int level , int constellation, int skin) {

        super(coord,level,constellation,skin);
        setBaseStats(10,2)
                .setBaseStats(4,0,3,0)
                .setTypes(ElementType.PHYSICAL,WeaponType.BOW , MovementType.WALK)
                .setInformation("hilichurlshooter","射手丘丘人",1,1)
                .build();


    }

    @Override
    public String getIntroduce() {
        return """
                         射手丘丘人
                        提瓦特荒野中游荡的原始住民。手持简陋弩机的丘丘人射手。丘丘人实际上并不具备制造弩的工艺水平。因此，一般认为，在丘丘人背后，存在着操控丘丘人，并为它们提供设备物资的组织。
                        \s
                        武器：弓
                        属性：物理
                        攻击力：4
                        生命值：10
                        元素能量：2
                        \s
                         §6§o移动·步行§r
                        朝十字方向步行，至多移动3格
                        \s
                         §6§o战技·丘丘射击§r
                        朝十字方向其一，锁定首个敌人射出箭矢造成50%攻击力的物理伤害
                        \s
                         §6§o爆发·多重射击§r
                        朝十字方向其一，锁定首个敌人射出三支箭矢造成3次50%攻击力的物理伤害。消耗2点元素能量
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
