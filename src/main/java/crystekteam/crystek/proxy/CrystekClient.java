package crystekteam.crystek.proxy;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.init.CrystekItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
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

	static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}

	private static void register(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
			new ModelResourceLocation(Crystek.PREFIX + name, "inventory"));
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

	@Override
	public void registerRenders() {
		//		int i;
		//		for (i = 0; i < CrystekItems.MATERIALS.types.size(); ++i) {
		//			registerItemModel(CrystekItems.MATERIALS, i, CrystekItems.MATERIALS.types.get(i));
		//		}

		for (Item item : Crystek.modcl.modelsToRegister) {
			registerItemModel(item, 0);
		}

		for (ItemMetadataCL item : Crystek.modcl.customBlockStates.keySet()) {
			int i;
			for (i = 0; i < CrystekItems.MATERIALS.types.size(); ++i) {
				registerBlockstate(item, i, item.types.get(i), Crystek.modcl.customBlockStates.get(item));
			}
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
