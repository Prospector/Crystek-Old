package crystekteam.crystek.machines;

import crystekteam.crystek.core.Machine;
import crystekteam.crystek.tiles.TileMachine;

/**
 * Created by Gigabit101 on 16/01/2017.
 */
public class MachineFurnace extends Machine
{
    public MachineFurnace()
    {
        super("furnace");
        setGuiID(1);
        setTileEntity(new TileMachine(this));
        setInvSize(2);
        addSlotToContainer(getInv(), 0, 50, 50);
        addSlotToContainer(getInv(), 1, 80, 50);
    }

    @Override
    public int getInvSize()
    {
        return 2;
    }
}
