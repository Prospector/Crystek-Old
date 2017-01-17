package crystekteam.crystek.machines;

import crystekteam.crystek.core.Machine;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Gigabit101 on 16/01/2017.
 */
public class MachineFurnace extends Machine
{
    public MachineFurnace()
    {
        super("furnace");
        this.setGuiID(1);
        this.setInvSize(2);
        this.addSlotToContainer(0, 50, 35);
        this.addSlotToContainer(1, 105, 35);
        this.setTankSize(1000);
    }

    @Override
    public int getInvSize()
    {
        return 2;
    }

    @Override @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiContainer gui)
    {
        builder.drawProgressBar(gui, 0, 75, 35);
    }
}
