package crystekteam.crystek.machines;

import crystekteam.crystek.blocks.BlockCrystekMachine;
import crystekteam.crystek.core.EnumTeslaType;
import crystekteam.crystek.core.Machine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Prospector
 */
public class MachineSolarArray extends Machine {
	public int solarEnergy;
	public int maxSolarEnergy = 1;
	private boolean solarCapable = false;

	@Override
	public boolean openGuiOnRightClick() {
		return false;
	}

	@Override
	public boolean invertPlacing() {
		return true;
	}

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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new MachineSolarArray();
	}

	@Override
	public void update() {
		super.update();
		if (!world.isRemote) {
			IBlockState BlockStateContainer = world.getBlockState(pos);
			BlockCrystekMachine blockBase = (BlockCrystekMachine) BlockStateContainer.getBlock();
			EnumFacing dir = blockBase.getFacing(BlockStateContainer).getOpposite();
			BlockPos generatorPos = getPos().offset(dir);
			if (world.getTileEntity(generatorPos) != null && world.getTileEntity(generatorPos) instanceof MachineSolarGenerator) {
				if (world.getTotalWorldTime() % 100 == 0) {
					if (this.hasWorld() && !this.world.provider.hasNoSky() && this.world.isDaytime() && canBlockSeeSky(this.pos.up()) && !this.world.isRaining() && !world.isThundering() && this.world.getSkylightSubtracted() == 0) {
						solarCapable = true;
						for (int i = 1; i <= world.getHeight(); i++)
							if (world.getTileEntity(pos.offset(EnumFacing.UP, i)) != null && world.getTileEntity(pos.offset(EnumFacing.UP, i)) instanceof MachineSolarArray)
								solarCapable = false;

					} else
						solarCapable = false;
					updateState(solarCapable);
				}
				if (solarCapable) {
					if (solarEnergy < maxSolarEnergy)
						solarEnergy++;
					if (solarEnergy >= 1) {
						((MachineSolarGenerator) world.getTileEntity(generatorPos)).solarEnergy++;
						solarEnergy--;
					}
				}
			} else
				updateState(false);
		}
	}

	public boolean canBlockSeeSky(BlockPos pos) {
		if (pos.getY() >= world.getSeaLevel())
			return world.canSeeSky(pos);
		else {
			BlockPos blockpos = new BlockPos(pos.getX(), world.getSeaLevel(), pos.getZ());

			if (!world.canSeeSky(blockpos))
				return false;
			else {
				for (blockpos = blockpos.down(); blockpos.getY() > pos.getY(); blockpos = blockpos.down()) {
					IBlockState iblockstate = world.getBlockState(blockpos);
					if (world.getTileEntity(blockpos) != null && world.getTileEntity(blockpos) instanceof MachineSolarArray)
						return false;
					if (iblockstate.getBlock().getLightOpacity(iblockstate, world, blockpos) > 0 && !iblockstate.getMaterial().isLiquid())
						return false;
				}
				return true;
			}
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		if (side != EnumFacing.DOWN && side != EnumFacing.UP && worldIn.getTileEntity(pos.offset(side.getOpposite())) != null && worldIn.getTileEntity(pos.offset(side.getOpposite())) instanceof MachineSolarGenerator)
			return true;
		return false;
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
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		AxisAlignedBB box = new AxisAlignedBB(0.0625, 0.375, 0.0625, 0.875 + 0.0625, 0.6875, 0.875 + 0.0625);
		return box;
	}
}
