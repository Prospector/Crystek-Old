package crystekteam.crystek.machines;

import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.minecraft.inventory.Slot;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Prospector
 */
public class TheMachine extends Machine {
	@Override
	public int invSize() {
		return 0;
	}

	@Override
	public int guiID() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getTankSize() {
		return 0;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		return null;
	}

	@Override
	public long maxCapacity() {
		return 0;
	}

	@Override
	public long maxInput() {
		return 0;
	}

	@Override
	public long maxOutput() {
		return 0;
	}

	@Override
	public EnumTeslaType teslaType() {
		return null;
	}
}
