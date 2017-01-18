package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.machines.MachineCreativeTeslaCell;
import crystekteam.crystek.machines.MachineFurnace;
import crystekteam.crystek.machines.MachineGenerator;
import crystekteam.crystek.machines.MachineTank;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.blocks.BlockMachineBase;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class BlockCrystek extends BlockContainer
{
    Machine machine;
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockCrystek(Machine machine) {
		super(Material.ROCK);
        this.machine = machine;
		setCreativeTab(Crystek.MOD_CL.getTab());
		setUnlocalizedName(machine.getName());
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
        setHardness(2.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (worldIn.getTileEntity(pos) != null)
        {
            Machine tileMachine = (Machine) worldIn.getTileEntity(pos);
            if(!playerIn.isSneaking())
            {
                try
                {
                    tileMachine.openGui(playerIn, tileMachine);
                }
                catch (Exception e)
                {
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
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        this.setDefaultDirection(worldIn, pos, state);
    }

    private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
            boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

            if (enumfacing == EnumFacing.NORTH && flag && !flag1)
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag)
            {
                enumfacing = EnumFacing.NORTH;
            }
            else
            {
                boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
                boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

                if (enumfacing == EnumFacing.WEST && flag2 && !flag3)
                {
                    enumfacing = EnumFacing.EAST;
                }
                else if (enumfacing == EnumFacing.EAST && flag3 && !flag2)
                {
                    enumfacing = EnumFacing.WEST;
                }
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(ACTIVE, Boolean.valueOf(false)), 2);
        }
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(ACTIVE, Boolean.valueOf(false));
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, ACTIVE});
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(ACTIVE, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityDispenser)
            {
                ((TileEntityDispenser)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }


    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(ACTIVE)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        //TODO automate this
        if(machine instanceof MachineFurnace)
        {
            return new MachineFurnace();
        }
        if(machine instanceof MachineGenerator)
        {
            return new MachineGenerator();
        }
        if(machine instanceof MachineTank)
        {
            return new MachineTank();
        }
        if(machine instanceof MachineCreativeTeslaCell)
        {
            return new MachineCreativeTeslaCell();
        }
        return null;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
