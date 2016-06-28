package crystekteam.crystek.items.misc;

import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ItemCrafting extends ItemBase
{
	public static final String[] types = new String[] { "dustIron", "dustGold", "dustDiamond", "dustEmerald",
			"dustTesla", "dustObsidian", "ingotTeslaAlloy", "ingotObsidian", "crystalTesla" };

	public ItemCrafting()
	{
		setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".crafting");
		setHasSubtypes(true);
		setRegistryName("crafting");
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

	public static ItemStack getStack(String name){
		return getStack(name, 1);
	}
}
