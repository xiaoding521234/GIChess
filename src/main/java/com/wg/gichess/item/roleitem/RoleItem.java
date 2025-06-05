package com.wg.gichess.item.roleitem;

import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.item.Item;
import com.wg.gichess.material.Material;

public class RoleItem extends Item {

    public RoleChess roleChess;


    public RoleItem(RoleChess roleChess) {
        super(roleChess.id, 1, roleChess.star, null);

    }
}
