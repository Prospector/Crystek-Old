package crystekteam.crystek.machines;

import crystekteam.crystek.container.ContainerCrystek;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import crystekteam.crystek.tiles.TileMachine;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachineGenerator extends Machine
{
    public MachineGenerator()
    {
        super("coalgenerator");
        setContainer(new ContainerCrystek());
        setGuiContainer(new GuiCrystek(getContainer()));
        setGuiID(0);
        setTileEntity(new TileMachine(this));
    }

    @Override
    public void update()
    {
        super.update();
    }
}
