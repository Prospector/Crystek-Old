package crystekteam.crystek.machines;

import crystekteam.crystek.core.Machine;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachineGenerator extends Machine
{
    public MachineGenerator()
    {
        super("coalgenerator");
        setGuiID(0);
        setInvSize(1);
        addSlotToContainer(0, 80, 50);
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
