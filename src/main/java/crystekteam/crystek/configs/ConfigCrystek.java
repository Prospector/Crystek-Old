package crystekteam.crystek.configs;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Gigabit101 on 18/01/2017.
 */
public class ConfigCrystek
{
    private static ConfigCrystek INSTANCE = null;
    public static String CATEGORY_MACHINE = "machine";

    public static Configuration config;

    private ConfigCrystek(File configFile)
    {
        config = new Configuration(configFile);
        config.load();

        ConfigCrystek.Configs();

        config.save();
    }

    public static ConfigCrystek initialize(File configFile)
    {
        if (INSTANCE == null)
            INSTANCE = new ConfigCrystek(configFile);
        else
            throw new IllegalStateException("Cannot initialize Crystek Config twice");
        return INSTANCE;
    }

    public static ConfigCrystek instance()
    {
        if (INSTANCE == null)
        {
            throw new IllegalStateException("Instance of Crystek Config requested before initialization");
        }
        return INSTANCE;
    }


    public static void Configs()
    {
        for(MachineConfig c: ConfigRegistry.getConfigList())
        {

        }

        if (config.hasChanged())
        {
            config.save();
        }
    }
}
