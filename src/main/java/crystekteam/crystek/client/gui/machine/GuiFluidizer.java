package crystekteam.crystek.client.gui.machine;

import crystekteam.crystek.client.gui.GuiBase;
import crystekteam.crystek.container.ContainerFluidizer;
import crystekteam.crystek.tiles.machines.TileFluidizer;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiFluidizer extends GuiBase
{
    TileFluidizer tileCrystallizer;

    public GuiFluidizer(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerFluidizer(tile, player), "crystek.fluidizer");
        this.tileCrystallizer = (TileFluidizer) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawTankOverlay(tileCrystallizer, 30, 8);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        builder.drawTank(this, tile.tank, 31, 9, zLevel, 14, 64, mouseX - guiLeft, mouseY - guiTop);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
