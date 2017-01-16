package crystekteam.crystek.init;

import crystekteam.crystek.core.Machine;
import crystekteam.crystek.machines.MachineFurnace;
import crystekteam.crystek.machines.MachineGenerator;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachinesInit
{
    static List<Machine> MACHINE_LIST = new ArrayList<Machine>();

    public static void init()
    {
        registerMachine(new MachineGenerator());
        registerMachine(new MachineFurnace());

        CrystekBlocks.init();
        GameRegistry.registerTileEntity(TileMachine.class, "MachineTile");
    }

    public static void registerMachine(Machine machine)
    {
        MACHINE_LIST.add(machine);
    }

    public static List<Machine> getMachineList()
    {
        return MACHINE_LIST;
    }
}
