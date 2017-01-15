package crystekteam.crystek.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by Prospector
 */
public class CrystekServer {
	public void registerRenders(){

	}

    public void registerItemModel(String modid, Item item, int meta, String id) {

    }

    public void registerCustomBlockStateLocation(Block block, String name) {
        registerCustomBlockStateLocation(block, name, true);
    }

    public void registerCustomBlockStateLocation(Block block, String name, boolean item) {

    }

    public void registerCustomBlockStateLocation(Item item, String name) {

    }
}
