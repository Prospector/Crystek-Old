package crystekteam.crystek.tiles.prefab;

import crystekteam.crystek.items.tools.ItemCircuit;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Created by Gigabit101 on 22/06/2016.
 */
public class TileGenerator extends TileBase
{
    public TileGenerator(int invSize, String invName, int invStackLimit, long maxCapacity, long input, long output, int tankamount)
    {
        super(invSize, invName, invStackLimit, maxCapacity, input, output, tankamount);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return (T) this.container;

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Override
    public void handleUpgrades(int upgradeslotstart, long baseoutput)
    {
        int i;
        for (i = upgradeslotstart; i < upgradeslotstart + 3; ++i)
        {
            if(getStackInSlot(i) != null && getStackInSlot(i).getItem() instanceof ItemCircuit)
            {
                int stacksize = getStackInSlot(i).stackSize;
                if(getStackInSlot(i).getMetadata() == 0)
                {
                    //TODO handle speed upgrades
                }
                if(getStackInSlot(i).getMetadata() == 1)
                {
                    //TODO handle capacity upgrades
                }
                if(getStackInSlot(i).getMetadata() == 2)
                {
                    if(getStoredPower() != getMaxCapacity())
                    {
                        generatePower((stacksize - 1) * baseoutput);
                    }
                }
            }
        }
    }
}
