package crystekteam.crystek.machines.old;

import crystekteam.crystek.container.slots.SlotTeslaCharge;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.guis.GuiCrystek;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
public class MachineCell extends Machine {
	@Override
	public int invSize() {
		return 2;
	}

	@Override
	public int guiID() {
		return 6;
	}

	@Override
	public String getName() {
		return "cell";
	}

	@Override
	public int getTankSize() {
		return 0;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		List<Slot> slots = new ArrayList<>();
		slots.add(new SlotTeslaCharge(getInv(), 0, 50, 35));
		slots.add(new SlotTeslaCharge(getInv(), 1, 105, 35));
		return slots;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new MachineCell();
	}

	@Override
	public long maxCapacity() {
		return 128000;
	}

	@Override
	public long maxInput() {
		return 4096;
	}

	@Override
	public long maxOutput() {
		return 4096;
	}

	@Override
	public EnumTeslaType teslaType() {
		return EnumTeslaType.STORER;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (facing == EnumFacing.UP && capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
			return true;
		}
		if (facing != EnumFacing.UP && capability == TeslaCapabilities.CAPABILITY_PRODUCER) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public void update() {
		if (teslaContainer.getEnergyStored() < 1) {
			updateState(false);
		} else {
			updateState(true);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY, GuiCrystek gui, int guiLeft, int guiTop, GuiCrystek.Layer layer) {
		builder.drawTeslaEnergyBar(gui, 9, 18, (int) getTeslaContainer().getStoredPower(), (int) getTeslaContainer().getCapacity(), energyGain, mouseX, mouseY, layer);
	}
}
