package com.example.examplemod.common.Trees.PeachBlossom;

import com.example.examplemod.common.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class PeachBlossomFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig,?> PEACH_BLOSSOM_TREE = register("peach_blossom_tree"
    , Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(States.PEACH_BLOSSOM_LOG)
                    ,new SimpleBlockStateProvider(States.PEACH_BLOSSOM_LEAVES)
                    ,new BlobFoliagePlacer(FeatureSpread.fixed(2),FeatureSpread.fixed(0),3)
                    ,new StraightTrunkPlacer(4,2,0)
                    ,new TwoLayerFeature(1,0,1)).ignoreVines().build()));

    public static final class States {
        protected static final BlockState PEACH_BLOSSOM_LOG = ModBlocks.Blocks.get("peachblossom_log").get().defaultBlockState();
        protected static final BlockState PEACH_BLOSSOM_LEAVES = ModBlocks.Blocks.get("peachblossom_leaves").get().defaultBlockState();
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String tree_name, ConfiguredFeature<FC, ?> tree_properties) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, tree_name, tree_properties);
    }
}
