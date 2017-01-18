package crystekteam.crystek.configs;

import crystekteam.crystek.core.Machine;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class MachineConfig
{
    long maxOutput;
    long maxInput;
    long maxCapacity;
    String name;

    public MachineConfig(Machine machine)
    {
        this.name = machine.getName();
        this.maxOutput = machine.maxOutput();
        this.maxInput = machine.maxInput();
        this.maxCapacity = machine.maxCapacity();
    }

    public long getMaxCapacity()
    {
        return maxCapacity;
    }

    public long getMaxInput()
    {
        return maxInput;
    }

    public long getMaxOutput()
    {
        return maxOutput;
    }

    public String getName()
    {
        return name;
    }
}
