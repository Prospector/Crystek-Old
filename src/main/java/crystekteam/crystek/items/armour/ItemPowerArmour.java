package crystekteam.crystek.items.armour;

import crystekteam.crystek.CreativeTabCrystek;
import crystekteam.crystek.items.armour.ItemTeslaArmour;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Gigabit101 on 28/06/2016.
 */
public class ItemPowerArmour extends ItemTeslaArmour
{
    public ItemPowerArmour(ArmorMaterial material, EntityEquipmentSlot slot)
    {
        super(material, material.getDamageReductionAmount(slot), slot);
        if (slot == EntityEquipmentSlot.HEAD)
            setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".powerarmour.helmet");
        if (slot == EntityEquipmentSlot.CHEST)
            setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".powerarmour.chestplate");
        if (slot == EntityEquipmentSlot.LEGS)
            setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".powerarmour.leggings");
        if (slot == EntityEquipmentSlot.FEET)
            setUnlocalizedName(ModInfo.MOD_NAME.toLowerCase() + ".powerarmour.boots");
        setCreativeTab(CreativeTabCrystek.instance);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(TextFormatting.DARK_PURPLE + "  (WIP)");
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
