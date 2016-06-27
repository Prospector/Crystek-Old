package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCharge;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerTeslaCell extends ContainerBase
{
    public ContainerTeslaCell(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        this.addSlotToContainer(new SlotCharge(tile.getInv(), 0, 44, 35));
        this.addSlotToContainer(new SlotCharge(tile.getInv(), 1, 116, 35));
    }
}
