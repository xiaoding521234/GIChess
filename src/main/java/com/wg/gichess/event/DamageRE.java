package com.wg.gichess.event;

import com.wg.gichess.Event;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.Element;
import com.wg.gichess.chess.ElementType;
import com.wg.gichess.chess.rolechess.BottomSkill;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.WeaponType;

import java.util.ArrayList;
import java.util.List;

public class DamageRE extends Event {

    RoleChess source;
    List<Coord> targets = new ArrayList<>();
    BottomSkill bottomSkill;

    Double multiplier = null;
    Integer base = null;
    int additionalDamage = 0;

    Element element;
    WeaponType weaponType;
    DamageType damageType;

    List<DamageTag> damageTags = new ArrayList<>();

    public DamageRE(RoleChess source, List<Coord> targets, BottomSkill bottomSkill) {
        this.source = source;
        this.targets = targets;
        this.bottomSkill = bottomSkill;
    }

    public DamageRE setBase(Double multiplier, Integer base, int additionalDamage) {
        this.multiplier = multiplier;
        this.base = base;
        this.additionalDamage = additionalDamage;
        return this;
    }

    public DamageRE setType(Element element, WeaponType weaponType) {
        this.element = element;
        this.weaponType = weaponType;

        return this;
    }

    public DamageRE setTag(List<DamageTag> damageTags) {
        this.damageTags = damageTags;
        return this;
    }

}
