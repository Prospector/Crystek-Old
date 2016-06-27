package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerCrystallizer;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileCrystallizer;
import crystekteam.crystek.tiles.prefab.TileBase;
import crystekteam.crystek.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiCrystallizer extends GuiBase
{
    private static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/crystallizer.png");
    TileCrystallizer tileCrystallizer;

    public GuiCrystallizer(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerCrystallizer(tile, player), "crystek.crystallizer", textureloc);
        this.tileCrystallizer = (TileCrystallizer) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(tileCrystallizer);
        drawProgressbar(tile, 72, 40);
        drawTank(tile);
    }
}
