package crystekteam.crystek.items.powered;

import crystekteam.crystek.CreativeTabCrystek;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.inventory.EntityEquipmentSlot;

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
}
