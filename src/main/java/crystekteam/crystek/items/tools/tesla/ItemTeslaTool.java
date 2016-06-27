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
    public ItemTeslaTool(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
    {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        setMaxStackSize(1);
        setMaxDamage(240);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add(I18n.format(TextFormatting.AQUA + "" + TeslaUtils.getStoredPower(stack) + "/ " + TeslaUtils.getMaxCapacity(stack)));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new BaseTeslaContainerProvider(new BaseTeslaContainer());
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        double charge = (TeslaUtils.getMaxCapacity(stack) - TeslaUtils.getStoredPower(stack));
        if(TeslaUtils.getStoredPower(stack) == 0)
            return TeslaUtils.getMaxCapacity(stack);
        return charge;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }
}
