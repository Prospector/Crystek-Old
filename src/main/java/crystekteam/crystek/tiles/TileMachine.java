package crystekteam.crystek.tiles;

import crystekteam.crystek.core.Machine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import reborncore.common.util.Tank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 15/01/2017.
 */
public class TileMachine extends TileEntity implements ITickable
{
    Machine machine;
    ItemStackHandler inv = new StackHandler();
    Tank tank = new Tank("", 0, this);

    public TileMachine(Machine machine)
    {
        this.machine = machine;
        this.inv.setSize(machine.getInvSize());
        this.tank.setCapacity(machine.getTankSize());
    }

    public TileMachine(){}

    public ItemStackHandler getInv()
    {
        return inv;
    }

    public Tank getTank()
    {
        return tank;
    }

    public boolean hasMachine(TileMachine tileMachine)
    {
        if(tileMachine.getMachine(tileMachine) != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public void update()
    {
        this.machine.update();
    }

    @Nullable
    public Machine getMachine(TileMachine tileMachine)
    {
        return tileMachine.machine;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        compound.merge(getInv().serializeNBT());
        compound.setInteger("machineInvSize", getInv().getSlots());
        tank.writeToNBT(compound);
        System.out.print("---------------Write to NBT----------");
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inv.setSize(compound.getInteger("machineInvSize"));
        tank.readFromNBT(compound);
        getInv().deserializeNBT(compound);
        System.out.print("---------------Read from NBT----------");
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> cap, @Nonnull EnumFacing side)
    {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY )
        {
            return true;
        }
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return true;
        }
        return super.hasCapability(cap, side);
    }

    @Nonnull
    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, @Nonnull EnumFacing side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getInv());
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(getTank());
        }
        return super.getCapability(cap, side);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(getPos(), getBlockMetadata(), writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
    {
        readFromNBT(packet.getNbtCompound());
    }

    class StackHandler extends ItemStackHandler
    {
        StackHandler()
        {
            super(1);
        }
    }
}
