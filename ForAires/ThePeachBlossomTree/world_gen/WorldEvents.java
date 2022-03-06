package com.example.examplemod.core.world_gen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.core.world_gen.generators.TreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class WorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent evt) {
        TreeGeneration.generateTrees(evt);
    }
}
