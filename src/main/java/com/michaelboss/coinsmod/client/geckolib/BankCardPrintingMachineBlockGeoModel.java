package com.michaelboss.coinsmod.client.geckolib;

import com.michaelboss.coinsmod.CoinsMod;
import com.michaelboss.coinsmod.blockentity.BankCardPrintingMachineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BankCardPrintingMachineBlockGeoModel extends GeoModel<BankCardPrintingMachineBlockEntity> {

    @Override
    public ResourceLocation getModelResource(BankCardPrintingMachineBlockEntity animatable) {
        return (CoinsMod.id("geo/bank_card_printing_machine_block.geo.json"));
    }

    @Override
    public ResourceLocation getTextureResource(BankCardPrintingMachineBlockEntity animatable) {
        return (CoinsMod.id("textures/block/bank_card_printing_machine.png"));
    }

    @Override
    public ResourceLocation getAnimationResource(BankCardPrintingMachineBlockEntity animatable) {
        return (CoinsMod.id("animations/bank_card_printing_machine_block.animation.json"));
    }
}
