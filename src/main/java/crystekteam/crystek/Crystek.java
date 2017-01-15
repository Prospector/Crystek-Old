package crystekteam.crystek;

import crystekteam.crystek.init.CrystekItems;
import crystekteam.crystek.init.CrystekOreDict;
import crystekteam.crystek.init.MachinesInit;
import crystekteam.crystek.proxy.CrystekServer;
import crystekteam.crysteklib.ModCL;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Prospector
 */
@Mod(modid = Crystek.MOD_ID, name = Crystek.MOD_NAME, version = Crystek.MOD_VERSION, dependencies = Crystek.MOD_DEPENDENCIES, acceptedMinecraftVersions = Crystek.MINECRAFT_VERSIONS)
public class Crystek extends ModCL {
	public static final String MOD_NAME = "Crystek";
	public static final String MOD_ID = "crystek";
	public static final String PREFIX = "crystek:";
	public static final String MOD_VERSION = "%version%";
	public static final String MINECRAFT_VERSIONS = "[1.11.2]";
	public static final String MOD_DEPENDENCIES = "required-after:reborncore;after:JEI@[4.0,);";
	public static final String SERVER_PROXY_CLASS = "crystekteam.crystek.proxy.CrystekServer";
	public static final String CLIENT_PROXY_CLASS = "crystekteam.crystek.proxy.CrystekClient";

	@Mod.Instance(MOD_ID)
	public static Crystek instance;
	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
	public static CrystekServer proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CrystekItems.init();
		MachinesInit.init();
		proxy.registerRenders();

		CrystekOreDict.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@Override
	public String MOD_NAME() {
		return MOD_NAME;
	}

	@Override
	public String MOD_ID() {
		return MOD_ID;
	}

	@Override
	public String MOD_VERSION() {
		return MOD_VERSION;
	}

	@Override
	public String MOD_DEPENDENCIES() {
		return MOD_DEPENDENCIES;
	}

	@Override
	public String SERVER_PROXY() {
		return SERVER_PROXY_CLASS;
	}

	@Override
	public String CLIENT_PROXY() {
		return CLIENT_PROXY_CLASS;
	}
}