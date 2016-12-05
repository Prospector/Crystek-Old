package crystekteam.crystekold.blocks.fluids;

import crystekteam.crystekold.lib.ModInfo;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * @author Prospector on 16/06/16
 */
public class FluidBlockBase extends BlockFluidClassic
{
    public FluidBlockBase(Fluid fluid, Material material)
    {
        super(fluid, material);
        setUnlocalizedName(ModInfo.MOD_ID.toLowerCase() + ".fluid." + fluid.getName());
        setRegistryName(new ResourceLocation(ModInfo.MOD_ID.toLowerCase(), "fluid" + fluid.getName()));
    }
}
