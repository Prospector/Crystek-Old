package crystekteam.crystek.tiles.prefab;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by Gigabit101 on 22/06/2016.
 */
public class TileMachine extends TileBase
{
    public TileMachine(int invSize, String invName, int invStackLimit, long maxCapacity, long input, long output, int tankamount, String tankname)
    {
        super(invSize, invName, invStackLimit, maxCapacity, input, output, tankamount, tankname);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER)
            return (T) this.container;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_HOLDER || capability == TeslaCapabilities.CAPABILITY_CONSUMER)
            return true;

        return super.hasCapability(capability, facing);
    }
}
