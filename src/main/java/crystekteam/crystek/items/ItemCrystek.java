package crystekteam.crystek.items;

import crystekteam.crystek.Crystek;
import net.minecraft.item.Item;

/**
 * Created by Prospector
 */
public class ItemCrystek extends Item {
	public String name;

	public ItemCrystek(String name) {
		this.name = name;
		setUnlocalizedName(Crystek.PREFIX + name);
		setRegistryName(Crystek.MOD_ID, name);
		setCreativeTab(Crystek.instance.getTab());
	}
}
