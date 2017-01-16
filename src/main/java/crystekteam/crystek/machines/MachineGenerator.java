package crystekteam.crystek.machines;

import crystekteam.crystek.core.Machine;
import crystekteam.crystek.tiles.TileMachine;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachineGenerator extends Machine
{
    public MachineGenerator()
    {
        super("coalgenerator");
        setGuiID(0);
        setTileEntity(new TileMachine(this));
        setInvSize(1);
        addSlotToContainer(getInv(), 0, 80, 50);
    }

    @Override
    public int getInvSize()
    {
        return 1;
    }

    @Override
    public void update()
    {
        super.update();
    }
}
