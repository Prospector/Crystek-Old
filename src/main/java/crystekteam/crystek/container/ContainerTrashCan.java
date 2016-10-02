package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class ContainerTrashCan extends ContainerBase
{
    public ContainerTrashCan(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);

        this.addSlotToContainer(new Slot(tile.inv, 0, 80, 35));
    }

    @Override
    public void detectAndSendChanges()
    {
    }
}
