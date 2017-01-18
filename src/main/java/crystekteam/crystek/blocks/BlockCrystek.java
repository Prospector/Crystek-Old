package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.machines.MachineFurnace;
import crystekteam.crystek.machines.MachineGenerator;
import crystekteam.crystek.machines.MachineTank;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class BlockCrystek extends BlockContainer {
    Machine machine;
	public BlockCrystek(Machine machine) {
		super(Material.IRON);
        this.machine = machine;
		setCreativeTab(Crystek.MOD_CL.getTab());
		setUnlocalizedName(machine.getName());
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
        return null;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
