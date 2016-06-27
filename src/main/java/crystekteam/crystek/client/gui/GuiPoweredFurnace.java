package crystekteam.crystek.client.gui;

import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileFurnace;
import crystekteam.crystek.container.ContainerPoweredFurnace;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class GuiPoweredFurnace extends GuiBase
{
    private static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/furnace.png");
    TileFurnace tileFurnace;

    public GuiPoweredFurnace(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerPoweredFurnace(tile, player), "crystek.poweredfurnace", textureloc);
        this.tileFurnace = (TileFurnace) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(tileFurnace);
        drawProgressbar(tile, 72, 40);
    }
}
