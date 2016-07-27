package crystekteam.crystek.tiles;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

/**
 * Created by Gigabit101 on 27/07/2016.
 */
public class TileTrashCan extends TileBase
{
    public TileTrashCan()
    {
        super(1, "trashcan", 64, 0, 0, 0, 8000, 0);
    }

    @Override
    public void update()
    {
        if(inv.getStackInSlot(0) != null)
            inv.setInventorySlotContents(0, null);
        if(tank.getFluid() != null)
            tank.setFluid(null);

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
