package crystekteam.crystek.proxy;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.blocks.FluidBlockBase;
import crystekteam.crystek.init.CrystekBlocks;
import crystekteam.crystek.init.CrystekItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import reborncore.modcl.ItemMetadataCL;

/**
 * Created by Prospector
 */
public class CrystekClient extends CrystekServer {

	static void registerItemModel(Item i, int meta) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "inventory"));
	}

	static void registerMachineModel(Block b, int meta) {
		ResourceLocation loc = Item.getItemFromBlock(b).getRegistryName();
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), meta, new ModelResourceLocation(loc, "active=false,facing=north"));
	}

	static void registerItemModel(Item i, int meta, String variant) {
		registerItemModel(i, meta, variant, "type");
	}

	static void registerItemModel(Item i, int meta, String variant, String property) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, property + "=" + variant));
	}

	private static void register(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
			new ModelResourceLocation(Crystek.PREFIX + name, "inventory"));
	}

	static void registerItemModel(Block b, int meta) {
		registerItemModel(Item.getItemFromBlock(b), meta);
	}

	static void registerItemModel(Block b, int meta, String variant) {
		registerItemModel(b, meta, variant);
	}

	static void registerItemModel(Block b, int meta, String variant, String property) {
		registerItemModel(Item.getItemFromBlock(b), meta, variant, property);
	}

	private static void register(Item item, String name) {
		register(item, 0, name);
	}

	private static void register(Block block, int meta, String name) {
		register(Item.getItemFromBlock(block), meta, name);
	}

	private static void register(Block block, String name) {
		register(Item.getItemFromBlock(block), 0, name);
	}

	private static void registerBlockstate(Item i, int meta, String variant) {
		registerBlockstate(i, meta, variant, "");
	}

	private static void registerBlockstate(Item i, int meta, String variant, String dir) {
		ResourceLocation loc = new ResourceLocation(Crystek.MOD_ID, dir + i.getRegistryName().getResourcePath());
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}

	private static void registerBlockstate(Block i, int meta, String variant) {
		registerBlockstate(i, meta, variant, "");
	}

	private static void registerBlockstate(Block i, int meta, String variant, String dir) {
		registerBlockstate(Item.getItemFromBlock(i), meta, variant, dir);
	}

	private static void registerBlockstateMultiItem(Item item, String variantName, String path) {
		ResourceLocation loc = new ResourceLocation(Crystek.MOD_ID, path);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(loc, "type=" + variantName));
	}

	private static void registerFluid(FluidBlockBase fluid) {
		final ModelResourceLocation location = new ModelResourceLocation(Crystek.PREFIX + "fluids", fluid.getFluid().getName().toLowerCase());
		ModelLoader.setCustomStateMapper(fluid, new StateMapperBase() {
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) { return location; }
		});
	}

	@Override
	public void registerRenders() {
		for (Item item : Crystek.MOD_CL.itemModelsToRegister) {
			registerItemModel(item, 0);
		}

		for (Block block : Crystek.MOD_CL.blockModelsToRegister) {
			registerMachineModel(block, 0);
		}

		for (ItemMetadataCL item : Crystek.MOD_CL.customBlockStates.keySet()) {
			int i;
			for (i = 0; i < CrystekItems.MATERIALS.types.size(); ++i) {
				registerBlockstate(item, i, item.types.get(i), Crystek.MOD_CL.customBlockStates.get(item));
			}
		}
		registerFluid(CrystekBlocks.TESLARINE_BLOCK);
		registerFluid(CrystekBlocks.BIOMASS_BLOCK);
		registerFluid(CrystekBlocks.EXPERIENCE_BLOCK);
		registerFluid(CrystekBlocks.COOLANT_BLOCK);
		registerFluid(CrystekBlocks.VENOM_BLOCK);
		register(CrystekBlocks.GRID_GLASS, "grid_glass");
		for (int i = 0; i <= 15; i++) {
			registerItemModel(CrystekBlocks.STAINED_GRID_GLASS, 15 - i, EnumDyeColor.byDyeDamage(i).getName(), "color");
		}
	}

	@Override
	public void registerItemModel(String modid, Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(modid + ":" + id, "inventory"));
	}

	@Override
	public void registerCustomBlockStateLocation(Block block, final String resourceLocation, boolean item) {
		super.registerCustomBlockStateLocation(block, resourceLocation, item);
		ModelLoader.setCustomStateMapper(block, new DefaultStateMapper() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				String resourceDomain = Block.REGISTRY.getNameForObject(state.getBlock()).getResourceDomain();
				String propertyString = getPropertyString(state.getProperties());
				return new ModelResourceLocation(resourceDomain + ':' + resourceLocation, propertyString);
			}
		});
		if (item) {
			String resourceDomain = Block.REGISTRY.getNameForObject(block).getResourceDomain();
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(resourceDomain + ':' + resourceLocation, "inventory"));
		}
	}

	@Override
	public void registerCustomBlockStateLocation(Item item, String resourceLocation) {
		String resourceDomain = Item.REGISTRY.getNameForObject(item).getResourceDomain();
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(resourceDomain + ':' + resourceLocation, "inventory"));

	}
}
