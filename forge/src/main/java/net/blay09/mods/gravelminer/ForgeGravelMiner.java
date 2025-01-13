package net.blay09.mods.gravelminer;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.forge.ForgeLoadContext;
import net.blay09.mods.gravelminer.client.GravelMinerClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(GravelMiner.MOD_ID)
public class ForgeGravelMiner {
    public ForgeGravelMiner(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModEventBus());
        Balm.initialize(GravelMiner.MOD_ID, loadContext, GravelMiner::initialize);
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initialize(GravelMiner.MOD_ID, loadContext, GravelMinerClient::initialize);
        }
    }

}
