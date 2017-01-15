package crystekteam.crystek.tiles;

import crystekteam.crystek.core.Machine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;
import reborncore.common.util.Tank;

/**
 * Created by Gigabit101 on 15/01/2017.
 */
public class TileMachine extends TileEntity implements ITickable
{
    Machine machine;
    //TODO
    ItemStackHandler inv = new StackHandler(0);
    Tank tank = new Tank("", 0, this);

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

    //TODO move
    class StackHandler extends ItemStackHandler
    {
        StackHandler(int size)
        {
            super(size);
        }
    }
}
