package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCharge;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ContainerBucketer extends ContainerBase
{
    public ContainerBucketer(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        addUpgradeSlots(tile, 3);
        this.addSlotToContainer(new Slot(tile.getInv(), 0, 44, 35));
        this.addSlotToContainer(new Slot(tile.getInv(), 1, 116, 35));
        this.addSlotToContainer(new SlotCharge(tile.inv, 2, 8, 62));
    }
}
