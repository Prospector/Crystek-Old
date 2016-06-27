package crystekteam.crystek.tiles.prefab;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by Gigabit101 on 22/06/2016.
 */
public class TileTeslaStorage extends TileBase
{
    public TileTeslaStorage(int invSize, String invName, int invStackLimit, long maxCapacity, long input, long output, int tankamount)
    {
        super(invSize, invName, invStackLimit, maxCapacity, input, output, tankamount);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER)
            return (T) this.container;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER)
            return true;

        return super.hasCapability(capability, facing);
    }
}
