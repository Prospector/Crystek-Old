package crystekteam.crystek.tiles;

import crystekteam.crystek.core.Machine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 15/01/2017.
 */
public class TileMachine extends TileEntity implements ITickable
{
    Machine machine;

    public TileMachine(Machine machine)
    {
        this.machine = machine;
    }

    public void setMachine(Machine machine)
    {
        this.machine = machine;
    }

    @Override
    public void update()
    {
        machine.update();
    }

    public Machine getMachine()
    {
        return machine;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        machine.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        machine.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> cap, @Nonnull EnumFacing side)
    {
        return machine.hasInv() && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
    }

    @Nonnull
    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, @Nonnull EnumFacing side)
    {
        if (machine.hasInv() && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(machine.getInv());
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
}
