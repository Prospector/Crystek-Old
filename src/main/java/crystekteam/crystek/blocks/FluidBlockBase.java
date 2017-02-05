package crystekteam.crystek.blocks;

import crystekteam.crystek.Crystek;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * @author Prospector
 */
public class FluidBlockBase extends BlockFluidClassic {
	public FluidBlockBase(Fluid fluid, Material material) {
		super(fluid, material);
		setUnlocalizedName(Crystek.PREFIX + "fluid." + fluid.getName());
		setRegistryName(new ResourceLocation(Crystek.MOD_ID, "fluid" + fluid.getName()));
	}
}