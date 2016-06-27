package crystekteam.crystek.init;

import crystekteam.crystek.api.AdvancedEngineeringApi;
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
	}

	static void registerCraftingRecipes()
	{

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
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.COBBLESTONE));
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GRAVEL));
		//Ores
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustIron", 2), "oreIron");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustGold", 2), "oreGold");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustDiamond", 2), "oreDiamond");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustEmerald", 2), "oreEmerald");
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Items.REDSTONE, 6), "oreRedstone");
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Items.DYE, 6, 11), "oreLapis");
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Items.QUARTZ, 2), "oreQuartz");
		//Blocks
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustIron", 9), "blockIron");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustGold", 9), "blockGold");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustDiamond", 9), "blockDiamond");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustEmerald", 9), "blockEmerald");
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Items.REDSTONE, 9), "blockRedstone");
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Items.DYE, 9, 11), "blockLapis");
		AdvancedEngineeringApi.registerSmasherRecipe(new ItemStack(Items.QUARTZ, 4), "blockQuartz");
		//Ingots/Gems
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustIron", 1), "ingotIron");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustGold", 1), "ingotGold");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustDiamond", 1), "gemDiamond");
		AdvancedEngineeringApi.registerSmasherRecipe(ItemCrafting.getStack("dustEmerald", 1), "gemEmerald");
	}

	static void registerCrystallizerRecipes()
	{
		AdvancedEngineeringApi
				.registerCrystallizerRecipes(new ItemStack(Items.IRON_INGOT), new FluidStack(FluidRegistry.LAVA, 1000),
						new ItemStack(Blocks.SAND));
	}
}
