package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerTinkerTable;
import crystekteam.crystek.container.ContainerTrashCan;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.TileTinkerTable;
import crystekteam.crystek.tiles.TileTrashCan;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class GuiTrashcan extends GuiBase
{
    private static final ResourceLocation textureloc = new ResourceLocation(ModInfo.MOD_NAME.toLowerCase() + ":" +"textures/gui/trashcan.png");

    public TileTrashCan table;
    public GuiTrashcan(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerTrashCan(tile, player), "crystek.trashcan", textureloc);
        this.table = (TileTrashCan) tile;
    }
}
