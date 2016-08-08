package crystekteam.crystek.client.gui.machine;

import crystekteam.crystek.client.gui.GuiBase;
import crystekteam.crystek.container.ContainerCrystallizer;
import crystekteam.crystek.tiles.machines.TileCrystallizer;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiCrystallizer extends GuiBase
{
    TileCrystallizer tileCrystallizer;
    public GuiCrystallizer(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerCrystallizer(tile, player), "crystek.crystallizer");
        this.tileCrystallizer = (TileCrystallizer) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
        drawTankOverlay(tileCrystallizer, 30, 8);
        builder.drawSlot(this, guiLeft + 60, guiTop + 34);
        builder.drawSlot(this, guiLeft + 120, guiTop + 34);
        builder.drawProgressBar(this, container.progress, guiLeft + 90, guiTop + 34);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        builder.drawTank(this, tile.tank, 31, 9, zLevel, 14, 64, mouseX - guiLeft, mouseY - guiTop);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
