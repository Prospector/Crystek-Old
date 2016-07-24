package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCharge;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 24/07/2016.
 */
public class ContainerTinkerTable extends ContainerBase
{
    public ContainerTinkerTable(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        //Charge slot
        this.addSlotToContainer(new SlotCharge(tile.inv, 0, 8, 62));

        //Tools slot
        this.addSlotToContainer(new Slot(tile.getInv(), 1, 80, 37));

        //Inv slots
        this.addSlotToContainer(new Slot(tile.getInv(), 2, 80, 8));
        this.addSlotToContainer(new Slot(tile.getInv(), 3, 80, 62));

        this.addSlotToContainer(new Slot(tile.getInv(), 4, 50, 62));
        this.addSlotToContainer(new Slot(tile.getInv(), 5, 110, 62));

        this.addSlotToContainer(new Slot(tile.getInv(), 6, 110, 37));
        this.addSlotToContainer(new Slot(tile.getInv(), 7, 50, 37));

        this.addSlotToContainer(new Slot(tile.getInv(), 8, 50, 8));
        this.addSlotToContainer(new Slot(tile.getInv(), 9, 110, 8));
    }
}
