package com.wg;

import com.wg.item.ModItems;
import com.wg.ui.ModTabs;


import com.mojang.logging.LogUtils;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;


import org.slf4j.Logger;

@Mod(Wg.MODID)
public class Wg
{


    public static final String MODID = "wg";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Wg(IEventBus modEventBus, ModContainer modContainer)
    {





        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);



    }


}
