package com.wg.gichess.chess.rolechess.roleregion;

import com.wg.gichess.RegionType;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.rolechess.RoleChess;

public class Mondstadt extends RoleChess {

    public int maxResonanceEnergy = 3;
    public int currentResonanceEnergy =0;

    public Mondstadt(Coord coord, int level, int constellation, int skin) {
        super(coord, level, constellation ,skin);
        setRegionType(RegionType.MONDSTADT);
    }

    public Mondstadt setMaxResonanceEnergy(int maxResonanceEnergy){
        this.maxResonanceEnergy = maxResonanceEnergy;
        return this;
    }

    @Override
    public String introduceSkill() {
        return String.format(
                """
                        武器：%s
                        属性：%s
                        \s
                        攻击力：%s(%s+%s)
                        生命值：%s/%s
                        护盾：%s
                        防御：%s(%s+%s)
                        移速：%s(%s+%s)
                        \s
                        元素能量：%s/%s
                        协奏能量：%s/%s
                        \s
                        元素精通：%s
                        韧性：%s
                        额外伤害：%s
                        治疗加成：%s
                        攻击倍率加成：%s
                        治疗倍率加成：%s
                        施法范围加成：%s
                        生效范围加成：%s
                        """
                ,weaponType.displayName
                ,elementType.displayName

                ,baseAttack+attackBonus,baseAttack,attackBonus
                ,currentHealth,maxHealth
                ,shield
                ,baseDefense+defenseBonus,baseDefense,defenseBonus
                ,baseSpeed+speedBonus,baseSpeed,speedBonus

                ,currentEnergy,maxEnergy
                ,currentResonanceEnergy,maxResonanceEnergy

                ,elementalMastery
                ,tenacity
                ,additionalDamage
                ,treatmentBonus
                ,attackMultiplierBonus
                ,treatmentMultiplierBonus
                ,castRangeBonus
                ,effectAreaBonus
        );

    }


}
