package com.wg.gichess;

import com.wg.gichess.sub.PutGoWarRoleS;
import com.wg.gichess.sub.TurnOverS;

public class Sub {


    /**
     * 出战角色放置订阅
     */
    public PutGoWarRoleS putGoWarRoleS() {
        return new PutGoWarRoleS();
    }

    /**
     * 回合结束订阅
     */
    public TurnOverS turnOverS() {
        return new TurnOverS();
    }

}
