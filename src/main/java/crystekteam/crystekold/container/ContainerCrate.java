package crystekteam.crystekold.container;

import crystekteam.crystekold.tiles.prefab.TileBase;
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
        drawPlayersInvAndHotbar(player);

        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(tile.getInv(), j + i * 9, 8 + j * 18, 17 + i * 18));
            }
        }
    }
}
