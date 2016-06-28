package crystekteam.crystek.items.misc;

import crystekteam.crystek.items.ItemBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

import java.util.List;

public class ItemGrindingBlade extends ItemBase
{

	ItemStack repairStack;
	int speed;

	public ItemGrindingBlade(String name, int speed, int durability, ItemStack repairMaterial)
	{
		setMaxStackSize(1);
		setRegistryName(name);
		setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + "." + name);
		if (durability == -1)
		{
			setMaxDamage(0);
		} else
		{
			setMaxDamage(durability);
		}
		repairStack = repairMaterial;
		this.speed = speed;
	}

	@Override public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		ItemGrindingBlade blade = (ItemGrindingBlade) stack.getItem();
		tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocal("desc.grindingblade") + " " + I18n
				.translateToLocal(blade.repairStack.getUnlocalizedName() + ".name"));
		switch (speed)
		{
			case 0:
				tooltip.add(TextFormatting.GRAY + "Slow Speed");
			case 1:
				tooltip.add(TextFormatting.GRAY + "Average Speed");
			case 2:
				tooltip.add(TextFormatting.GRAY + "Above Average Speed");
			case 3:
				tooltip.add(TextFormatting.GRAY + "Fast Speed");
		}
		if (stack.getMaxDamage() != 0)
		{
			tooltip.add(
					TextFormatting.GRAY + "Durability: " + (stack.getMaxDamage() - stack.getItemDamage() + 1) + "/" + (
							stack.getMaxDamage() + 1));
		} else
		{
			tooltip.add(TextFormatting.GRAY + "Unbreakable");
		}

	}

	public int getSpeed()
	{
		return speed;
	}

	@Override public boolean showDurabilityBar(ItemStack stack)
	{
		if (stack.getMetadata() > 0)
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
