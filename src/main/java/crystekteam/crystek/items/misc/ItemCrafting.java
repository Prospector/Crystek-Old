package crystekteam.crystek.items.misc;

import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ItemCrafting extends ItemBase
{
	public static final String[] types = new String[] { "dustIron", "dustGold", "dustDiamond", "dustEmerald",
			"dustTesla", "dustObsidian", "ingotTeslaAlloy", "ingotObsidian", "catalystRed", "catalystYellow",
			"catalystPurple", "catalystBlue", "crystalTesla", "crystalTeslaRed", "crystalTeslaYellow",
			"crystalTeslaPurple", "crystalTeslaBlue" };

	public ItemCrafting()
	{
		setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".crafting");
		setHasSubtypes(true);
		setRegistryName("crafting");
	}

	public static ItemStack getStack(String name, int count)
	{
		for (int i = 0; i < types.length; i++)
		{
			if (types[i].equalsIgnoreCase(name))
			{
				return new ItemStack(ModItems.crafting, count, i);
			}
		}
		throw new InvalidParameterException("Stack not found: " + name);
	}

	public static ItemStack getStack(String name)
	{
		return getStack(name, 1);
	}

	@Override public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int meta = 0; meta < types.length; meta++)
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}

	@Override
	// gets Unlocalized Name depending on meta data
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= types.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + types[meta];
	}

	@Override public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if (types[stack.getMetadata()] == "catalystRed"||types[stack.getMetadata()] == "crystalTeslaRed")
		{
			tooltip.add(TextFormatting.RED + "Speed");
			tooltip.add(TextFormatting.RED + "   +200% Speed");
			tooltip.add(TextFormatting.RED + "   +200% Energy Cost");
		}
		if (types[stack.getMetadata()] == "catalystYellow"||types[stack.getMetadata()] == "crystalTeslaYellow")
		{
			tooltip.add(TextFormatting.YELLOW + "Efficiency");
			tooltip.add(TextFormatting.YELLOW + "   -50% Speed");
			tooltip.add(TextFormatting.YELLOW + "   -50% Energy Cost");
		}
		if (types[stack.getMetadata()] == "catalystPurple"||types[stack.getMetadata()] == "crystalTeslaPurple")
		{
			tooltip.add(TextFormatting.DARK_PURPLE + "Oblivion");
			tooltip.add(TextFormatting.DARK_PURPLE + "   +Instant Speed");
			tooltip.add(TextFormatting.DARK_PURPLE + "   +1600% Energy Cost");
		}
		if (types[stack.getMetadata()] == "catalystBlue"||types[stack.getMetadata()] == "crystalTeslaBlue")
		{
			tooltip.add(TextFormatting.BLUE + "Luck");
			tooltip.add(TextFormatting.BLUE + "   +33% Fortune");
			tooltip.add(TextFormatting.BLUE + "   +100% Energy Cost");
		}
		if (types[stack.getMetadata()] == "crystalTesla")
		{
			tooltip.add(TextFormatting.GRAY + "No Catalyst");
		}
	}
}
