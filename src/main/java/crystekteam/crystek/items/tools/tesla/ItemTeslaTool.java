package crystekteam.crystek.items.tools.tesla;

import crystekteam.crystek.tesla.BaseTeslaContainerProvider;
import crystekteam.crystek.tesla.TeslaUtils;
import net.darkhax.tesla.api.BaseTeslaContainer;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;
import java.util.Set;

/**
 * Created by Gigabit101 on 26/06/2016.
 */
public class ItemTeslaTool extends ItemTool
{
    private long maxCapacity;
    private long output;
    private long input;

    public ItemTeslaTool(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn, long maxCapacity, long input, long output)
    {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        setMaxStackSize(1);
        setMaxDamage(240);
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
}
