package crystekteam.crystek.init;

import crystekteam.crystek.api.CrystekApi;
import crystekteam.crystek.util.CraftingHelper;
import crystekteam.crystek.util.ItemBank;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes
{
	public static void init()
	{
		registerCraftingRecipes();
		registerSmeltingRecipes();
		registerGrinderRecipes();
		registerCrystallizerRecipes();
		CrystekApi.registerFuel(new FluidStack(FluidRegistry.LAVA, 1000), 1000, 50);
	}

	static void registerCraftingRecipes()
	{
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.drill), " ii", "oci", "rt ", 'i', "ingotIron", 'o',
						"ingotObsidian", 'c', "crystalTesla", 'r', "dustRedstone", 't', "ingotTeslaAlloy"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.powerScanner), "oto", "tct", "i i", 'i', "ingotIron", 'o',
						"ingotObsidian", 'c', "crystalTesla", 't', "ingotTeslaAlloy"));
		GameRegistry.addShapelessRecipe(ItemBank.obsidianIngot, new ItemStack(Items.WATER_BUCKET),
				new ItemStack(Items.LAVA_BUCKET));
		CraftingHelper.addShapelessOreRecipe(ItemBank.catalystRed, "dustIron", new ItemStack(Items.REDSTONE));
		CraftingHelper.addShapelessOreRecipe(ItemBank.catalystYellow, "dustIron", new ItemStack(Items.GLOWSTONE_DUST));
		CraftingHelper.addShapelessOreRecipe(ItemBank.catalystPurple, "dustIron", new ItemStack(Items.CHORUS_FRUIT));
		CraftingHelper.addShapelessOreRecipe(ItemBank.catalystBlue, "dustIron", new ItemStack(Items.DYE, 1, 11));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(ItemBank.efficiencyUpgrade, "rtr", "tct", "rtr", 'r', "blockRedstone", 'c',
						ItemBank.teslaCrystalYellow, 't', "ingotTeslaAlloy"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.battery), " i ", "tct", "oco", 'i', "ingotIron", 'c',
						"crystalTesla", 't', "ingotTeslaAlloy", 'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.ironGrindingBlade), " m ", "mom", " m ", 'm', "ingotIron",
						'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.goldGrindingBlade), " m ", "mom", " m ", 'm', "ingotGold",
						'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.diamondGrindingBlade), " m ", "mom", " m ", 'm',
						"gemDiamond", 'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.obsidianGrindingBlade), " m ", "mom", " m ", 'm', "obsidian",
						'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.teslaAlloyGrindingBlade), " m ", "mom", " m ", 'm',
						"ingotTeslaAlloy", 'o', "ingotObsidian"));
		//TODO Add manual power generation and make this require tesla crystal
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.coalGen), "ooo", "oco", "obo", 'c', "blockGlass", 'o',
						"ingotObsidian", 'b', new ItemStack(Blocks.IRON_BARS)));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.fluidGen), "ooo", "oco", "obo", 'c', "crystalTesla", 'o',
						"ingotObsidian", 'b', new ItemStack(Items.BUCKET)));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.fluidizer), "olo", "cgc", "oro", 'l', "gemLapis", 'r',
						"dustRedstone", 'g', "blockGlass", 'c', new ItemStack(Items.CAULDRON), 'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.crystallizer), "olo", "cgc", "oro", 'l', "ingotGold", 'r',
						"dustRedstone", 'g', "blockGlass", 'c', new ItemStack(Items.CAULDRON), 'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.grinder), "oio", "fcf", "oro", 'i', "dustIron", 'r',
						"dustRedstone", 'c', "crystalTesla", 'f', new ItemStack(Items.FLINT), 'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.poweredFurnace), "odo", "fcf", "oro", 'd', "ingotIron", 'r',
						"dustRedstone", 'c', "crystalTesla", 'f', new ItemStack(Blocks.FURNACE), 'o', "ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.teslaCell), "oco", "oco", "oco", 'c', "crystalTesla", 'o',
						"ingotObsidian"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModBlocks.solarGen), "ccc", "oco", "oro", 'c', "crystalTesla", 'o',
						"ingotObsidian", 'r', "dustRedstone"));

	}

	static void registerCrystallizerRecipes()
	{
		CrystekApi.registerCrystallizerRecipes("dustIron", new FluidStack(ModFluids.fluidTesla, 1000),
				ItemBank.teslaCrystal);
		CrystekApi.registerCrystallizerRecipes("ingotIron", new FluidStack(ModFluids.fluidTesla, 10),
				ItemBank.teslaIngot);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystRed, new FluidStack(ModFluids.fluidTesla, 1000),
				ItemBank.teslaCrystalRed);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystYellow, new FluidStack(ModFluids.fluidTesla, 1000),
				ItemBank.teslaCrystalYellow);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystPurple, new FluidStack(ModFluids.fluidTesla, 1000),
				ItemBank.teslaCrystalPurple);
		CrystekApi.registerCrystallizerRecipes(ItemBank.catalystBlue, new FluidStack(ModFluids.fluidTesla, 1000),
				ItemBank.teslaCrystalBlue);
	}

	static void registerSmeltingRecipes()
	{
		//Dusts
		GameRegistry.addSmelting(ItemBank.ironDust, new ItemStack(Items.IRON_INGOT), 0.5F);
		GameRegistry.addSmelting(ItemBank.goldDust, new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(ItemBank.obsidianDust, ItemBank.obsidianIngot, 0.5F);
	}

	static void registerGrinderRecipes()
	{
		//General
		CrystekApi.registerGrinderRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.COBBLESTONE));
		CrystekApi.registerGrinderRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.GRAVEL));
		ItemStack dustObsidian = OreDictionary.getOres("dustObsidian").get(0).copy();
		CrystekApi.registerGrinderRecipe(dustObsidian, new ItemStack(Blocks.OBSIDIAN));

		//Ores
        //Todo recipe handler for this 
