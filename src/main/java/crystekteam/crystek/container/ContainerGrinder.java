package crystekteam.crystek.container;

import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

/**
 * Created by Gigabit101 on 16/06/2016.
 */
public class ContainerGrinder extends ContainerBase
{
    public ContainerGrinder(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        addPlayersHotbar();
        addPlayersInventory();
        this.addSlotToContainer(new Slot(tile.getInv(), 0, 48, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(player, tile.getInv(), 1, 108, 35));
    }
}
