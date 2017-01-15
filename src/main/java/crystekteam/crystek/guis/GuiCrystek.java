package crystekteam.crystek.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import reborncore.client.guibuilder.GuiBuilder;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class GuiCrystek extends GuiContainer
{
    public GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);

    public GuiCrystek(Container inventorySlotsIn)
    {
        super(inventorySlotsIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);

        builder.drawSlot(this, guiLeft + 40, guiTop + 30);
        builder.drawSlot(this, guiLeft + 120, guiTop + 30);

        builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }
}
