package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
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

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui, int guiLeft, int guiTop)
    {
        builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX - guiLeft, mouseY - guiTop);
    }

    @Override
    public void update()
    {
        int fuelSlot = 0;
//        if(getInv().getStackInSlot(fuelSlot) != ItemStack.EMPTY && getItemBurnTime(getInv().getStackInSlot(fuelSlot)) < 0)
//        {
            getTeslaContainer().givePower(100, false);
//        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        return TileEntityFurnace.getItemBurnTime(stack) / 4;
    }
}
