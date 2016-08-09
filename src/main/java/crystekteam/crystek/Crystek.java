package crystekteam.crystek;

import crystekteam.crystek.compat.CompatHandler;
import crystekteam.crystek.config.ConfigAE;
import crystekteam.crystek.eventhandlers.CrystekEventHandler;
import crystekteam.crystek.init.ModBlocks;
import crystekteam.crystek.init.ModFluids;
import crystekteam.crystek.init.ModItems;
import crystekteam.crystek.init.ModRecipes;
import crystekteam.crystek.lib.ModInfo;
import crystekteam.crystek.network.PacketHandler;
import crystekteam.crystek.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES)
public class Crystek
{
    @Mod.Instance(ModInfo.MOD_ID)
    public static Crystek instance;

    public Crystek()
    {
        FluidRegistry.enableUniversalBucket();
    }

    public static ConfigAE config;

    @SidedProxy(clientSide = "crystekteam.crystek.proxy.ClientProxy", serverSide = "crystekteam.crystek.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        instance = this;
        //Config stuff
        String path = event.getSuggestedConfigurationFile().getAbsolutePath().replace(ModInfo.MOD_ID, "Crystek");
        config = ConfigAE.initialize(new File(path));
        //Register fluids
        ModFluids.init();
        //Register Items
        ModItems.init();
        //Register Blocks
        ModBlocks.init();
        //Packets
        PacketHandler.setChannels(NetworkRegistry.INSTANCE.newChannel(ModInfo.MOD_ID + "_packets", new PacketHandler()));
        //Register Item/Block textures (Client side only)
        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Register Gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(ModInfo.MOD_ID, new GuiHandler());
        //Register Compat Handler
        CompatHandler.init(event);
        MinecraftForge.EVENT_BUS.register(new CrystekEventHandler());
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        //Register Recipes
        ModRecipes.init();
    }
}
