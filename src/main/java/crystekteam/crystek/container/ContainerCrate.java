package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class ContainerCrate extends ContainerBase
{
    public ContainerCrate(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();

        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(tile.inv, j + i * 9, 8 + j * 18, 15 + i * 18));
            }
        }
    }
}
