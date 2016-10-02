package crystekteam.crystek.items.armour;

import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tesla.TeslaUtils;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;

/**
 * Created by Giabit101 on 28/06/2016.
 */
public class ItemTeslaArmour extends ItemArmor// implements ISpecialArmor
{
    long cost = 20;
    private long maxCapacity;
    private long output;
    private long input;

    public ItemTeslaArmour(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, long maxCapacity, long input, long output)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        ItemStack powered = TeslaUtils.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    @Override
    public boolean isRepairable()
    {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return (1 - (double) TeslaUtils.getStoredPower(stack) / (double) TeslaUtils.getMaxCapacity(stack));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(I18n.format(TextFormatting.DARK_AQUA + "" + TeslaUtils.getStoredPower(stack) + "/" + TeslaUtils
                .getMaxCapacity(stack) + " Tesla"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new BaseTeslaContainerProvider(new BaseTeslaContainer(maxCapacity, output, input));
    }

//	@Override public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
//	{
//		return null;
//	}
//
//	@Override public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
//	{
//		return 0;
//	}
//
//	@Override public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage,
//			int slot)
//	{
//		TeslaUtils.usePower(stack, damage * cost);
//	}

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return ModInfo.MOD_ID + ":textures/armour/infused";
    }
}
