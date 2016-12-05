package crystekteam.crystekold.client.gui;

import crystekteam.crystekold.container.ContainerTeslaCell;
import crystekteam.crystekold.tiles.prefab.TileBase;
import crystekteam.crystekold.tiles.prefab.TileTeslaStorage;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Travis on 24/06/2016.
 */
public class GuiTeslaCell extends GuiBase
{
    TileTeslaStorage tile;

    public GuiTeslaCell(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerTeslaCell(tile, player), "crystek.teslacell");
        this.tile = (TileTeslaStorage) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        builder.drawSlot(this, guiLeft + 43, guiTop + 34);
        builder.drawSlot(this, guiLeft + 115, guiTop + 34);
    }
}
