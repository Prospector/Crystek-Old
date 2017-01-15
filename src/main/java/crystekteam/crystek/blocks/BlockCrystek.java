package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.core.Machine;
import crystekteam.crystek.init.MachinesInit;
import crystekteam.crystek.tiles.TileCrystek;
import crystekteam.crystek.tiles.TileMachine;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class BlockCrystek extends BlockDirectional {
	public PropertyInteger METADATA;

	public BlockCrystek() {
		super(Material.IRON);
		setCreativeTab(Crystek.modcl.getTab());
		setUnlocalizedName("machine");
		this.setDefaultState(this.getDefaultState().withProperty(METADATA, 0));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getTileEntity(pos) != null && !worldIn.isRemote)
        {
            TileMachine tileMachine = (TileMachine) worldIn.getTileEntity(pos);
            Machine machine = tileMachine.getMachine();
            if(playerIn.isSneaking())
            {
                playerIn.sendMessage(new TextComponentString("" + machine.getName()));
				return true;
            }
            else
            {
                playerIn.openGui(Crystek.modcl, machine.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
				return true;
            }
		}
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if (meta > MachinesInit.getMachineList().size()) {
			meta = 0;
		}
		return this.getDefaultState().withProperty(METADATA, meta);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int meta = 0; meta < MachinesInit.getMachineList().size(); meta++) {
			list.add(new ItemStack(item, 1, meta));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(METADATA);
	}

	protected BlockStateContainer createBlockState() {
		METADATA = PropertyInteger.create("type", 0, MachinesInit.getMachineList().size());
		return new BlockStateContainer(this, METADATA);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		if (MachinesInit.getMachineList().get(getMetaFromState(state)).getTileEntity() != null) {
			return MachinesInit.getMachineList().get(getMetaFromState(state)).getTileEntity();
		}
		return super.createTileEntity(world, state);
	}
}
