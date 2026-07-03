package com.michaelboss.coinsmod.tag;

import com.michaelboss.coinsmod.CoinsMod;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> ATM_CURRENCY =
                ItemTags.create(CoinsMod.id("atm_currency"));

        public static final TagKey<Item> ATM_CARDS =
                ItemTags.create(CoinsMod.id("atm_cards"));
    }
}
