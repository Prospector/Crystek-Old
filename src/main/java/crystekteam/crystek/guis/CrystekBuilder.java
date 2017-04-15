package crystekteam.crystek.guis;

import crystekteam.crystek.Crystek;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Loader;
import reborncore.client.guibuilder.GuiBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class CrystekBuilder extends GuiBuilder {
	public static final ResourceLocation GUI_SHEET = new ResourceLocation(Crystek.PREFIX + "textures/gui/gui_sheet.png");

	public CrystekBuilder() {
		super(GUI_SHEET);
	}

	public void drawTankForground(GuiScreen gui, FluidTank tank, int x, int y, float zLevel, int width, int height, int mouseX, int mouseY, int guiLeft, int guiTop) {
		drawTank(gui, tank, x, y, zLevel, width, height, mouseX - guiLeft, mouseY - guiTop);
	}

	public void drawTeslaEnergyBar(GuiCrystek gui, int x, int y, int energyStored, int maxEnergyStored, int energyGain, int mouseX, int mouseY, GuiCrystek.Layer layer) {
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == GuiCrystek.Layer.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);

		gui.drawTexturedModalRect(x, y, 0, 150, 14, 50);

		int draw = (int) ((double) energyStored / (double) maxEnergyStored * (48));
		if (energyStored > maxEnergyStored) {
			draw = (int) ((double) maxEnergyStored / (double) maxEnergyStored * (48));
		}
		gui.drawTexturedModalRect(x + 1, y + 49 - draw, 15, 48 + 151 - draw, 12, draw);
		int percentage = percentage(maxEnergyStored, energyStored);
		if (isInRect(x + 1, y + 1, 11, 48, mouseX, mouseY)) {
			List<String> list = new ArrayList<>();
			list.add(TextFormatting.GOLD + "" + energyStored + "/" + maxEnergyStored + " Tesla");
			list.add(getPercentageColour(percentage) + "" + percentage + "%" + TextFormatting.GRAY + " Charged");
			if (energyGain != 0) {
				if (energyGain < 0)
					list.add(TextFormatting.RED + "" + energyGain + " Tesla/t");
				else
					list.add(TextFormatting.GREEN + "+" + energyGain + " Tesla/t");
			}
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawProgressBar(GuiCrystek gui, int progress, int maxProgress, int x, int y, int mouseX, int mouseY, GuiCrystek.Layer layer) {
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == GuiCrystek.Layer.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}

		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 150, 112, 26, 5);

		int j = (int) ((double) progress / (double) maxProgress * 24);
		if (j < 0)
			j = 0;
		gui.drawTexturedModalRect(x + 1, y + 1, 150, 117, j, 3);

		if (isInRect(x, y, 26, 5, mouseX, mouseY)) {
			int percentage = percentage(maxProgress, progress);
			List<String> list = new ArrayList<>();
			list.add(getPercentageColour(percentage) + "" + percentage + "%");
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawDefaultBackground(GuiScreen gui, int x, int y, int width, int height) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 0, 0, width / 2, height / 2);
		gui.drawTexturedModalRect(x + width / 2, y, 150 - width / 2, 0, width / 2, height / 2);
		gui.drawTexturedModalRect(x, y + height / 2, 0, 150 - height / 2, width / 2, height / 2);
		gui.drawTexturedModalRect(x + width / 2, y + height / 2, 150 - width / 2, 150 - height / 2, width / 2, height / 2);
	}

	public void drawTank(GuiCrystek gui, int x, int y, int mouseX, int mouseY, FluidStack fluid, int maxCapacity, boolean isTankEmpty, GuiCrystek.Layer layer) {
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == GuiCrystek.Layer.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}
		int percentage = 0;
		int amount = 0;
		boolean empty = true;
		if (!isTankEmpty) {
			amount = fluid.amount;
			percentage = percentage(maxCapacity, amount);
			empty = false;
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 228, 18, 22, 56);
		if (!empty)
			drawFluid(gui, fluid, x + 4, y + 4, 14, 48, maxCapacity);
		gui.drawTexturedModalRect(x + 3, y + 3, 231, 74, 16, 50);

		if (isInRect(x, y, 22, 56, mouseX, mouseY)) {
			List<String> list = new ArrayList<>();
			if (empty)
				list.add(TextFormatting.GOLD + "Empty Tank");
			else
				list.add(TextFormatting.GOLD + "" + amount + "mB/" + maxCapacity + "mB " + fluid.getLocalizedName());
			list.add(getPercentageColour(percentage) + "" + percentage + "%" + TextFormatting.GRAY + " Full");
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawFluid(GuiCrystek gui, FluidStack fluid, int x, int y, int width, int height, int maxCapacity) {
		gui.mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		y += height;
		final ResourceLocation still = fluid.getFluid().getStill(fluid);
		final TextureAtlasSprite sprite = gui.mc.getTextureMapBlocks().getAtlasSprite(still.toString());

		final int drawHeight = (int) (fluid.amount / (maxCapacity * 1F) * height);
		final int iconHeight = sprite.getIconHeight();
		int offsetHeight = drawHeight;

		int iteration = 0;
		while (offsetHeight != 0) {
			final int curHeight = offsetHeight < iconHeight ? offsetHeight : iconHeight;
			gui.drawTexturedModalRect(x, y - offsetHeight, sprite, width, curHeight);
			offsetHeight -= curHeight;
			iteration++;
			if (iteration > 50)
				break;
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
	}

	public void drawJEIButton(GuiCrystek gui, int x, int y, GuiCrystek.Layer layer) {
		if (Loader.isModLoaded("jei")) {
			if (layer == GuiCrystek.Layer.BACKGROUND) {
				x += gui.getGuiLeft();
				y += gui.getGuiTop();
			}
			gui.mc.getTextureManager().bindTexture(GUI_SHEET);
			gui.drawTexturedModalRect(x, y, 184, 70, 20, 12);
		}
	}

	public void drawBigBlueBar(GuiCrystek gui, int x, int y, int value, int max, int mouseX, int mouseY, String suffix, GuiCrystek.Layer layer) {
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		if (!suffix.equals("")) {
			suffix = " " + suffix;
		}
		gui.drawTexturedModalRect(x, y, 0, 218, 114, 18);
		int j = (int) ((double) value / (double) max * 106);
		if (j < 0)
			j = 0;
		gui.drawTexturedModalRect(x + 4, y + 4, 0, 236, j, 10);
		gui.drawCentredString(value + suffix, y + 5, 0xFFFFFF, layer);
		if (isInRect(x, y, 114, 18, mouseX, mouseY)) {
			int percentage = percentage(max, value);
			List<String> list = new ArrayList<>();
			list.add("" + TextFormatting.GOLD + value + "/" + max + suffix);
			list.add(getPercentageColour(percentage) + "" + percentage + "%" + TextFormatting.GRAY + " Full");

			if (value > max) {
				list.add(TextFormatting.GRAY + "Yo this is storing more than it should be able to");
				list.add(TextFormatting.GRAY + "prolly a bug");
				list.add(TextFormatting.GRAY + "pls report and tell how tf you did this");
			}
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawBigHeatBar(GuiCrystek gui, int x, int y, int value, int max, GuiCrystek.Layer layer) {
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 0, 218, 114, 18);
		if (value != 0) {
			int j = (int) ((double) value / (double) max * 106);
			if (j < 0)
				j = 0;
			gui.drawTexturedModalRect(x + 4, y + 4, 0, 246, j, 10);
			gui.drawCentredString(value + " Heat", y + 5, 0xFFFFFF, layer);

		}
	}

	public void drawMultiblockMissingBar(GuiCrystek gui, GuiCrystek.Layer layer) {
		int x = 0;
		int y = 4;
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		GlStateManager.colorMask(true, true, true, false);
		GuiUtils.drawGradientRect(0, x, y, x + 176, y + 20, 0x000000, 0xC0000000);
		GuiUtils.drawGradientRect(0, x, y + 20, x + 176, y + 20 + 48, 0xC0000000, 0xC0000000);
		GuiUtils.drawGradientRect(0, x, y + 68, x + 176, y + 70 + 20, 0xC0000000, 0x00000000);
		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableDepth();
		gui.drawCentredString(I18n.translateToLocal("techreborn.message.missingmultiblock"), 43, 0xFFFFFF, layer);
	}

	public void drawBigBlueBar(GuiCrystek gui, int x, int y, int value, int max, int mouseX, int mouseY, GuiCrystek.Layer layer) {
		drawBigBlueBar(gui, x, y, value, max, mouseX, mouseY, "", layer);
	}

	public void drawSelectedStack(GuiCrystek gui, int x, int y) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x - 4, y - 4, 202, 44, 24, 24);
	}

	public void drawBurnBar(GuiCrystek gui, int progress, int maxProgress, int x, int y, int mouseX, int mouseY, GuiCrystek.Layer layer) {
		if (layer == GuiCrystek.Layer.BACKGROUND) {
			x += gui.getGuiLeft();
			y += gui.getGuiTop();
		}
		if (layer == GuiCrystek.Layer.FOREGROUND) {
			mouseX -= gui.getGuiLeft();
			mouseY -= gui.getGuiTop();
		}

		gui.mc.getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(x, y, 171, 84, 13, 13);
		int j = 13 - (int) ((double) progress / (double) maxProgress * 13);
		if (j > 0) {
			gui.drawTexturedModalRect(x, y + j, 171, 70 + j, 13, 13 - j);

		}
		if (isInRect(x, y, 12, 12, mouseX, mouseY)) {
			int percentage = percentage(maxProgress, progress);
			List<String> list = new ArrayList<>();
			list.add(getPercentageColour(percentage) + "" + percentage + "%");
			GuiUtils.drawHoveringText(list, mouseX, mouseY, gui.width, gui.height, -1, gui.mc.fontRendererObj);
			GlStateManager.disableLighting();
			GlStateManager.color(1, 1, 1, 1);
		}
	}

	public void drawSlot(GuiScreen gui, int posX, int posY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(posX, posY, 150, 0, 18, 18);
	}

	public void drawSlot(GuiScreen gui, int posX, int posY, SlotSprite sprite) {
		drawSlot(gui, posX, posY);
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		sprite.draw(gui, posX, posY);
	}

	public void drawOutputSlot(GuiScreen gui, int posX, int posY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUI_SHEET);
		gui.drawTexturedModalRect(posX, posY, 150, 18, 26, 26);
	}

	public int getScaledBurnTime(int scale, int burnTime, int totalBurnTime) {
		return (int) (((float) burnTime / (float) totalBurnTime) * scale);
	}

	public TextFormatting getPercentageColour(int percentage) {
		if (percentage <= 10) {
			return TextFormatting.RED;
		} else if (percentage >= 75) {
			return TextFormatting.GREEN;
		} else {
			return TextFormatting.YELLOW;
		}
	}

	public int percentage(int MaxValue, int CurrentValue) {
		if (CurrentValue == 0)
			return 0;
		return (int) ((CurrentValue * 100.0f) / MaxValue);
	}

	public enum SlotSprite {
		BATTERY(182, 1), GRINDING_BLADE(199, 1);

		public int x;
		public int y;
		public int width;
		public int height;
		public int posX;
		public int posY;

		SlotSprite(int x, int y) {
			this(x, y, 16, 16, 0, 0);
		}

		SlotSprite(int x, int y, int width, int height, int posX, int posY) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.posX = posX;
			this.posY = posY;
		}

		public void draw(GuiScreen gui, int x, int y) {
			gui.drawTexturedModalRect(posX + x + 1, posY + y + 1, this.x, this.x, width, height);
		}
	}
}
