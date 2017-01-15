package crystekteam.crystek.items;

import crystekteam.crystek.Crystek;
import crystekteam.crystek.init.CrystekItems;
import net.minecraft.item.ItemStack;
import reborncore.modcl.ItemMetadataCL;

import java.security.InvalidParameterException;

/**
 * Created by Prospector
 */
public class ItemMetadataCrystek extends ItemMetadataCL {

	public ItemMetadataCrystek(String name) {
		super(Crystek.modcl, name);
	}

	public ItemStack getStack(String name) {
		return getStack(name, 1);
	}

	public ItemStack getStack(String name, int count) {
		for (String type : types) {
			if (type.equalsIgnoreCase(name)) {
				ItemStack stack = new ItemStack(CrystekItems.REGISTRY.get(this.name), count, types.indexOf(name));
				stack.setCount(count);
				return stack;
			}
		}
		throw new InvalidParameterException("Stack not found: " + name);
	}
}
