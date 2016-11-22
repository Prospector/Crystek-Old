package crystekteam.crystek;

import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.lib.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class CreativeTabCrystek extends CreativeTabs {
    public static CreativeTabCrystek instance = new CreativeTabCrystek();

    public CreativeTabCrystek() {
        super(ModInfo.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem() {
        Random rand = new Random();
        int num = rand.nextInt(4);
        if (num == 0)
            return new ItemStack(ModItems.ironGrindingBlade);
        else if (num == 1)
            return new ItemStack(ModItems.goldGrindingBlade);
        else if (num == 2)
            return new ItemStack(ModItems.diamondGrindingBlade);
        else if (num == 3)
            return new ItemStack(ModItems.obsidianGrindingBlade);
        return new ItemStack(ModItems.teslaAlloyGrindingBlade);
    }
}
