package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerTeslaCell;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.prefab.TileBase;
import crystekteam.crystek.tiles.prefab.TileTeslaStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Travis on 24/06/2016.
 */
public class GuiTeslaCell extends GuiBase
{
//    private static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" +"textures/gui/teslacell.png");
    TileTeslaStorage tile;

    public GuiTeslaCell(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerTeslaCell(tile, player), "crystek.teslacell");//, textureloc);
        this.tile = (TileTeslaStorage) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(tile, 81, 54);
    }
}
