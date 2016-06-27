package crystekteam.crystek.items.misc;

import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.items.ItemBase;
import net.minecraft.item.ItemStack;

public class ItemGrindingBlade extends ItemBase
{

	ItemStack repairStack;
	int speed;

	public ItemGrindingBlade(String name, int speed, int durability, ItemStack repairMaterial)
	{
		setMaxStackSize(1);
		setRegistryName(name);
		setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() +"."+ name);
		setMaxDamage(durability);
		repairStack = repairMaterial;
		this.speed=speed;
	}

	public int getSpeed(){
		return speed;
	}

	@Override public boolean showDurabilityBar(ItemStack stack)
	{
		if(stack.getMetadata()>0)
			return true;
		else
			return false;
	}

	@Override public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (repairStack.isItemEqual(par2ItemStack))
			return true;
		else
			return false;
	}

}
