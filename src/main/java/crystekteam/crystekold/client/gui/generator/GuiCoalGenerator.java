package crystekteam.crystekold.client.gui.generator;

import crystekteam.crystekold.client.gui.GuiBase;
import crystekteam.crystekold.container.ContainerCoalGenerator;
import crystekteam.crystekold.tiles.generator.TileCoalGenerator;
import crystekteam.crystekold.tiles.prefab.TileBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class GuiCoalGenerator extends GuiBase {
    TileCoalGenerator tile;

    public GuiCoalGenerator(EntityPlayer player, TileBase tile) {
        super(player, tile, new ContainerCoalGenerator(tile, player), "crystek.coalgenerator");
        this.tile = (TileCoalGenerator) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);
        builder.drawSlot(this, guiLeft + 79, guiTop + 38);
            builder.drawBurnBar(this, 10, guiLeft + 81, guiTop + 24);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }
}
