package com.michaelboss.newcoins.block;

import com.michaelboss.newcoins.blockentity.PrinterPaperMoneyEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrinterPaperMoneyBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<DoublePart> PART = EnumProperty.create("part", DoublePart.class);
    public static final MapCodec<PrinterPaperMoneyBlock> CODEC = simpleCodec(PrinterPaperMoneyBlock::new);

    private static final VoxelShape LEFT_SHAPE = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape RIGHT_SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public PrinterPaperMoneyBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, DoublePart.LEFT));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new PrinterPaperMoneyEntity(blockPos, blockState);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(PART) == DoublePart.LEFT ? LEFT_SHAPE : RIGHT_SHAPE;
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getShape(state, level, pos, context);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        BlockPos pos = context.getClickedPos();
        BlockPos otherPos = pos.relative(facing.getClockWise());

        if (!context.getLevel().getBlockState(otherPos).canBeReplaced(context))
            return null;

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(PART, DoublePart.LEFT);
    }

    @Override
    public void setPlacedBy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);
            BlockPos otherPos = pos.relative(facing.getClockWise());

            level.setBlock(otherPos, state.setValue(PART, DoublePart.RIGHT), 3);
        }
    }

    @Override
    protected void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            Direction facing = state.getValue(FACING);
            BlockPos otherPos = pos.relative(facing.getClockWise());

            if (level.getBlockState(otherPos).is(this)) {
                level.destroyBlock(otherPos, false);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
