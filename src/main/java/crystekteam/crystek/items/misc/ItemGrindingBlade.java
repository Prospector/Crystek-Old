package crystekteam.crystek.items.misc;

import crystekteam.crystek.items.ItemCrystek;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

import java.util.List;

/**
 * Created by Prospector
 */
public class ItemGrindingBlade extends ItemCrystek {
	public ItemStack repairStack;
	public int speed;

	public ItemGrindingBlade(String name, int speed, int durability, ItemStack repairMaterial) {
		super(name + "_grinding_blade");
		setMaxStackSize(1);
		if (durability == -1) {
			setMaxDamage(0);
		} else {
			setMaxDamage(durability);
		}
		repairStack = repairMaterial;
		this.speed = speed;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		ItemGrindingBlade blade = (ItemGrindingBlade) stack.getItem();
		if (!repairStack.isEmpty())
			tooltip.add(TextFormatting.DARK_GRAY + "Repair with " + I18n.translateToLocal(blade.repairStack.getUnlocalizedName() + ".name"));
		else
			tooltip.add(TextFormatting.DARK_GRAY + "Unbreakable");
		if (stack.getMaxDamage() != 0)
			tooltip.add(TextFormatting.GRAY + "Durability: " + (stack.getMaxDamage() - stack.getItemDamage() + 1) + "/" + (stack.getMaxDamage() + 1));
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		if (stack.getMetadata() > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		if (repairStack.isEmpty()) {
			return false;
		}
		if (repairStack.isItemEqual(par2ItemStack))
			return true;
		else
			return false;
	}
}
