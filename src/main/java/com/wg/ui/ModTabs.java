package com.wg.ui;

import com.wg.Wg;
import com.wg.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wg.MODID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> NEO_WG =
            CREATIVE_MODE_TABS.register("neo_wg",()-> CreativeModeTab.builder()
                    .title(Component.literal("原神棋"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(()-> ModItems.GI_CHESS.get().getDefaultInstance())
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.GI_CHESS.get());


                    }).build()
            );

}
