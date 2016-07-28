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
        this.addSlotToContainer(new Slot(tile.getInv(), 1, 88, 35));

        //Inv slots
        this.addSlotToContainer(new Slot(tile.getInv(), 2, 88, 9));
        this.addSlotToContainer(new Slot(tile.getInv(), 3, 88, 61));

        this.addSlotToContainer(new Slot(tile.getInv(), 4, 64, 59));
        this.addSlotToContainer(new Slot(tile.getInv(), 5, 112, 59));

        this.addSlotToContainer(new Slot(tile.getInv(), 6, 114, 35));
        this.addSlotToContainer(new Slot(tile.getInv(), 7, 62, 35));

        this.addSlotToContainer(new Slot(tile.getInv(), 8, 64, 11));
        this.addSlotToContainer(new Slot(tile.getInv(), 9, 112, 11));
    }
}
