package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerCrystallizer;
import crystekteam.crystek.container.ContainerFluidizer;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileCrystallizer;
import crystekteam.crystek.tiles.machines.TileFluidizer;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiFluidizer extends GuiBase
{
    private static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/fluidizer.png");
    TileFluidizer tileCrystallizer;

    public GuiFluidizer(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerFluidizer(tile, player), "crystek.fluidizer", textureloc);
        this.tileCrystallizer = (TileFluidizer) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(tileCrystallizer);
        drawProgressbar(tile, 56, 40);
        drawTank(tile, guiLeft + 102, guiTop + 11, zLevel, 14,64);
    }
}
