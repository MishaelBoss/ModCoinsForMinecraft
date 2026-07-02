package com.michaelboss.coinsmod.util;

import com.michaelboss.coinsmod.item.CoinItem;
import net.minecraft.world.item.ItemStack;

public class CoinValues {
    public static int getCoinValue(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof CoinItem coinItem) {
            return coinItem.getInternalValue();
        }
        return 0;
    }

    public static int getTotalStackValue(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        return getCoinValue(stack) * stack.getCount();
    }
}
