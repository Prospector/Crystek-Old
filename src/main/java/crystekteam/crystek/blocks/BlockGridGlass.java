package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Prospector
 */
public class BlockGridGlass extends BlockGlass {
	public BlockGridGlass() {
		super(Material.GLASS, false);
		setUnlocalizedName(Crystek.PREFIX + "grid_glass");
		setRegistryName(Crystek.MOD_ID, "grid_glass");
		setCreativeTab(Crystek.MOD_CL.getTab());
		setHardness(0.3F);
		setSoundType(SoundType.GLASS);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
