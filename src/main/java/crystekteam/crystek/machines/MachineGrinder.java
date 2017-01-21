package crystekteam.crystek.machines;

import crystekteam.crystek.container.slots.SlotItemHandlerOutput;
import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 21/01/2017.
 */
public class MachineGrinder extends Machine
{
    @Override
    public int invSize()
    {
        return 4;
    }

    @Override
    public int guiID()
    {
        return 4;
    }

    @Override
    public String getName()
    {
        return "grinder";
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
        slots.add(new SlotItemHandler(getInv(), 3, 78, 45));
        return slots;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
        builder.drawProgressBar(gui, this.getProgress(), this.getMaxProgress(), 73, 35, mouseX, mouseY, GuiCrystek.Layer.FOREGROUND);
        builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX, mouseY, layer);
    }

    @Override
    public long maxCapacity()
    {
        return 10000;
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

    @Override
    public void update()
    {
        super.update();
    }
}
