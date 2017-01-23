package crystekteam.crystek;

import crystekteam.crystek.configs.ConfigCrystek;
import crystekteam.crystek.event.CrystekEventHandler;
import crystekteam.crystek.init.CrystekItems;
import crystekteam.crystek.init.CrystekOreDict;
import crystekteam.crystek.init.CrystekRecipes;
import crystekteam.crystek.init.MachinesInit;
import crystekteam.crystek.network.Packets;
import crystekteam.crystek.proxy.CrystekServer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import reborncore.modcl.ModCL;
import reborncore.modcl.RegistryCL;

import java.io.File;
import java.util.Random;

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
	public static RegistryCL ITEM_REGISTRY = new CrystekItems();

	@Mod.Instance(MOD_ID)
	public static Crystek MOD_CL;
	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
	public static CrystekServer PROXY;
	public static ConfigCrystek config;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		getItemRegistry().init(MOD_CL);
		MachinesInit.init();
		PROXY.registerRenders();
        String path = event.getSuggestedConfigurationFile().getAbsolutePath().replace(MOD_ID, "Crystek");
        config = ConfigCrystek.initialize(new File(path));

		CrystekOreDict.init();
        MinecraftForge.EVENT_BUS.register(Packets.class);
    }

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		CrystekRecipes.init();

		MinecraftForge.EVENT_BUS.register(new CrystekEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(MOD_CL, new GuiHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@Override
	public String getModName() {
		return MOD_NAME;
	}

	@Override
	public String getModID() {
		return MOD_ID;
	}

	@Override
	public String getModVersion() {
		return MOD_VERSION;
	}

	@Override
	public String getModDependencies() {
		return MOD_DEPENDENCIES;
	}

	@Override
	public String getServerProxy() {
		return SERVER_PROXY_CLASS;
	}

	@Override
	public String getClientProxy() {
		return CLIENT_PROXY_CLASS;
	}

	@Override
	public RegistryCL getItemRegistry() {
		return ITEM_REGISTRY;
	}

	@Override
	public ItemStack getTabStack() {
		Random rand = new Random();
		int number = rand.nextInt(6);
		if (number == 1)
			return CrystekItems.MATERIALS.getStack("blue_crystal");
		else if (number == 2)
			return CrystekItems.MATERIALS.getStack("purple_crystal");
		else if (number == 3)
			return CrystekItems.MATERIALS.getStack("red_crystal");
		else if (number == 4)
			return CrystekItems.MATERIALS.getStack("yellow_crystal");
		else if (number == 5)
			return CrystekItems.MATERIALS.getStack("green_crystal");
		else
			return CrystekItems.MATERIALS.getStack("crystal");
	}
}