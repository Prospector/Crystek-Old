package crystekteam.crystek.machines;

import crystekteam.crystek.container.slots.SlotItemHandlerOutput;
import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 16/01/2017.
 */
public class MachineFurnace extends Machine
{
    @Override
    public int invSize()
    {
        return 3;
    }

    @Override
    public int guiID()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "furnace";
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
        slots.add(new SlotItemHandler(getInv(), 0, 50, 35));
        slots.add(new SlotItemHandlerOutput(getInv(), 1, 105, 35));
        slots.add(new SlotTeslaCharge(getInv(), 2, 8, 60));
        return slots;
    }

    @Override @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui, int guiLeft, int guiTop)
    {
        builder.drawProgressBar(gui, 0, 75, 35);
        builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX - guiLeft, mouseY - guiTop);
    }

    @Override
    public long maxCapacity()
    {
        return 100000;
    }

    @Override
    public long maxInput()
    {
        return 100;
    }

    @Override
    public long maxOutput()
    {
        return 0;
    }

    @Override
    public EnumTeslaType teslaType()
    {
        return EnumTeslaType.CONSUMER;
    }
}