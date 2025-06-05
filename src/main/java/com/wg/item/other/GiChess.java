package com.wg.item.other;

import com.wg.gichess.User;
import com.wg.gichess.screen.LobbyScreen;
import com.wg.gichess.screen.OpenScreen;
import com.wg.gichess.screen.TestScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.awt.*;


public class GiChess extends Item{


    public GiChess(Properties properties) {
        super( properties );
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (level.isClientSide){
            Minecraft minecraft = Minecraft.getInstance();
            minecraft.options.fullscreen().set(true);
            minecraft.resizeDisplay();
            Minecraft.getInstance().setScreen(new OpenScreen(true, User.fastOpen));



        }

        return super.use(level, player, hand);
    }


}
