package de.dafuqs.spectrum.worldgen.features;

import com.mojang.serialization.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.*;

import java.util.*;

public class WeightedRandomFeaturePatch extends Feature<WeightedRandomFeaturePatchConfig> {

    public WeightedRandomFeaturePatch(Codec<WeightedRandomFeaturePatchConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<WeightedRandomFeaturePatchConfig> context) {
        Random random = context.getRandom();
        WeightedRandomFeaturePatchConfig weightedRandomFeaturePatchConfig = context.getConfig();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();

        int i = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int xzSpread = weightedRandomFeaturePatchConfig.xzSpread();
        int ySpread = weightedRandomFeaturePatchConfig.ySpread();

        for (int l = 0; l < weightedRandomFeaturePatchConfig.tries(); ++l) {
            mutable.set(blockPos, xzSpread - random.nextInt(xzSpread * 2 + 1), ySpread - random.nextInt(ySpread * 2 + 1), xzSpread - random.nextInt(xzSpread * 2 + 1));
            Optional<PlacedFeature> randomFeature = (weightedRandomFeaturePatchConfig.weightedRandomFeatureConfig().features().getDataOrEmpty(random));
            if (randomFeature.isPresent() && randomFeature.get().generateUnregistered(structureWorldAccess, context.getGenerator(), random, mutable)) {
                ++i;
            }
        }
        return i > 0;
    }

}
