package com.michaelboss.coinsmod.client.geckolib;

import com.michaelboss.coinsmod.CoinsMod;
import com.michaelboss.coinsmod.blockentity.CoinageBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CoinageBlockGeoModel extends GeoModel<CoinageBlockEntity> {

    @Override
    public ResourceLocation getModelResource(CoinageBlockEntity animatable) {
        return (CoinsMod.id("geo/coinage_block.geo.json"));
    }

    @Override
    public ResourceLocation getTextureResource(CoinageBlockEntity animatable) {
        return (CoinsMod.id("textures/block/coinage.png"));
    }

    @Override
    public ResourceLocation getAnimationResource(CoinageBlockEntity animatable) {
        return (CoinsMod.id("animations/coinage_block.animation.json"));
    }
}