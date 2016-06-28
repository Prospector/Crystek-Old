package crystekteam.crystek.items.armour;

import crystekteam.crystek.tesla.BaseTeslaContainerProvider;
import crystekteam.crystek.tesla.TeslaUtils;
import net.darkhax.tesla.api.BaseTeslaContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;

/**
 * Created by Giabit101 on 28/06/2016.
 */
public class ItemTeslaArmour extends ItemArmor implements ISpecialArmor
{
    private long maxCapacity;
    private long output;
    private long input;

    long cost = 20;
    public ItemTeslaArmour(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, long maxCapacity, long input, long output)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Override public boolean isRepairable()
    {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return (1-(double) TeslaUtils.getStoredPower(stack) / (double) TeslaUtils.getMaxCapacity(stack));
    }

    @Override public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(I18n.format(TextFormatting.AQUA + "" + TeslaUtils.getStoredPower(stack) + "/ " + TeslaUtils.getMaxCapacity(stack)));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new BaseTeslaContainerProvider(new BaseTeslaContainer(), maxCapacity, output, input);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        TeslaUtils.usePower(stack, damage * cost);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }
}
