package crystekteam.crystek.items.armour;

import crystekteam.crystek.CreativeTabCrystek;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Gigabit101 on 28/06/2016.
 */
public class ItemInfusedArmour extends ItemTeslaArmour
{
	public ItemInfusedArmour(ArmorMaterial material, EntityEquipmentSlot slot, long maxCapacity, long input,
			long output)
	{
		super(material, material.getDamageReductionAmount(slot), slot, maxCapacity, input, output);
		if (slot == EntityEquipmentSlot.HEAD)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".infusedarmour.helmet");
		if (slot == EntityEquipmentSlot.CHEST)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".infusedarmour.chestplate");
		if (slot == EntityEquipmentSlot.LEGS)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".infusedarmour.leggings");
		if (slot == EntityEquipmentSlot.FEET)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".infusedarmour.boots");
		setCreativeTab(CreativeTabCrystek.instance);
	}

	@Override public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add(TextFormatting.RED + "" + TextFormatting.BOLD + "<WIP>");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
