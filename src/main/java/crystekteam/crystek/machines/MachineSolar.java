package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Gigabit101 on 22/01/2017.
 */
public class MachineSolar extends Machine
{
    @Override
    public int invSize()
    {
        return 0;
    }

    @Override
    public int guiID()
    {
        return 5;
    }

    @Override
    public String getName()
    {
        return "solar_panel";
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
        return null;
    }

    @Override
    public long maxCapacity()
    {
        return 1000;
    }

    @Override
    public long maxInput()
    {
        return 5;
    }

    @Override
    public long maxOutput()
    {
        return 5;
    }

    @Override
    public EnumTeslaType teslaType()
    {
        return EnumTeslaType.GENERATOR;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
        builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX, mouseY, layer);
    }

    @Override
    public void update()
    {
        super.update();
        if (this.hasWorld() && !this.world.provider.hasNoSky() && this.world.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.world.isRaining() && this.world.getSkylightSubtracted() == 0 && this.getTeslaContainer().getStoredPower() != this.getTeslaContainer().getCapacity())
        {
            getTeslaContainer().givePower(5, false);
            updateState(true);
        }
        else
        {
            updateState(false);
        }
    }
}
