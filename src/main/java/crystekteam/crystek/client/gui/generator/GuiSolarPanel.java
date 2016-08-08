package crystekteam.crystek.client.gui.generator;

import crystekteam.crystek.client.gui.GuiBase;
import crystekteam.crystek.container.ContainerSolarPanel;
import crystekteam.crystek.tiles.generator.TileSolarPanel;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Travis on 24/06/2016.
 */
public class GuiSolarPanel extends GuiBase
{
    TileSolarPanel tile;

    public GuiSolarPanel(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerSolarPanel(tile, player), "crystek.solarpanel");
        this.tile = (TileSolarPanel) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
    }
}
