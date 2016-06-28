package crystekteam.crystek.tiles.prefab;

import crystekteam.crystek.config.ConfigAE;
import crystekteam.crystek.items.tools.ItemCircuit;
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

    @Override
    public void handleUpgrades(int upgradeslotstart, long maxcapcity)
    {
        int i;
        for (i = upgradeslotstart; i < upgradeslotstart + 3; ++i)
        {
            if(getStackInSlot(i) != null && getStackInSlot(i).getItem() instanceof ItemCircuit)
            {
                if(getStackInSlot(i).getMetadata() == 1)
                {
                    long upgradeamount = 100000;
                    long base = ConfigAE.teslacellBaseStorage;
                    container.setCapacity(base + (getStackInSlot(i).stackSize * upgradeamount));
                    break;
                }
            }
            //reset
            else if(getStackInSlot(i) == null && getMaxCapacity() != maxcapcity)
            {
                container.setCapacity(maxcapcity);
            }
        }
    }
}
