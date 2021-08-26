package net.blay09.mods.gravelminer.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.api.client.keymappings.BalmKeyMappings;
import net.blay09.mods.balm.api.client.keymappings.KeyConflictContext;
import net.blay09.mods.balm.api.client.keymappings.KeyModifier;
import net.blay09.mods.balm.api.event.TickPhase;
import net.blay09.mods.balm.api.event.TickType;
import net.blay09.mods.balm.api.event.client.ClientTickHandler;
import net.blay09.mods.gravelminer.GravelMinerConfig;
import net.blay09.mods.gravelminer.network.SetEnabledMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

public class ModKeyBindings {

    public static KeyMapping keyToggleGravelMiner;

    public static void initialize(BalmKeyMappings keyMappings) {
        keyToggleGravelMiner = keyMappings.registerKeyMapping("key.gravelminer.toggle", KeyConflictContext.INGAME, KeyModifier.NONE, InputConstants.UNKNOWN.getValue(), "key.categories.gravelminer");

        Balm.getEvents().onTickEvent(TickType.Client, TickPhase.End, ModKeyBindings::clientTicked);
    }

    public static void clientTicked(Minecraft client) {
        while (BalmClient.getKeyMappings().isActiveAndWasPressed(keyToggleGravelMiner)) {
            boolean newEnabled = !GravelMinerConfig.getActive().client.isEnabled;
            GravelMinerConfig.setEnabled(newEnabled);
            Balm.getNetworking().sendToServer(new SetEnabledMessage(newEnabled));

            if (client.player != null) {
                final TranslatableComponent message = new TranslatableComponent("gravelminer.toggle" + (newEnabled ? "On" : "Off"));
                client.player.displayClientMessage(message, true);
            }
        }
    }
}
