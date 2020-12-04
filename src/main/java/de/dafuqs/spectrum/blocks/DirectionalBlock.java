package de.dafuqs.spectrum.blocks;

import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.Direction;

public class DirectionalBlock extends PillarBlock {

    public DirectionalBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(AXIS, Direction.Axis.Y));
    }


}
