package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerTinkerTable;
import crystekteam.crystek.tiles.TileTinkerTable;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class GuiTinkerTable extends GuiBase
{
    public TileTinkerTable table;
    public GuiTinkerTable(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerTinkerTable(tile, player), "tinkertable");
        this.table = (TileTinkerTable) tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
    }
}
