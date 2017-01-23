package crystekteam.crystek.guis;

import crystekteam.crystek.container.ContainerConfig;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.network.PacketFacing;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import reborncore.common.network.NetworkManager;

import java.io.IOException;

/**
 * Created by Gigabit101 on 23/01/2017.
 */
public class GuiConfig extends GuiContainer
{
    CrystekBuilder builder = new CrystekBuilder();
    EntityPlayer player;
    Machine machine;

    public GuiConfig(EntityPlayer player, Machine machine)
    {
        super(new ContainerConfig(player, machine));
        this.machine = machine;
        this.player = player;
        buttonList.clear();
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {}

    int UP = 0;
    int DOWN = 1;
    int NORTH = 2;
    int EAST = 3;
    int SOUTH = 4;
    int WEST = 5;

    @Override
    public void initGui()
    {
        super.initGui();
        buttonList.add(new GuiButton(UP, guiLeft + 5, guiTop + 4, 40, 20, "UP"));
        buttonList.add(new GuiButton(DOWN, guiLeft + 130, guiTop + 4, 40, 20, "DOWN"));

        buttonList.add(new GuiButton(NORTH, guiLeft + 65, guiTop + 40, 40, 20, "NORTH"));
        buttonList.add(new GuiButton(EAST, guiLeft + 15, guiTop + 80, 40, 20, "EAST"));
        buttonList.add(new GuiButton(SOUTH, guiLeft + 65, guiTop + 120, 40, 20, "SOUTH"));
        buttonList.add(new GuiButton(WEST, guiLeft + 120, guiTop + 80, 40, 20, "WEST"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        super.actionPerformed(button);
        if(button.id == UP)
        {
            NetworkManager.sendToServer(new PacketFacing(machine.getPos(), EnumFacing.UP));
        }
        System.out.print(button.displayString);
    }
}
