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

	public static ItemStack efficiencyUpgrade = ItemCircuit.getStack("circuitSpeed");
	public static ItemStack speedUpgrade = ItemCircuit.getStack("circuitEfficiency");
	public static ItemStack storageUpgrade = ItemCircuit.getStack("circuitStorage");
}
