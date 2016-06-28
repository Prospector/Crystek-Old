package crystekteam.crystek.client.gui;

import crystekteam.crystek.container.ContainerBase;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.tiles.prefab.TileBase;
import crystekteam.crystek.util.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

/**
 * Created by Gigabit101 on 01/06/2016.
 */
public class GuiBase extends GuiContainer
{
	private static final ResourceLocation overlays = new ResourceLocation(
			ModInfo.MOD_NAME.toLowerCase() + ":" + "textures/gui/guiSheet.png");
	public ResourceLocation location;
	public String name;
	public TileBase tile;
	public ContainerBase container;

	public GuiBase(EntityPlayer player, TileBase tile, ContainerBase container, String name, ResourceLocation texture)
	{
		super(container);
		this.container = container;
		this.location = texture;
		this.name = name;
		this.tile = tile;
	}

	@Override public void initGui()
	{
		super.initGui();
	}

	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		String name = I18n.translateToLocal("tile." + this.name + ".name");
		this.fontRendererObj
				.drawString(name, this.xSize / 2 + 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 16777215);
	}

	@Override protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		this.mc.getTextureManager().bindTexture(location);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	public void drawProgressbar(TileBase tile, int x, int y)
	{
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		if (this.tile != null)
		{
			int j = this.container.progress / 4;
			this.mc.getTextureManager().bindTexture(overlays);
			if (j > 0)
				this.drawTexturedModalRect(k + x, l + y, 16, 0, j + 1, 4);
		}
	}

	public void drawPowerBar(TileBase tile)
	{
		drawPowerBar(tile, 9, 44);
	}

	public void drawPowerBar(TileBase tile, int x, int y)
	{
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		if (this.tile != null)
		{
			int j = (int) this.container.power;
			this.mc.getTextureManager().bindTexture(overlays);
			if (j > 0)
				this.drawTexturedModalRect(k + x, l + y + 12 - j, 0, 48 - j, 14, j + 2);
		}
	}

	public void drawBurn(TileBase tile, int x, int y)
	{
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		if (tile != null)
		{
			int j = this.container.burnTime;
			this.mc.getTextureManager().bindTexture(overlays);

			if (j > 0)
				this.drawTexturedModalRect(k + x, l + y + 12 - j, 0, 62 - j, 14, j + 2);
		}
	}

	public void drawTank(TileBase tile, double x, double y, double zLevel, double width, double height)
	{
		RenderUtil.renderGuiTank(tile.tank, x, y, zLevel, width, height);
		drawTankOverlay(tile, (int) x - guiLeft - 1, (int) y - guiTop + 4);
	}

	public void drawTank(TileBase tile)
	{
		RenderUtil.renderGuiTank(tile.tank, guiLeft + 39, guiTop + 11, zLevel, 14, 64);
		drawTankOverlay(tile, 38, 15);
	}

	public void drawTankOverlay(TileBase tile, int x, int y)
	{
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.mc.getTextureManager().bindTexture(overlays);
		this.drawTexturedModalRect(k + x, l + y, 14, 10, 20, 64);
	}
}
