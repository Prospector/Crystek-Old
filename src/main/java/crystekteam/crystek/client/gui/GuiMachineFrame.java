package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerMachineFrame;
import crystekteam.crystek.tiles.TileMachineFrame;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class GuiMachineFrame extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tab_items.png");

    public GuiMachineFrame(EntityPlayer player, TileMachineFrame tile)
    {
        super(new ContainerMachineFrame(tile, player));
        this.ySize = 136;
        this.xSize = 195;
    }

    @Override
    public void initGui()
    {
        super.initGui();
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String name = I18n.translateToLocal("tile.build.name");
        this.fontRendererObj.drawString(name, this.xSize / 2 - 10 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
