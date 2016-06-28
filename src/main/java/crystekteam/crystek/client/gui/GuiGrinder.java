package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerGrinder;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.machines.TileGrinder;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Travis on 16/06/2016.
 */
public class GuiGrinder extends GuiBase
{
    private static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" +"textures/gui/grinder.png");
    TileGrinder tile;

    public GuiGrinder(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerGrinder(tile, player), "crystek.oresmasher", textureloc);
        this.tile = (TileGrinder) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        drawPowerBar(tile);
        drawProgressbar(tile, 72, 40);
    }
}
