package crystekteam.crystek.init;

import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.items.misc.ItemCrafting;
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
		registerCraftingRecipes();
		registerSmeltingRecipes();
		registerSmasherRecipes();
		registerCrystallizerRecipes();
		CrystekApi.registerFuel(new FluidStack(FluidRegistry.LAVA, 1000), 1000, 50);
	}

	static void registerCraftingRecipes()
	{

	}

    static void registerCrystallizerRecipes()
    {
        CrystekApi.registerCrystallizerRecipes(new ItemStack(Items.IRON_INGOT), new FluidStack(FluidRegistry.LAVA, 1000), new ItemStack(Blocks.SAND));
    }
	static void registerSmeltingRecipes()
	{
		//Dusts
		GameRegistry.addSmelting(ItemBank.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		GameRegistry.addSmelting(ItemBank.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
	}

	static void registerSmasherRecipes()
	{
		//General
		CrystekApi.registerSmasherRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.COBBLESTONE));
		CrystekApi.registerSmasherRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GRAVEL));
		//Ores
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustIron", 2), "oreIron");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustGold", 2), "oreGold");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustDiamond", 2), "oreDiamond");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustEmerald", 2), "oreEmerald");
		CrystekApi.registerSmasherRecipe(new ItemStack(Items.REDSTONE, 6), "oreRedstone");
		CrystekApi.registerSmasherRecipe(new ItemStack(Items.DYE, 6, 11), "oreLapis");
		CrystekApi.registerSmasherRecipe(new ItemStack(Items.QUARTZ, 2), "oreQuartz");
		//Blocks
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustIron", 9), "blockIron");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustGold", 9), "blockGold");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustDiamond", 9), "blockDiamond");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustEmerald", 9), "blockEmerald");
		CrystekApi.registerSmasherRecipe(new ItemStack(Items.REDSTONE, 9), "blockRedstone");
		CrystekApi.registerSmasherRecipe(new ItemStack(Items.DYE, 9, 11), "blockLapis");
		CrystekApi.registerSmasherRecipe(new ItemStack(Items.QUARTZ, 4), "blockQuartz");
		//Ingots/Gems
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustIron", 1), "ingotIron");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustGold", 1), "ingotGold");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustDiamond", 1), "gemDiamond");
		CrystekApi.registerSmasherRecipe(ItemCrafting.getStack("dustEmerald", 1), "gemEmerald");
	}
}
