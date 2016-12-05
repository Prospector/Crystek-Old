package crystekteam.crystekold.client.gui.machine;

import crystekteam.crystekold.client.gui.GuiBase;
import crystekteam.crystekold.container.ContainerGrinder;
import crystekteam.crystekold.tiles.machines.TileGrinder;
import crystekteam.crystekold.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Travis on 16/06/2016.
 */
public class GuiGrinder extends GuiBase
{
    TileGrinder tile;

    public GuiGrinder(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerGrinder(tile, player), "crystek.grinder");
        this.tile = (TileGrinder) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        builder.drawSlot(this, guiLeft + 47, guiTop + 34);
        builder.drawSlot(this, guiLeft + 107, guiTop + 34);
        builder.drawSlot(this, guiLeft + 75, guiTop + 47);

        builder.drawProgressBar(this, container.progress, guiLeft + 76, guiTop + 34);
    }
}
