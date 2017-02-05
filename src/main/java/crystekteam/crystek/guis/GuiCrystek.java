package crystekteam.crystek.guis;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.container.ContainerCrystek;
import crystekteam.crystek.core.Machine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;

import java.io.IOException;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class GuiCrystek extends GuiContainer {

	public int xSize = 176;
	public int ySize = 181;
	public Machine machine;
	public EntityPlayer player;
	int CONFIG_BUTTON_ID = 999;

	public GuiCrystek(EntityPlayer player, Machine machine) {
		super(new ContainerCrystek(player, machine));
		this.machine = machine;
		this.player = player;
		buttonList.clear();
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		machine.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, this, Layer.BACKGROUND);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		drawTitle();
		machine.drawGuiContainerForegroundLayer(mouseX, mouseY, this, guiLeft, guiTop, Layer.FOREGROUND);
	}

	protected void drawTitle() {
		drawCentredString(I18n.translateToLocal(machine.getBlockType().getUnlocalizedName() + ".name"), 6, 0xFFFFFF, Layer.FOREGROUND);
	}

	public void drawCentredString(String string, int y, int colour, Layer layer) {
		drawString(string, (xSize / 2 - mc.fontRendererObj.getStringWidth(string) / 2), y, colour, layer);
	}

	public void drawCentredString(String string, int y, int colour, int modifier, Layer layer) {
		drawString(string, (xSize / 2 - (mc.fontRendererObj.getStringWidth(string)) / 2) + modifier, y, colour, layer);
	}

	public void drawString(String string, int x, int y, int colour, Layer layer) {
		int factorX = 0;
		int factorY = 0;
		if (layer == Layer.BACKGROUND) {
			factorX = guiLeft;
			factorY = guiTop;
		}
		mc.fontRendererObj.drawString(string, x + factorX, y + factorY, colour);
		GlStateManager.color(1, 1, 1, 1);
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.add(new GuiButton(CONFIG_BUTTON_ID, guiLeft + 150, guiTop + 4, 20, 20, "C"));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == CONFIG_BUTTON_ID) {
			player.openGui(Crystek.MOD_CL, 999, machine.getWorld(), machine.getPos().getX(), machine.getPos().getY(), machine.getPos().getZ());
		}
		super.actionPerformed(button);
	}

	public enum Layer {
		BACKGROUND, FOREGROUND
	}
}
