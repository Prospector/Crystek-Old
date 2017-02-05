package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Prospector
 */
public class BlockStainedGridGlass extends BlockBreakable {

	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);

	public BlockStainedGridGlass() {
		super(Material.GLASS, false);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		setUnlocalizedName(Crystek.PREFIX + "stained_grid_glass");
		setRegistryName(Crystek.MOD_ID, "stained_grid_glass");
		setCreativeTab(Crystek.MOD_CL.getTab());
		setHardness(0.3F);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMetadata();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
			list.add(new ItemStack(itemIn, 1, enumdyecolor.getMetadata()));
		}
	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMapColor();
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			BlockBeacon.updateColorAsync(worldIn, pos);
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			BlockBeacon.updateColorAsync(worldIn, pos);
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { COLOR });
	}
}
