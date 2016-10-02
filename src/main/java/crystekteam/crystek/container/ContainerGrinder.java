package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotGrindingBlade;
import crystekteam.crystek.container.slot.SlotOutput;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 16/06/2016.
 */
public class ContainerGrinder extends ContainerBase
{
    public ContainerGrinder(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        this.addSlotToContainer(new Slot(tile.getInv(), 0, 48, 35));
        this.addSlotToContainer(new SlotOutput(tile.getInv(), 1, 108, 35));
        this.addSlotToContainer(new SlotGrindingBlade(tile.getInv(), 2, 76, 48));
    }
}
