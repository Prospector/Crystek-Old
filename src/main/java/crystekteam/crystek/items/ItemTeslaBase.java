package crystekteam.crystek.items;

import crystekteam.crystek.tesla.BaseTeslaContainerProvider;
import crystekteam.crystek.tesla.TeslaUtils;
import net.darkhax.tesla.api.BaseTeslaContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;

/**
 * Created by Gigabit101 on 25/06/2016.
 */
public class ItemTeslaBase extends ItemBase
{
	private long maxCapacity;
	private long output;
	private long input;

	public ItemTeslaBase(long maxCapacity, long input, long output)
	{
		setMaxStackSize(1);
		this.maxCapacity = maxCapacity;
		this.output = output;
		this.input = input;
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
	{
		ItemStack powered = TeslaUtils.createChargedStack(new ItemStack(itemIn));
		ItemStack unpowered = new ItemStack(itemIn);
		subItems.add(powered);
		subItems.add(unpowered);
	}

	@Override public boolean isRepairable()
	{
		return false;
	}

	@Override public double getDurabilityForDisplay(ItemStack stack)
	{
		return (1 - (double) TeslaUtils.getStoredPower(stack) / (double) TeslaUtils.getMaxCapacity(stack));
	}

	@Override public boolean showDurabilityBar(ItemStack stack)
	{
		return true;
	}

	@Override public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add(I18n.format(TextFormatting.DARK_AQUA + "" + TeslaUtils.getStoredPower(stack) + "/" + TeslaUtils
				.getMaxCapacity(stack) + " Tesla"));
	}

	@Override public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		return new BaseTeslaContainerProvider(new BaseTeslaContainer(), maxCapacity, output, input);
	}
}
