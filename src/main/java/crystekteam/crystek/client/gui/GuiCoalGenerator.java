package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerCoalGenerator;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.generator.TileCoalGenerator;
import crystekteam.crystek.tiles.prefab.TileBase;
import crystekteam.crystek.util.tooltip.TooltipRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class GuiCoalGenerator extends GuiBase
{
    TileCoalGenerator tile;
    public GuiCoalGenerator(EntityPlayer player, TileBase tile)
    {
        super(player, tile, new ContainerCoalGenerator(tile, player), "crystek.coalgenerator");
        this.tile = (TileCoalGenerator) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        builder.drawSlot(this, guiLeft + 79, guiTop + 54);
    }
}
