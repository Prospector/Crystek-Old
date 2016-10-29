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
        drawPlayersInvAndHotbar(player);
        this.addSlotToContainer(new SlotFurnaceFuel(tile.getInv(), 0, 80, 39));
    }
}
