package com.wg.gichess.material.tutorialscreen.fundamentals;

import com.wg.gichess.material.Material;
import com.wg.gichess.material.tutorialscreen.TutorialMEntry;

public class Preface extends TutorialMEntry {
    public Preface() {
        super("前言",new Material("textures/tutorialscreen/fundamentals/preface.png"));


    }

    @Override
    public String getIntroduce() {
        return """
                 原神棋当前版本：0.1.20250421
                严禁将本MOD用于任何商业用途或倒卖行为！本MOD完全免费，切勿上当受骗！
                此为二创作品，一切版权归官方所有
                MOD内容可能存在与官方设定的差异，请以游戏实际内容为准。
                提建议/报BUG等请加QQ群：1045300642
                新手福利兑换码:
                原神棋 200纠缠之缘，自选全三星角色*3，自选全四星角色*2，自选全五星角色*1
                感谢您的支持！请合理游戏，享受乐趣~
                """;
    }
}
