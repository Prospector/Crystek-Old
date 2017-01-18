package crystekteam.crystek.configs;

import crystekteam.crystek.core.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class ConfigRegistry
{
    public static List<MachineConfig> CONFIG_LIST = new ArrayList<MachineConfig>();

    public static void registerNewMachineConfig(Machine machine)
    {
        CONFIG_LIST.add(new MachineConfig(machine));
    }

    public static List<MachineConfig> getConfigList()
    {
        return CONFIG_LIST;
    }
}
