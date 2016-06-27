package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ContainerFluidizer extends ContainerBase
{
    public ContainerFluidizer(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        this.addSlotToContainer(new Slot(tile.getInv(), 0, 56, 35));
//        this.addSlotToContainer(new Slot(tile.getInv(), 1, 122, 35));
        addUpgradeSlots(tile, 2);
    }
}
