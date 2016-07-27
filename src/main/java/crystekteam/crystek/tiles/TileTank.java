package crystekteam.crystek.tiles;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

/**
 * Created by Gigabit101 on 01/07/2016.
 */
public class TileTank extends TileBase
{
    public TileTank()
    {
        super(0, "", 0, 0, 0, 0, 8000, 0);
        this.hasInv = false;
        this.hasTank = true;
        this.hasTesla = false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing)
    {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return (T) tank;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing)
    {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
}
