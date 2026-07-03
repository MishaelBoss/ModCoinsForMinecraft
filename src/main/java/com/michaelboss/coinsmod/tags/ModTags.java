package com.michaelboss.coinsmod.tags;

import com.michaelboss.coinsmod.CoinsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> ATM_CURRENCY =
                ItemTags.create(ResourceLocation.fromNamespaceAndPath(CoinsMod.MOD_ID, "atm_currency"));

        public static final TagKey<Item> ATM_CARDS =
                ItemTags.create(ResourceLocation.fromNamespaceAndPath(CoinsMod.MOD_ID, "atm_cards"));
    }
}
