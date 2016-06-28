package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerFluidGenerator extends ContainerBase
{
    public ContainerFluidGenerator(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        addUpgradeSlots(tile, 0);
    }
}
