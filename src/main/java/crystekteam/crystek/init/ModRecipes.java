package crystekteam.crystek.init;

import crystekteam.crystek.api.AdvancedEngineeringApi;
import crystekteam.crystek.api.CraftingBlocks;
import crystekteam.crystek.util.ItemBank;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes
{
    public static void init()
    {
        registerCrafterBlocks();
        registerRecipes();
        registerSmeltingRecipes();
        registerSmasherRecipes();
    }

    static void registerSmasherRecipes()
    {
        AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.COBBLESTONE));
        AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GRAVEL));
        //AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(ModItems.crafting, 2, 2), "oreCopper");
    }

    static void registerCrafterBlocks()
    {
        CraftingBlocks.addItemStackToList(new ItemStack(ModBlocks.coalGen, 1));
        CraftingBlocks.addItemStackToList(new ItemStack(ModBlocks.poweredFurnace, 1));
        CraftingBlocks.addItemStackToList(new ItemStack(ModBlocks.grinder, 1));
    }

    static void registerRecipes()
    {
        AdvancedEngineeringApi.registerCrafterRecipe(new ItemStack(ModBlocks.coalGen), new ItemStack(Blocks.IRON_BLOCK), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.crafting, 1, 2));
    }

    static void registerSmeltingRecipes()
    {
        //Dusts
        GameRegistry.addSmelting(ItemBank.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
        GameRegistry.addSmelting(ItemBank.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
    }
}
