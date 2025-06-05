package com.wg.item;

import com.wg.Wg;
import com.wg.item.other.GiChess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;



public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Wg.MODID);

    //原神棋
    public static final DeferredItem<Item> GI_CHESS = ITEMS.registerItem("gi_chess", GiChess::new);





}
