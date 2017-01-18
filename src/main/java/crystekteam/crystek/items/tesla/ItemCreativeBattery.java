package crystekteam.crystek.items.tesla;

import crystekteam.crystek.items.ItemCrystek;
import net.darkhax.tesla.api.implementation.*;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class ItemCreativeBattery extends ItemCrystek
{
    public ItemCreativeBattery()
    {
        super("creative_battery");
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        super.addInformation(stack, playerIn, tooltip, advanced);
        TeslaUtils.createTooltip(stack, tooltip);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new InfiniteTeslaProducerProvider();
    }
}
