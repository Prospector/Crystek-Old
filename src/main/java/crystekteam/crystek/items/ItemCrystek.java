package crystekteam.crystek.items;

import crystekteam.crystek.Crystek;
import net.minecraft.item.Item;

public class ItemCrystek extends Item {
	public ItemCrystek() {
		setCreativeTab(Crystek.instance.getTab());
	}
}
