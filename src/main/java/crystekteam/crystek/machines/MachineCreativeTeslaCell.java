package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.darkhax.tesla.api.implementation.InfiniteTeslaProducer;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class MachineCreativeTeslaCell extends Machine
{
    @Override
    public int invSize()
    {
        return 0;
    }

    @Override
    public int guiID()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "creative_cell";
    }

    @Override
    public int getTankSize()
    {
        return 0;
    }

    @Nullable
    @Override
    public List<Slot> getSlots()
    {
        return null;
    }

    @Override
    public long maxCapacity()
    {
        return 0;
    }

    @Override
    public long maxInput()
    {
        return 0;
    }

    @Override
    public long maxOutput()
    {
        return 0;
    }

    @Override
    public EnumTeslaType teslaType()
    {
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new MachineCreativeTeslaCell();
    }

    @Override
    public <T> T getCapability (Capability<T> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
        {
            return (T) new InfiniteTeslaProducer();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public void update()
    {
        TeslaUtils.distributePowerToAllFaces(this.world, this.pos, 50, false);
    }
}
