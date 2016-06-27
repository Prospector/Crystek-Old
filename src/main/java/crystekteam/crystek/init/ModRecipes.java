package crystekteam.crystek.init;

import crystekteam.crystek.api.AdvancedEngineeringApi;
import crystekteam.crystek.util.ItemBank;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes
{
    public static void init()
    {
        registerSmeltingRecipes();
        registerSmasherRecipes();
        registerCrystallizerRecipes();
    }

    static void registerSmasherRecipes()
    {
        AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.COBBLESTONE));
        AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GRAVEL));
        //AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(ModItems.crafting, 2, 2), "oreCopper");
    }

    static void registerSmeltingRecipes()
    {
        //Dusts
        GameRegistry.addSmelting(ItemBank.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
        GameRegistry.addSmelting(ItemBank.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
    }

    static void registerCrystallizerRecipes()
    {
        AdvancedEngineeringApi.registerCrystallizerRecipes(new ItemStack(Items.IRON_INGOT), new FluidStack(FluidRegistry.LAVA, 1000), new ItemStack(Blocks.SAND));
    }
}
