package crystekteam.crystekold.tiles.prefab;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

/**
 * Created by Gigabit101 on 22/06/2016.
 */
public class TileMachine extends TileBase
{
    public TileMachine(int invSize, String invName, int invStackLimit, long maxCapacity, long input, long output, int tankamount, int maxProgress)
    {
        super(invSize, invName, invStackLimit, maxCapacity, input, output, tankamount, maxProgress);
        this.hasInv = true;
        this.hasTank = false;
        this.hasTesla = true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER)
        {
            return (T) this.container;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return (T) tank;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER)
        {
            return true;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}
