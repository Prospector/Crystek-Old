package crystekteam.crystek.container;

import crystekteam.crystek.container.slot.SlotCharge;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 02/06/2016.
 */
public class ContainerFluidizer extends ContainerBase
{
    public ContainerFluidizer(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        addUpgradeSlots(tile, 0);
        this.addSlotToContainer(new SlotCharge(tile.inv, 3, 8, 62));
    }
}
