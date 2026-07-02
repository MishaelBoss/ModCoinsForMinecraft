package com.michaelboss.coinsmod.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoinItem extends Item {
    private final int internalCoinValue;

    public CoinItem(Properties properties, float displayValue) {
        super(properties);
        this.internalCoinValue = Math.round(displayValue * 10);
    }

    public int getInternalValue() {
        return this.internalCoinValue;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {

        if (Screen.hasShiftDown()) {
            float singleValue = this.internalCoinValue / 10.0F;
            tooltipComponents.add(Component.translatable("tooltip.coinsmod.coin.details", singleValue));

            if (stack.getCount() > 1) {
                int totalInternalValue = this.internalCoinValue * stack.getCount();
                float totalValue = totalInternalValue / 10.0F;

                tooltipComponents.add(Component.translatable("tooltip.coinsmod.coin.total_details", totalValue));
            }
        } else {
            tooltipComponents.add(Component.translatable("tooltip.coinsmod.hold_shift"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
