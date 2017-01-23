package crystekteam.crystek.machines;

import crystekteam.crystek.blocks.BlockCrystekMachine;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Prospector
 */
public class MachineSolarArray extends Machine {
	public int solarEnergy;
	public int maxSolarEnergy = 1;

	@Override
	public int invSize() {
		return 0;
	}

	@Override
	public int guiID() {
		return 7;
	}

	@Override
	public String getName() {
		return "solar_array";
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
		return EnumTeslaType.NULL;
	}

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new MachineSolarArray();
    }

	@Override
	public void update() {
		super.update();
		if (this.hasWorld() && !this.world.provider.hasNoSky() && this.world.provider.isDaytime() && this.world.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.world.isRaining() && this.world.getSkylightSubtracted() == 0) {
			if (solarEnergy < maxSolarEnergy)
				solarEnergy++;
			updateState(true);
			IBlockState BlockStateContainer = world.getBlockState(pos);
			BlockCrystekMachine blockBase = (BlockCrystekMachine) BlockStateContainer.getBlock();
			EnumFacing dir = blockBase.getFacing(BlockStateContainer).getOpposite();
			BlockPos generatorPos = getPos().offset(dir);
			if (world.getTileEntity(generatorPos) != null && world.getTileEntity(generatorPos) instanceof MachineSolarGenerator) {
				if (solarEnergy >= 1) {
					((MachineSolarGenerator) world.getTileEntity(generatorPos)).solarEnergy++;
					solarEnergy--;
				}
			}
		} else {
			updateState(false);
		}
	}

	@Override
	public NBTTagCompound writeToNBTWithoutCoords(NBTTagCompound compound) {
		super.writeToNBTWithoutCoords(compound);
		compound.setInteger("SolarEnergy", solarEnergy);
		return compound;
	}

	@Override
	public void readFromNBTWithoutCoords(NBTTagCompound compound) {
		super.readFromNBTWithoutCoords(compound);
		solarEnergy = compound.getInteger("SolarEnergy");
	}

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
