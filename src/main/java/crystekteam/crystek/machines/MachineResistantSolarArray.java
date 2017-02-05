package crystekteam.crystek.machines;

import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Prospector
 */
public class MachineResistantSolarArray extends MachineSolarArray {

	@Override
	public int invSize() {
		return 0;
	}

	@Override
	public int guiID() {
		return 8;
	}

	@Override
	public String getName() {
		return "resistant_solar_array";
	}

	@Override
	public int getTankSize() {
		return 16000;
	}

	@Nullable
	@Override
	public List<Slot> getSlots() {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new MachineResistantSolarArray();
	}
}
