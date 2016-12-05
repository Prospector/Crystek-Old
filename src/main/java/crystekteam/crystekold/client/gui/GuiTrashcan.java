package crystekteam.crystekold.client.gui;

import crystekteam.crystekold.container.ContainerTrashCan;
import crystekteam.crystekold.tiles.TileTrashCan;
import crystekteam.crystekold.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class GuiTrashcan extends GuiBase
{
    public TileTrashCan table;

    public GuiTrashcan(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerTrashCan(tile, player), "crystek.trashcan");
        this.table = (TileTrashCan) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, mouseX, mouseY);
        builder.drawSlot(this, guiLeft + 79, guiTop + 34);
    }
}
