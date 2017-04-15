package crystekteam.crystek.machines.old;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Prospector
 */
public abstract class BasicMachine extends Machine {
	private int inventorySize;
	private int guiID;
	private String name;
	private int tankSize;
	private int maxEnergy;
	private int maxEnergyInput;
	private int maxEnergyOutput;
	private EnumTeslaType teslaType;

	public BasicMachine(int inventorySize, int guiID, String name, int tankSize, int maxEnergy, int maxEnergyInput, int maxEnergyOutput, EnumTeslaType teslaType) {
		this.inventorySize = inventorySize;
		this.guiID = guiID;
		this.name = name;
		this.tankSize = tankSize;
		this.maxEnergy = maxEnergy;
		this.maxEnergyInput = maxEnergyInput;
		this.maxEnergyOutput = maxEnergyOutput;
		this.teslaType = teslaType;
	}

	public BasicMachine(int inventorySize, int guiID, String name, int maxEnergy, int maxEnergyInput, int maxEnergyOutput, EnumTeslaType teslaType) {
		this(inventorySize, guiID, name, 0, maxEnergy, maxEnergyInput, maxEnergyOutput, teslaType);
	}

	public BasicMachine(int inventorySize, int guiID, String name, List<Slot> slots) {
		this(inventorySize, guiID, name, 0, 0, 0, 0, EnumTeslaType.NULL);
	}

	public BasicMachine(String name) {
		this(0, -1, name, 0, 0, 0, 0, EnumTeslaType.NULL);
	}

	@Override
	public int invSize() {
		return inventorySize;
	}

	@Override
	public int guiID() {
		return guiID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getTankSize() {
		return tankSize;
	}

	@Override
	public long maxCapacity() {
		return maxEnergy;
	}

	@Override
	public long maxInput() {
		return maxEnergyInput;
	}

	@Override
	public long maxOutput() {
		return maxEnergyOutput;
	}

	@Override
	public EnumTeslaType teslaType() {
		return teslaType;
	}

	protected void drawForeground(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {

	}

	protected void drawBackground(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, GuiCrystek gui, GuiCrystek.Layer layer) {

	}

	@Override
	public final void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY, int guiLeft, int guiTop, int xSize, int ySize, GuiCrystek gui, GuiCrystek.Layer layer) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, gui, layer);
		drawBackground(partialTicks, mouseX, mouseY, guiLeft, guiTop, xSize, ySize, gui, layer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public final void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY, gui, guiLeft, guiTop, layer);
		if (centreTeslaBar()) {
			builder.drawTeslaEnergyBar(gui, 9, 18, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), energyGain, mouseX, mouseY, layer);
		} else {
			builder.drawTeslaEnergyBar(gui, 9, 18, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), energyGain, mouseX, mouseY, layer);
		}
		drawForeground(mouseX, mouseY, gui, guiLeft, guiTop, layer);
	}

	protected boolean centreTeslaBar() {
		return false;
	}
}
