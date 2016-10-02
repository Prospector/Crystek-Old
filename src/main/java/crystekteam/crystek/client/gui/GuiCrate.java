package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerCrate;
import crystekteam.crystek.tiles.TileCrate;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class GuiCrate extends GuiBase
{
    public TileCrate table;

    public GuiCrate(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerCrate(tile, player), "crate");
        this.table = (TileCrate) tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                builder.drawSlot(this, guiLeft + 7 + j * 18, guiTop + 16 + i * 18);
            }
        }
    }
}
