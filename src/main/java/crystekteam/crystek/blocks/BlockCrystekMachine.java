package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.machines.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import reborncore.common.blocks.BlockMachineBase;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class BlockCrystekMachine extends BlockMachineBase {
	Machine machine;

	public BlockCrystekMachine(Machine machine) {
		super(false);
		this.machine = machine;
		setCreativeTab(Crystek.MOD_CL.getTab());
		setUnlocalizedName(machine.getName());
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
		setHardness(2.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos) != null) {
			Machine tileMachine = (Machine) worldIn.getTileEntity(pos);
			if (!playerIn.isSneaking()) {
				try {
					tileMachine.openGui(playerIn, tileMachine);
				} catch (Exception e) {
					System.out.print(e);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.removeTileEntity(pos);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(this));
		return drops;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
		if (stack != ItemStack.EMPTY && stack.hasTagCompound()) {
			((Machine) world.getTileEntity(pos)).readFromNBTWithoutCoords(stack.getTagCompound().getCompoundTag("tileEntity"));
		}
		super.onBlockPlacedBy(world, pos, state, player, stack);
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if (machine instanceof MachineFurnace) {
			return new MachineFurnace();
		}
		if (machine instanceof MachineGenerator) {
			return new MachineGenerator();
		}
		if (machine instanceof MachineTank) {
			return new MachineTank();
		}
		if (machine instanceof MachineCreativeTeslaCell) {
			return new MachineCreativeTeslaCell();
		}
		if (machine instanceof MachineGrinder) {
			return new MachineGrinder();
		}
		if (machine instanceof MachineSolarGenerator) {
			return new MachineSolarGenerator();
		}
		if (machine instanceof MachineCell) {
			return new MachineCell();
		}
		if (machine instanceof MachineSolarArray) {
			return new MachineSolarArray();
		}
		return null;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
