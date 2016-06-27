package crystekteam.crystek.container;

import crystekteam.crystek.tiles.TileMachineFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ContainerMachineFrame extends Container
{
    public TileMachineFrame tile;
    public EntityPlayer player;

    public ContainerMachineFrame(TileMachineFrame tile, EntityPlayer player)
    {
        super();
        this.tile = tile;
        this.player = player;

        int i;
        for (i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(tile.inv, j + i * 9, 9 + j * 18, 18 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 9 + i * 18, 112));
        }
    }

    @Nullable
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
    {
        if(tile.getStackInSlot(slotId) != null)
        {
            this.tile.setTargetSlot(tile.getStackInSlot(slotId));
        }
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
