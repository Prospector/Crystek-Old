package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCharge;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotFurnaceFuel;

public class ContainerCoalGenerator extends ContainerBase
{
    public ContainerCoalGenerator(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        this.addSlotToContainer(new SlotFurnaceFuel(tile.getInv(), 0, 80, 55));
        this.addSlotToContainer(new SlotCharge(tile.getInv(), 1, 8, 62));
    }
}
