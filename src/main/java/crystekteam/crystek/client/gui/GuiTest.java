package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerTrashCan;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import reborncore.client.guibuilder.GuiBuilder;

/**
 * Created by Gigabit101 on 08/08/2016.
 */
@Deprecated
public class GuiTest extends GuiContainer
{
    GuiBuilder builder = new GuiBuilder(GuiBuilder.defaultTextureSheet);
    public GuiTest(EntityPlayer player, TileBase tile)
    {
        super(new ContainerTrashCan(tile, player));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        builder.drawSlot(this, guiLeft + 79, guiTop + 34);

        builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 83, true);
    }
}
