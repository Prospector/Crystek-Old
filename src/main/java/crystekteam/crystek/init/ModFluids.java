package crystekteam.crystek.init;

import crystekteam.crystek.blocks.fluids.FluidBlockBase;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Prospector on 25/06/16
 */
public class ModFluids
{
	public static final Fluid fluidTesla = new Fluid("fluidTesla",
			new ResourceLocation(ModInfo.MOD_ID.toLowerCase(), "blocks/fluids/fluidTesla_still"),
			new ResourceLocation(ModInfo.MOD_ID.toLowerCase(), "blocks/fluids/fluidTesla_flowing"));

	public static FluidBlockBase fluidTeslaBlock;

	public static void init()
	{
		FluidRegistry.registerFluid(fluidTesla);
		fluidTeslaBlock=new FluidBlockBase(fluidTesla, Material.LAVA);
		GameRegistry.register(fluidTeslaBlock);
		GameRegistry.register(new ItemBlock(fluidTeslaBlock).setRegistryName(fluidTeslaBlock.getRegistryName()));
		FluidRegistry.addBucketForFluid(fluidTesla);
	}
}
