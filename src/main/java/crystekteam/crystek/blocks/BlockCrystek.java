package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.init.MachinesInit;
import crystekteam.crystek.machines.MachineFurnace;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class BlockCrystek extends BlockContainer {
//	public PropertyInteger METADATA;
    static Machine machine;

	public BlockCrystek(Machine machine) {
		super(Material.IRON);
		setCreativeTab(Crystek.MOD_CL.getTab());
		setUnlocalizedName(machine.getName());
        this.machine = machine;
//		this.setDefaultState(this.getDefaultState().withProperty(METADATA, 0));
        setHardness(2.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (worldIn.getTileEntity(pos) != null)
        {
            TileMachine tileMachine = (TileMachine) worldIn.getTileEntity(pos);
            Machine machine = tileMachine.getMachine(tileMachine);
            if(!playerIn.isSneaking())
            {
                try
                {
                    machine.openGui(playerIn, tileMachine);
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

    public static Machine getMachine()
    {
        return machine;
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
//        Machine machine = MachinesInit.getMachineList().get(meta);
        return new TileMachine(new MachineFurnace());
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
