package crystekteam.crystek.init;

import crystekteam.crystek.blocks.BlockCrystek;
import crystekteam.crystek.blocks.ItemBlockMachine;
import crystekteam.crystek.machines.MachineFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Gigabit101 on 06/12/2016.
 */
public class CrystekBlocks
{
    public static BlockCrystek blockCrystek;

    public static void init()
    {
        blockCrystek = new BlockCrystek(new MachineFurnace());
        registerBlock(blockCrystek, ItemBlockMachine.class, "machine");
    }

    public static void registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
    }

    public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name)
    {
        block.setRegistryName(name);
        GameRegistry.register(block);
        try
        {
            ItemBlock itemBlock = itemclass.getConstructor(Block.class).newInstance(block);
            itemBlock.setRegistryName(name);
            GameRegistry.register(itemBlock);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
