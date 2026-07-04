package com.michaelboss.coinsmod;

import com.michaelboss.coinsmod.registry.*;
import com.michaelboss.coinsmod.client.ClientModEvents;
import com.michaelboss.coinsmod.item.tab.ModCreativeModeTabs;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(CoinsMod.MOD_ID)
public class CoinsMod {
    public static final String MOD_ID = "coinsmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CoinsMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModMenus.register(modEventBus);
        ModDataComponents.register(modEventBus);
        modEventBus.register(ClientModEvents.class);
        ModBlockEntities.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        modEventBus.addListener(this::registerPackets);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static String getVersion() {
        ModContainer container = ModList.get().getModContainerById(MOD_ID).orElse(null);
        if (container == null) return "unknown";
        return container.getModInfo().getVersion().toString();
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        LOGGER.info("COPPER_COIN_INTERNAL_COIN_VALUE >> {}", Config.COPPER_COIN_INTERNAL_COIN_VALUE.get());
        LOGGER.info("IRON_COIN_INTERNAL_COIN_VALUE >> {}", Config.IRON_COIN_INTERNAL_COIN_VALUE.get());
        LOGGER.info("GOLD_COIN_INTERNAL_COIN_VALUE >> {}", Config.GOLD_COIN_INTERNAL_COIN_VALUE.get());
    }

    private void registerPackets(final net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent event) {
        final net.neoforged.neoforge.network.registration.PayloadRegistrar registrar = event.registrar("1.0.0");

        registrar.playToServer(
                com.michaelboss.coinsmod.network.DepositC2SPacket.TYPE,
                com.michaelboss.coinsmod.network.DepositC2SPacket.STREAM_CODEC,
                com.michaelboss.coinsmod.network.DepositC2SPacket::handle
        );

        registrar.playToServer(
                com.michaelboss.coinsmod.network.WithdrawC2SPacket.TYPE,
                com.michaelboss.coinsmod.network.WithdrawC2SPacket.STREAM_CODEC,
                com.michaelboss.coinsmod.network.WithdrawC2SPacket::handle
        );
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
