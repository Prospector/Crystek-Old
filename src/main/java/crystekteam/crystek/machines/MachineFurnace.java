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
        setGuiID(1);
        setTileEntity(new TileMachine(this));
        setInvSize(2);
        addSlotToContainer(getInv(), 0, 50, 35);
        addSlotToContainer(getInv(), 1, 105, 35);
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
