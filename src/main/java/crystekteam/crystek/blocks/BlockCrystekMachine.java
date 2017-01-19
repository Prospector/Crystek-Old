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
public class BlockCrystekMachine extends BlockMachineBase
{
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
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.removeTileEntity(pos);
        super.breakBlock(worldIn, pos, state);
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
