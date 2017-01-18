package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachineGenerator extends Machine
{
    @Override
    public int invSize()
    {
        return 1;
    }

    @Override
    public int guiID()
    {
        return 1;
    }

    @Override
    public String getName()
    {
        return "coalgenerator";
    }

    @Override
    public int getTankSize()
    {
        return 0;
    }

    @Nullable
    @Override
    public List<Slot> getSlots()
    {
        List<Slot> slots = new ArrayList<Slot>();
        slots.add(new SlotItemHandler(getInv(), 0, 80, 50));
        return slots;
    }

    @Override
    public long maxCapacity()
    {
        return 100000;
    }

    @Override
    public long maxInput()
    {
        return 0;
    }

    @Override
    public long maxOutput()
    {
        return 100;
    }

    @Override
    public EnumTeslaType teslaType()
    {
        return EnumTeslaType.GENERATOR;
    }
}
