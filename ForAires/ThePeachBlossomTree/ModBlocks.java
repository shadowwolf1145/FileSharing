package com.example.examplemod.common;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.common.Trees.PeachBlossom.PeachBlossomTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);
    public static final Map<String, RegistryObject<Block>> Blocks = new HashMap<String,RegistryObject<Block>>();
    public static final Map<String,Boolean> BlockItems = new HashMap<String,Boolean>();
    public static Block get(String block_name) {
        return Blocks.get(block_name).get();
    }
    public static void create_block(String block_name, AbstractBlock.Properties block_properties, Item.Properties item_properties) {
        Blocks.put(block_name,
                BLOCKS.register(block_name,
                        () -> new Block(block_properties))
        );
        ModItems.create_blockitem(block_name,Blocks.get(block_name),item_properties);
        BlockItems.put(block_name,true);
    }
    public static void create_leaves(String block_name, AbstractBlock.Properties block_properties, Item.Properties item_properties) {
        Blocks.put(block_name,
                BLOCKS.register(block_name,
                        () -> new LeavesBlock(block_properties))
        );
        ModItems.create_blockitem(block_name,Blocks.get(block_name),item_properties);
        BlockItems.put(block_name,true);
    }
    public static <T extends Tree> void create_sapling(String sapling_name, Class<T> tree, AbstractBlock.Properties block_properties, Item.Properties item_properties) {
        Blocks.put(sapling_name,
                BLOCKS.register(sapling_name,
                        () -> {
                            SaplingBlock block = null;
                            try {
                                block = new SaplingBlock(tree.getDeclaredConstructor().newInstance(),block_properties);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                            return block;
                        })
        );
        ModItems.create_blockitem(sapling_name,Blocks.get(sapling_name),item_properties);
        BlockItems.put(sapling_name,true);
    }
    public static void create_all_blocks() {
        create_block("peachblossom_log",AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).sound(SoundType.ANCIENT_DEBRIS),
                new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
        create_block("peachblossom_leaves",AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).sound(SoundType.ANCIENT_DEBRIS),
                new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
        create_sapling("peachblossom_sapling",PeachBlossomTree.class,AbstractBlock.Properties.of(Material.GRASS).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)
        ,new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    }
    public static void register(IEventBus eventBus) {
        System.out.println("Registering Blocks");
        create_all_blocks();
        BLOCKS.register(eventBus);
    }

}
