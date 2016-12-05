package crystekteam.crystek;

import crystekteam.crystek.lib.InfoCrystek;
import crystekteam.crystek.proxy.CrystekServer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by McKeever on 02-Nov-16.
 */
@Mod(modid = InfoCrystek.MOD_ID, name = InfoCrystek.MOD_NAME, version = InfoCrystek.MOD_VERSION, dependencies = InfoCrystek.MOD_DEPENDENCIES, acceptedMinecraftVersions = "[1.11]")
public class Crystek {

	@Mod.Instance(InfoCrystek.MOD_ID)
	public static Crystek instance;

	@SidedProxy(clientSide = InfoCrystek.CLIENT_PROXY_CLASS, serverSide = InfoCrystek.SERVER_PROXY_CLASS)
	public static CrystekServer proxy;
	public static CreativeTabs tab = new CreativeTabs("crystek") {
		@Override
		public String getTabLabel() {
			return "crystek";
		}

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(Blocks.BEDROCK);
		}
	};

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
