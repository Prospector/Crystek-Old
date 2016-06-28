package crystekteam.crystek.util;

import crystekteam.crystek.items.misc.ItemCrafting;
import crystekteam.crystek.items.tools.ItemCircuit;
import net.minecraft.item.ItemStack;

/**
 * @author Prospector on 25/06/16
 */
public class ItemBank
{
	public static ItemStack ironDust = ItemCrafting.getStack("dustIron");
	public static ItemStack goldDust = ItemCrafting.getStack("dustGold");
	public static ItemStack diamondDust = ItemCrafting.getStack("dustDiamond");
	public static ItemStack emeraldDust = ItemCrafting.getStack("dustEmerald");
	public static ItemStack teslaDust = ItemCrafting.getStack("dustTesla");
	public static ItemStack obsidianDust = ItemCrafting.getStack("dustObsidian");

	public static ItemStack teslaIngot = ItemCrafting.getStack("ingotTeslaAlloy");
	public static ItemStack obsidianIngot = ItemCrafting.getStack("ingotObsidian");

	public static ItemStack teslaCrystal = ItemCrafting.getStack("crystalTesla");
	public static ItemStack teslaCrystalRed = ItemCrafting.getStack("crystalTeslaRed");
	public static ItemStack teslaCrystalYellow = ItemCrafting.getStack("crystalTeslaYellow");
	public static ItemStack teslaCrystalPurple = ItemCrafting.getStack("crystalTeslaPurple");
	public static ItemStack teslaCrystalBlue = ItemCrafting.getStack("crystalTeslaBlue");

	public static ItemStack catalystRed = ItemCrafting.getStack("catalystRed");
	public static ItemStack catalystYellow = ItemCrafting.getStack("catalystYellow");
	public static ItemStack catalystPurple = ItemCrafting.getStack("catalystPurple");
	public static ItemStack catalystBlue = ItemCrafting.getStack("catalystBlue");

	public static ItemStack efficiencyUpgrade = ItemCircuit.getStack("circuitSpeed");
	public static ItemStack speedUpgrade = ItemCircuit.getStack("circuitEfficiency");
	public static ItemStack storageUpgrade = ItemCircuit.getStack("circuitStorage");
}
