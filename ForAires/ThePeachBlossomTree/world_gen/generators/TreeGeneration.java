package com.example.examplemod.core.world_gen.generators;

import com.example.examplemod.common.Trees.PeachBlossom.PeachBlossomFeatures;
import com.example.examplemod.common.Trees.PeachBlossom.PeachBlossomTree;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class TreeGeneration {

    public static void generateTrees(final BiomeLoadingEvent evt) {
        PeachBlossomTree peachBlossomTree = new PeachBlossomTree();
        Random random = new Random();

        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY,evt.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (types.contains(BiomeDictionary.Type.PLAINS)) {
            List<Supplier<ConfiguredFeature<?,?>>> base =
                    evt.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
            base.add(()-> PeachBlossomFeatures.PEACH_BLOSSOM_TREE
                    .decorated(Features.Placements.HEIGHTMAP)
                    .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.25f,2))));
        }
    }
}
