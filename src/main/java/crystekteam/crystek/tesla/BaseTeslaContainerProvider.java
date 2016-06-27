package crystekteam.crystek.tesla;

import net.darkhax.tesla.api.BaseTeslaContainer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by Gigabit101 on 25/06/2016.
 */
public class BaseTeslaContainerProvider implements INBTSerializable<NBTTagCompound>, ICapabilityProvider
{
    private final BaseTeslaContainer container;

    public BaseTeslaContainerProvider(BaseTeslaContainer container)
    {
        this.container = container;
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing)
    {
        return capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_CONSUMER || capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return (T) this.container;

        return null;
    }

    @Override
    public NBTTagCompound serializeNBT ()
    {
        return this.container.serializeNBT();
    }

    @Override
    public void deserializeNBT (NBTTagCompound nbt)
    {
        this.container.deserializeNBT(nbt);
    }
}
