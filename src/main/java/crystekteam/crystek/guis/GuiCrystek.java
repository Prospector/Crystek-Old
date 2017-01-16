package crystekteam.crystek.guis;

import crystekteam.crystek.container.ContainerCrystek;
import crystekteam.crystek.core.Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import reborncore.client.guibuilder.GuiBuilder;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class GuiCrystek extends GuiContainer
{
    public Machine machine;
    public EntityPlayer player;

    public GuiCrystek(EntityPlayer player, Machine machine)
    {
        super(new ContainerCrystek(player, machine));
        this.machine = machine;
        this.player = player;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        machine.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, this);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        machine.drawGuiContainerForegroundLayer(mouseX, mouseY, this);
    }
}
