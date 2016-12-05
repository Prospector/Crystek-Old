package crystekteam.crystekold.container;

import crystekteam.crystekold.container.slot.SlotCharge;
import crystekteam.crystekold.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerTeslaCell extends ContainerBase
{
    public ContainerTeslaCell(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        addUpgradeSlots(tile, 2);
        this.addSlotToContainer(new SlotCharge(tile.getInv(), 0, 44, 35));
        this.addSlotToContainer(new SlotCharge(tile.getInv(), 1, 116, 35));
    }
}
