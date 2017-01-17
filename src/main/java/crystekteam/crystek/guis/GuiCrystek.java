package crystekteam.crystek.guis;

import crystekteam.crystek.container.ContainerCrystek;
import crystekteam.crystek.container.ContainerDummy;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class GuiCrystek extends GuiContainer
{
    public TileMachine machine;
    public EntityPlayer player;

    public GuiCrystek(EntityPlayer player, TileMachine machine)
    {
        super(new ContainerCrystek(player, machine));
        this.machine = machine;
        this.player = player;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        machine.getMachine(machine).drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, this);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        machine.getMachine(machine).drawGuiContainerForegroundLayer(mouseX, mouseY, this);
    }
}
