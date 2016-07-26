package crystekteam.crystek.blocks;

import crystekteam.crystek.lib.ModInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;

public class BlockLamp extends BlockBase
{
	public BlockLamp()
	{
		setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".lamp");
		setLightLevel(1F);
	}


	@Override
	public boolean isBlockNormalCube(IBlockState blockState) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
