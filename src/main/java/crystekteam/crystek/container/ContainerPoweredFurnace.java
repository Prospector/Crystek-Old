package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCharge;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ContainerPoweredFurnace extends ContainerBase
{
    public ContainerPoweredFurnace(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        addUpgradeSlots(tile, 2);
        this.addSlotToContainer(new Slot(tile.getInv(), 0, 48, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(player, tile.getInv(), 1, 108, 35));
        this.addSlotToContainer(new SlotCharge(tile.inv, 5, 8, 62));
    }
}
