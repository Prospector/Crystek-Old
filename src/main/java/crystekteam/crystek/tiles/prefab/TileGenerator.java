package crystekteam.crystek.tiles.prefab;

import crystekteam.crystek.items.tools.ItemCircuit;
import crystekteam.crystek.tesla.TeslaUtils;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

/**
 * Created by Gigabit101 on 22/06/2016.
 */
public class TileGenerator extends TileBase
{
    public TileGenerator(int invSize, String invName, int invStackLimit, long maxCapacity, long input, long output, int tankamount, int maxProgress)
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
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return (T) this.container;
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) tank;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER)
            return true;
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    public void transferPowerTo(EnumFacing facing)
    {
        TileEntity tileEntity = worldObj.getTileEntity(getPos().offset(facing));
        if (tileEntity != null && TeslaUtils.isTelsaBlock(tileEntity) && TeslaUtils.isConsumer(tileEntity))
        {
            if (TeslaUtils.canAcceptPower(tileEntity, this.container.getOutputRate()) && this.container.getStoredPower() >= this.container.getOutputRate())
            {
                TeslaUtils.addPower(tileEntity, this.container.getOutputRate());
                this.container.takePower(this.container.getOutputRate(), false);
            }
            if (TeslaUtils.canAcceptPower(tileEntity, this.container.getOutputRate()) && this.container.getStoredPower() < this.container.getOutputRate())
            {
                TeslaUtils.addPower(tileEntity, this.container.getStoredPower());
                this.container.takePower(this.container.getStoredPower(), false);
            }
        }
    }

    @Override
    public void handleUpgrades(int upgradeslotstart, long baseoutput)
    {
        int i;
        for (i = upgradeslotstart; i < upgradeslotstart + 3; ++i)
        {
            if (getStackInSlot(i) != null && getStackInSlot(i).getItem() instanceof ItemCircuit)
            {
                int stacksize = getStackInSlot(i).stackSize;
                if (getStackInSlot(i).getMetadata() == 0)
                {
                    //TODO handle speed upgrades
                }
                if (getStackInSlot(i).getMetadata() == 1)
                {
                    //TODO handle capacity upgrades
                }
                if (getStackInSlot(i).getMetadata() == 2)
                {
                    if (getStoredPower() != getMaxCapacity())
                    {
                        generatePower((stacksize - 1) * baseoutput);
                    }
                }
            }
        }
    }
}
