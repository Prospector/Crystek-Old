package crystekteam.crystekold.container;

import crystekteam.crystekold.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerFluidGenerator extends ContainerBase
{
    public ContainerFluidGenerator(TileBase tile, EntityPlayer player)
    {
        super(tile, player);
        drawPlayersInvAndHotbar(player);
        addUpgradeSlots(tile, 0);
    }
}
