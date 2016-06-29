package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerBucketer;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileBucketer;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiBucketer extends GuiBase
{
    public static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/bucketer.png");
    TileBucketer tileCrystallizer;

    public GuiBucketer(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerBucketer(tile, player), "crystek.bucketer", textureloc);
        this.tileCrystallizer = (TileBucketer) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(tileCrystallizer);
        drawProgressbar(tile, 85, 40);
        drawTank(tile, guiLeft + 82, guiTop + 11, zLevel, 14, 64);
    }
}