//		addOreGrinderRecipe("Iron");
//		addOreGrinderRecipe("Gold");
//		addOreGrinderRecipe("Coal");
//		addOreGrinderRecipe("Redstone");
//		addOreGrinderRecipe("Diamond");
//		addOreGrinderRecipe("Emerald");
//		CrystekApi.registerGrinderRecipe(new ItemStack(Items.DYE, 6, 11), "oreLapis");
//		CrystekApi.registerGrinderRecipe(new ItemStack(Items.QUARTZ, 2), "oreQuartz");
//		CrystekApi.registerGrinderRecipe(new ItemStack(Items.DYE, 9, 11), "blockLapis");
//		CrystekApi.registerGrinderRecipe(new ItemStack(Items.QUARTZ, 4), "blockQuartz");
//		addOreGrinderRecipe("Copper");
//		addOreGrinderRecipe("Tin");
//		addOreGrinderRecipe("Nickel");
//		addOreGrinderRecipe("Zinc");
//		addOreGrinderRecipe("Bronze");
//		addOreGrinderRecipe("Brass");
//		addOreGrinderRecipe("Silver");
//		addOreGrinderRecipe("Lead");
//		addOreGrinderRecipe("Steel");
//		addOreGrinderRecipe("Electrum");
//		addOreGrinderRecipe("AluminumBrass");
//		addOreGrinderRecipe("AluminiumBrass");
//		addOreGrinderRecipe("Invar");
//		addOreGrinderRecipe("Aluminum");
//		addOreGrinderRecipe("Aluminium");
//		addOreGrinderRecipe("Platinum");
	}

	public static void addOreGrinderRecipe(String name)
	{
		addGrinderOreDictRecipe("dust"+name, "ingot"+name);
		addGrinderOreDictRecipe("dust"+name, "gem"+name);
		addGrinderOreDictRecipe("dust" + name, 2, "ore" + name);
		addGrinderOreDictRecipe("dust"+name, 9, "block"+name);
	}

	public static void addGrinderOreDictRecipe(String oreDictOutput, int outputAmount, String oreDictInput)
	{
		if (OreDictionary.doesOreNameExist(oreDictOutput) && OreDictionary.doesOreNameExist(oreDictInput))
		{
			try
            {
				ItemStack output = OreDictionary.getOres(oreDictOutput).get(0).copy();
				output.stackSize = outputAmount;
				CrystekApi.registerGrinderRecipe(output, oreDictInput);
			}
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
		}
	}

	public static void addGrinderOreDictRecipe(String oreDictOutput, String oreDictInput)
	{
		addGrinderOreDictRecipe(oreDictOutput, 1, oreDictInput);
	}
}
