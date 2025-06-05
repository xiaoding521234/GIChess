package com.wg.gichess.chess.rolechess;

public enum RoleTag {

    // 游戏 (1xx)
    EARLY_GAME(101, "前期"),
    MID_GAME(102, "中期"),
    LATE_GAME(103, "后期"),

    //上手难度
    CUTE(201, "萌萌难度"),
    NEWBIE(202, "新手难度"),
    VETERAN(203, "熟手难度"),
    EXPERT(204, "专家难度"),
    MASTER(205, "大师难度"),


    // 战斗特性 (3xx)
    RESISTANCE(301, "抵抗"),
    HEALING(302, "治疗"),
    SHIELD(303, "护盾"),
    AGILE(304, "灵活"),
    CLUMSY(305, "笨重"),
    KNOCKBACK(306, "击退"),
    CONTROL(307, "控制"),
    STEALTH(308, "隐身"),
    STEALTH_BREAK(309, "破隐"),
    SUSTAINED_DPS(310, "持续输出"),
    BURST_DPS(311, "爆发输出"),
    SINGLE(312, "对单"),
    AOE(313, "对群"),
    LONG_RANGE(314, "长手"),
    SHORT_RANGE(315, "短手"),
    SPEED_BUFF(316, "加速"),
    SLOW_DEBUFF(317, "减速"),

    // 特殊机制 (4xx)
    QUICK_COMBO(401, "快速协奏"),

    // 祝福 (5xx)
    ANEMO_BLESSING(501, "风神庇佑"),
    GEO_BLESSING(502, "岩神庇佑"),
    ELECTRO_BLESSING(503, "雷神庇佑"),
    DENDRO_BLESSING(504, "草神庇佑"),
    HYDRO_BLESSING(505, "水神庇佑"),
    PYRO_BLESSING(506, "火神庇佑"),
    CRYO_BLESSING(507, "冰神庇佑"),

    // 其它 (6xx)
    IMMORTAL_CURSE(601, "不死诅咒");

    public final int id;
    public final String displayName;

    RoleTag(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getCategory() {
        return id / 100;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
