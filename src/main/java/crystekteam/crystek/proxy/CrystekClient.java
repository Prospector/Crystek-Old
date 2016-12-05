package crystekteam.crystek.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by McKeever on 09-Nov-16.
 */
public class CrystekClient extends CrystekServer {
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
