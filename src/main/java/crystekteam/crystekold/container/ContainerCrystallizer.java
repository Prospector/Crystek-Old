package crystekteam.crystekold.container;

import crystekteam.crystekold.container.slot.SlotCharge;
import crystekteam.crystekold.container.slot.SlotOutput;
import crystekteam.crystekold.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ContainerCrystallizer extends ContainerBase
{
    public ContainerCrystallizer(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        addUpgradeSlots(tile, 2);
        this.addSlotToContainer(new Slot(tile.getInv(), 0, 61, 35));
        this.addSlotToContainer(new SlotOutput(tile.getInv(), 1, 121, 35));
        this.addSlotToContainer(new SlotCharge(tile.inv, 5, 8, 62));
    }
}
