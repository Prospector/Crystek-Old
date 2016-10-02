package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerSolarPanel extends ContainerBase
{
    public ContainerSolarPanel(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        addUpgradeSlots(tile, 0);
//        this.addSlotToContainer(new SlotCircuit(tile.getInv(), 0, 152, 15));
//        this.addSlotToContainer(new SlotCircuit(tile.getInv(), 1, 152, 35));
//        this.addSlotToContainer(new SlotCircuit(tile.getInv(), 2, 152, 55));
    }
}
