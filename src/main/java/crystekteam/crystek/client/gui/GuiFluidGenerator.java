package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerFluidGenerator;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.generator.TileFluidGenerator;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class GuiFluidGenerator extends GuiBase
{
    TileFluidGenerator tile;
    public GuiFluidGenerator(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerFluidGenerator(tile, player), "crystek.fluidgenerator");
        this.tile = (TileFluidGenerator) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawTankOverlay(tile, 30, 8);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        builder.drawTank(this, tile.tank, 31, 9, zLevel, 14, 64, mouseX - guiLeft, mouseY - guiTop);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
