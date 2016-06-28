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
import net.minecraftforge.oredict.OreDictionary;

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
		CrystekApi.registerCrystallizerRecipes("dustIron", new FluidStack(ModFluids.fluidTesla, 1000), ItemBank.teslaCrystal);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystRed, new FluidStack(ModFluids.fluidTesla, 1000), ItemBank.teslaCrystalRed);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystYellow, new FluidStack(ModFluids.fluidTesla, 1000), ItemBank.teslaCrystalYellow);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystPurple, new FluidStack(ModFluids.fluidTesla, 1000), ItemBank.teslaCrystalPurple);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystBlue, new FluidStack(ModFluids.fluidTesla, 1000), ItemBank.teslaCrystalBlue);
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
		CrystekApi.registerGrinderRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.COBBLESTONE));
		CrystekApi.registerGrinderRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GRAVEL));
		//Ores
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustIron", 2), "oreIron");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustGold", 2), "oreGold");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustDiamond", 2), "oreDiamond");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustEmerald", 2), "oreEmerald");
		CrystekApi.registerGrinderRecipe(new ItemStack(Items.REDSTONE, 6), "oreRedstone");
		CrystekApi.registerGrinderRecipe(new ItemStack(Items.DYE, 6, 11), "oreLapis");
		CrystekApi.registerGrinderRecipe(new ItemStack(Items.QUARTZ, 2), "oreQuartz");
		//Blocks
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustIron", 9), "blockIron");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustGold", 9), "blockGold");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustDiamond", 9), "blockDiamond");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustEmerald", 9), "blockEmerald");
		CrystekApi.registerGrinderRecipe(new ItemStack(Items.REDSTONE, 9), "blockRedstone");
		CrystekApi.registerGrinderRecipe(new ItemStack(Items.DYE, 9, 11), "blockLapis");
		CrystekApi.registerGrinderRecipe(new ItemStack(Items.QUARTZ, 4), "blockQuartz");
		//Ingots/Gems
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustIron", 1), "ingotIron");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustGold", 1), "ingotGold");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustDiamond", 1), "gemDiamond");
		CrystekApi.registerGrinderRecipe(ItemCrafting.getStack("dustEmerald", 1), "gemEmerald");
		//Compat
		addOreGrinderRecipe("Iron");
		addOreGrinderRecipe("Gold");
		addOreGrinderRecipe("Coal");
		addOreGrinderRecipe("Redstone");
		addOreGrinderRecipe("Diamond");
		addOreGrinderRecipe("Emerald");
		addOreGrinderRecipe("Copper");
		addOreGrinderRecipe("Tin");
		addOreGrinderRecipe("Nickel");
		addOreGrinderRecipe("Zinc");
		addOreGrinderRecipe("Bronze");
		addOreGrinderRecipe("Brass");
		addOreGrinderRecipe("Silver");
		addOreGrinderRecipe("Lead");
		addOreGrinderRecipe("Steel");
		addOreGrinderRecipe("Electrum");
		addOreGrinderRecipe("AluminumBrass");
		addOreGrinderRecipe("AluminiumBrass");
		addOreGrinderRecipe("Invar");
		addOreGrinderRecipe("Aluminum");
		addOreGrinderRecipe("Aluminium");
		addOreGrinderRecipe("Platinum");
	}

	public static void addOreGrinderRecipe(String name){
		addGrinderOreDictRecipe("dust"+name, "ingot"+name);
		addGrinderOreDictRecipe("dust"+name, "gem"+name);
		addGrinderOreDictRecipe("dust"+name, 2, "ore"+name);
		addGrinderOreDictRecipe("dust"+name, 9, "block"+name);
	}

	public static void addGrinderOreDictRecipe(String oreDictOutput, int outputAmount, String oreDictInput){
		if(OreDictionary.doesOreNameExist(oreDictOutput)&&OreDictionary.doesOreNameExist(oreDictInput)){
			ItemStack output = OreDictionary.getOres(oreDictOutput).get(0);
			output.stackSize=outputAmount;
			CrystekApi.registerGrinderRecipe(output, oreDictInput);
		}
	}

	public static void addGrinderOreDictRecipe(String oreDictOutput, String oreDictInput){
		addGrinderOreDictRecipe(oreDictOutput, 1, oreDictInput);
	}
}
