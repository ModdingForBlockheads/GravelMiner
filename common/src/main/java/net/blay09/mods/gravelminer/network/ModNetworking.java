package net.blay09.mods.gravelminer.network;

import net.blay09.mods.balm.api.network.BalmNetworking;
import net.blay09.mods.gravelminer.GravelMiner;

public class ModNetworking {

    public static void initialize(BalmNetworking networking) {
        networking.allowServerOnly(GravelMiner.MOD_ID);

        networking.registerClientboundPacket(HelloMessage.TYPE, HelloMessage.class, HelloMessage::encode, HelloMessage::decode, HelloMessage::handle);
        networking.registerServerboundPacket(SetClientSettingMessage.TYPE, SetClientSettingMessage.class, SetClientSettingMessage::encode, SetClientSettingMessage::decode, SetClientSettingMessage::handle);
    }

}
