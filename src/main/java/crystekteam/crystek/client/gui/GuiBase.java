package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerBase;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.prefab.TileBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import reborncore.client.guibuilder.GuiBuilder;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class GuiBase extends GuiContainer {
    public String name;
    public TileBase tile;
    public ContainerBase container;
    public CrystekBuilder builder = new CrystekBuilder();

    public GuiBase(EntityPlayer player, TileBase tile, ContainerBase container, String name) {
        super(container);
        this.container = container;
        this.name = name;
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 168;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
        builder.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        builder.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 84, true);
        builder.drawChargeSlot(this, guiLeft + 7, guiTop + 57);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        builder.drawExperienceBar(this, 7, 77, 100, mouseX - guiLeft, mouseY - guiTop);
        builder.drawTeslaEnergyBar(this, 9, 5, (int) container.power, (int) tile.getMaxCapacity(), mouseX - guiLeft, mouseY - guiTop);
        //builder.drawEnergyBar(this, 5, 5, 50, (int) container.power, (int) tile.getMaxCapacity(), mouseX - guiLeft, mouseY - guiTop, "Tesla");
    }


    public void drawTankOverlay(TileBase tile, int x, int y) {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.mc.getTextureManager().bindTexture(CrystekBuilder.resourceLocation);
        this.drawTexturedModalRect(k + x, l + y, 26, 150, 16, 66);
    }
}
