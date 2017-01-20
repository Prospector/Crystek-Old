package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 14/01/2017.
 */
public class MachineGenerator extends Machine {
	public static int getItemBurnTime(ItemStack stack) {
		return TileEntityFurnace.getItemBurnTime(stack) / 4;
	}

	@Override
	public int invSize() {
		return 1;
	}

	@Override
	public int guiID() {
		return 1;
	}

	@Override
	public String getName() {
		return "coalgenerator";
	}

	@Override
	public int getTankSize() {
		return 0;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		List<Slot> slots = new ArrayList<Slot>();
		slots.add(new SlotItemHandler(getInv(), 0, 80, 50));
		return slots;
	}

	@Override
	public long maxCapacity() {
		return 100000;
	}

	@Override
	public long maxInput() {
		return 100;
	}

	@Override
	public long maxOutput() {
		return 100;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.GENERATOR;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		builder.drawProgressBar(gui, 0, 75, 35);
		builder.drawTeslaEnergyBar(gui, 9, 6, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), mouseX, mouseY, layer);
	}

	@Override
	public void update() {
		if (getInv().getStackInSlot(0) != ItemStack.EMPTY) {
			getTeslaContainer().givePower(maxInput(), false);
		}
	}
}
