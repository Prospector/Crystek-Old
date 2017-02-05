package crystekteam.crystek.init;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.blocks.BlockGridGlass;
import crystekteam.crystek.blocks.BlockStainedGridGlass;
import crystekteam.crystek.blocks.FluidBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class CrystekBlocks {
	public static final Fluid BIOMASS_FLUID = new Fluid("biomass", new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/biomass_still"), new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/biomass_flowing"));
	public static final Fluid TESLARINE_FLUID = new Fluid("teslarine", new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/teslarine_still"), new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/teslarine_flowing"));
	public static final Fluid EXPERIENCE_FLUID = new Fluid("experience", new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/experience_still"), new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/experience_flowing"));
	public static final Fluid COOLANT_FLUID = new Fluid("coolant", new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/coolant_still"), new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/coolant_flowing"));
	public static final Fluid VENOM_FLUID = new Fluid("venom", new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/venom_still"), new ResourceLocation(Crystek.MOD_ID, "blocks/fluids/venom_flowing"));
	public static final BlockGridGlass GRID_GLASS = new BlockGridGlass();
	public static final BlockStainedGridGlass STAINED_GRID_GLASS = new BlockStainedGridGlass();
	public static FluidBlockBase BIOMASS_BLOCK;
	public static FluidBlockBase TESLARINE_BLOCK;
	public static FluidBlockBase EXPERIENCE_BLOCK;
	public static FluidBlockBase COOLANT_BLOCK;
	public static FluidBlockBase VENOM_BLOCK;

	public static void init() {
		FluidRegistry.registerFluid(TESLARINE_FLUID);
		TESLARINE_BLOCK = new FluidBlockBase(TESLARINE_FLUID, Material.LAVA);
		GameRegistry.register(TESLARINE_BLOCK);
		FluidRegistry.addBucketForFluid(TESLARINE_FLUID);

		FluidRegistry.registerFluid(BIOMASS_FLUID);
		BIOMASS_BLOCK = new FluidBlockBase(BIOMASS_FLUID, Material.WATER);
		GameRegistry.register(BIOMASS_BLOCK);
		FluidRegistry.addBucketForFluid(BIOMASS_FLUID);

		FluidRegistry.registerFluid(EXPERIENCE_FLUID);
		EXPERIENCE_BLOCK = new FluidBlockBase(EXPERIENCE_FLUID, Material.WATER);
		GameRegistry.register(EXPERIENCE_BLOCK);
		FluidRegistry.addBucketForFluid(EXPERIENCE_FLUID);

		FluidRegistry.registerFluid(COOLANT_FLUID);
		COOLANT_BLOCK = new FluidBlockBase(COOLANT_FLUID, Material.WATER);
		GameRegistry.register(COOLANT_BLOCK);
		FluidRegistry.addBucketForFluid(COOLANT_FLUID);

		FluidRegistry.registerFluid(VENOM_FLUID);
		VENOM_BLOCK = new FluidBlockBase(VENOM_FLUID, Material.WATER);
		GameRegistry.register(VENOM_BLOCK);
		FluidRegistry.addBucketForFluid(VENOM_FLUID);

		registerBlock(GRID_GLASS);
		registerColourfulBlock(STAINED_GRID_GLASS, STAINED_GRID_GLASS.getRegistryName().getResourcePath());
	}

	public static void registerBlock(Block block, String name) {
		block.setRegistryName(name);
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}

	public static void registerColourfulBlock(Block block, String name) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemCloth(block).setUnlocalizedName(Crystek.PREFIX + name), block.getRegistryName());
	}

	public static void registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), block.getRegistryName());
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name) {
		block.setRegistryName(name);
		GameRegistry.register(block);
		try {
			ItemBlock itemBlock = itemclass.getConstructor(Block.class).newInstance(block);
			itemBlock.setRegistryName(name);
			GameRegistry.register(itemBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
