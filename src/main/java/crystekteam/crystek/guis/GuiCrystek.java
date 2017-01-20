package crystekteam.crystek.guis;

import crystekteam.crystek.container.ContainerCrystek;
import crystekteam.crystek.core.Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class GuiCrystek extends GuiContainer {

	public Machine machine;
	public EntityPlayer player;

	public GuiCrystek(EntityPlayer player, Machine machine) {
		super(new ContainerCrystek(player, machine));
		this.machine = machine;
		this.player = player;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		machine.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, this, Layer.BACKGROUND);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		machine.drawGuiContainerForegroundLayer(mouseX, mouseY, this, guiLeft, guiTop, Layer.FOREGROUND);
	}

	protected void drawTitle() {
		drawCentredString(I18n.translateToLocal(machine.getBlockType().getUnlocalizedName() + ".name"), 6, 0xFFFFFF, Layer.FOREGROUND);
	}

	protected void drawCentredString(String string, int y, int colour, Layer layer) {
		drawString(string, (xSize / 2 - mc.fontRendererObj.getStringWidth(string) / 2), y, colour, layer);
	}

	protected void drawCentredString(String string, int y, int colour, int modifier, Layer layer) {
		drawString(string, (xSize / 2 - (mc.fontRendererObj.getStringWidth(string)) / 2) + modifier, y, colour, layer);
	}

	protected void drawString(String string, int x, int y, int colour, Layer layer) {
		int factorX = 0;
		int factorY = 0;
		if (layer == Layer.BACKGROUND) {
			factorX = guiLeft;
			factorY = guiTop;
		}
		mc.fontRendererObj.drawString(string, x + factorX, y + factorY, colour);
		GlStateManager.color(1, 1, 1, 1);
	}

	public enum Layer {
		BACKGROUND, FOREGROUND
	}
}
