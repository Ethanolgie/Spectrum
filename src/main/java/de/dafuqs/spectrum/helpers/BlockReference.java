package de.dafuqs.spectrum.helpers;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

/**
 * Why yes. I did in fact yoink this from PulseFlux.
 */
public final class BlockReference {

    private BlockState state;
    private final Optional<BlockEntity> be;
    public final BlockPos pos;

    private BlockReference(BlockState state, Optional<BlockEntity> be, BlockPos pos) {
        this.state = state;
        this.be = be;
        this.pos = pos;
    }

    public static BlockReference of(BlockState state, BlockPos pos) {
        return new BlockReference(state, Optional.empty(), pos);
    }

    public static BlockReference of(World world, BlockPos pos) {
        return new BlockReference(world.getBlockState(pos), Optional.ofNullable(world.getBlockEntity(pos)), pos);
    }

    public BlockReference appendBE(BlockEntity entity) {
        return new BlockReference(state, Optional.of(entity), pos);
    }

    public BlockReference tryRecreateWithBE(World world) {
        return new BlockReference(state, Optional.ofNullable(world.getBlockEntity(pos)), pos);
    }

    public <V extends Comparable<V>> void setProperty(Property<V> property, V value) {
        state = state.with(property, value);
    }

    public <V extends Comparable<V>> V getProperty(Property<V> property) {
        return state.get(property);
    }

    public BlockState getState() {
        return state;
    }

    public boolean exists() {
        return state != null && pos != null;
    }
    
    public boolean isOf(Block block) {
        return state.isOf(block);
    }
    
    public boolean isOf(BlockState blockState) {
        return state == blockState;
    }

    public boolean validateBE() {
        return be.isPresent();
    }

    public Optional<BlockEntity> tryGetBlockEntity() {
        return be;
    }

    public void update(World world, int flags) {
        world.setBlockState(pos, state, flags);
    }

    public void update(World world) {
        update(world, Block.NOTIFY_ALL);
    }
}