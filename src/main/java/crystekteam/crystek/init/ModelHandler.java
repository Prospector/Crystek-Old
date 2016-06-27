package crystekteam.crystek.init;

import crystekteam.crystek.items.misc.ItemCrafting;
import crystekteam.crystek.items.tools.ItemCircuit;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Gigabit101 on 09/06/2016.
 */
public class ModelHandler
{
    public static void init()
    {
        registerItemModel(ModBlocks.coalGen, 0);
        registerItemModel(ModBlocks.machineframe, 0);
        registerItemModel(ModBlocks.poweredFurnace, 0);
        registerItemModel(ModItems.welder, 0);
        registerItemModel(ModItems.powerScanner, 0);
        registerItemModel(ModItems.laserLinkingDevice, 0);
        registerItemModel(ModBlocks.grinder, 0);
        registerItemModel(ModBlocks.crystallizer, 0);
        registerItemModel(ModBlocks.solarGen, 0);
        registerItemModel(ModBlocks.teslaCell, 0);
        registerItemModel(ModItems.battery, 0);
        registerItemModel(ModItems.drill, 0);
        registerItemModel(ModItems.ironGrindingBlade, 0);

        int i;
        //Register all crafting items textures
        for (i = 0; i < ItemCrafting.types.length; ++i)
        {
            String[] name = ItemCrafting.types.clone();
            registerItemModel(ModItems.crafting, i, name[i]);
        }
        for (i = 0; i < ItemCircuit.types.length; ++i)
        {
            String[] name = ItemCircuit.types.clone();
            registerItemModel(ModItems.circuit, i, name[i]);
        }
    }

    static void registerItemModel(Block b, int meta)
    {
        registerItemModel(Item.getItemFromBlock(b), meta);
    }

    static void registerItemModel(Block b, int meta, String variant)
    {
        registerItemModel(Item.getItemFromBlock(b), meta, variant);
    }

    static void registerItemModel(Item i, int meta)
    {
        ResourceLocation loc = i.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "inventory"));
    }

    static void registerItemModel(Item i, int meta, String variant)
    {
        ResourceLocation loc = i.getRegistryName();
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
    }
}
