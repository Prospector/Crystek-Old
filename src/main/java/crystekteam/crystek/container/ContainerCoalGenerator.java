package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;

public class ContainerCoalGenerator extends ContainerBase
{
    public ContainerCoalGenerator(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        this.addSlotToContainer(new SlotFurnaceFuel(tile.getInv(), 0, 80, 55));
        this.addSlotToContainer(new Slot(tile.getInv(), 1, 8, 62));
    }
}
