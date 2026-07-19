package com.michaelboss.newcoins.blockentity;

import com.michaelboss.newcoins.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PrinterPaperMoneyEntity extends BlockEntity {
    public PrinterPaperMoneyEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PRINTER_PAPER_MONEY_BLOCK_ENTITY.get(), pos, blockState);
    }
}
