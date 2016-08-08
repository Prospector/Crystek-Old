package crystekteam.crystek.items.armour;

import crystekteam.crystek.CreativeTabCrystek;
import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Gigabit101 on 28/06/2016.
 */
public class ItemJetArmour extends ItemTeslaArmour
{
	public ItemJetArmour(ArmorMaterial material, EntityEquipmentSlot slot, long maxCapacity, long input, long output)
	{
		super(material, material.getDamageReductionAmount(slot), slot, maxCapacity, input, output);
		if (slot == EntityEquipmentSlot.HEAD)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".jetsuit.helmet");
		if (slot == EntityEquipmentSlot.CHEST)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".jetsuit.chestplate");
		if (slot == EntityEquipmentSlot.LEGS)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".jetsuit.leggings");
		if (slot == EntityEquipmentSlot.FEET)
			setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".jetsuit.boots");
		setCreativeTab(CreativeTabCrystek.instance);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (player.inventory.armorItemInSlot(3) != null
				&& player.inventory.armorItemInSlot(3).getItem() == ModItems.jetSuitHelmet
				&& player.inventory.armorItemInSlot(2) != null
				&& player.inventory.armorItemInSlot(2).getItem() == ModItems.jetSuitChestplate
				&& player.inventory.armorItemInSlot(1) != null
				&& player.inventory.armorItemInSlot(1).getItem() == ModItems.jetSuitLeggings
				&& player.inventory.armorItemInSlot(0) != null
				&& player.inventory.armorItemInSlot(0).getItem() == ModItems.jetSuitBoots)
		{
//			if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
//			player.motionY = 0.3;
		}
	}

	@Override public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add(TextFormatting.RED + "" + TextFormatting.BOLD + "<WIP>");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override public String getArmorTexture(ItemStack itemStack, Entity entity, EntityEquipmentSlot slot, String layer)
	{
		if (armorType == EntityEquipmentSlot.LEGS)
		{
			return "crystek:textures/armour/infused_layer_2.png";
		}
		return "crystek:textures/armour/infused_layer_1.png";
	}
}
