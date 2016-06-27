package crystekteam.crystek.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigAE
{
    public static ConfigAE instance = null;

    public static String CATEGORY_RECIPE = "recipe";
    public static String CATEGORY_POWER = "power";

    public static long generatorTick;
    public static long solarTick;
    public static int solarPanelMaxUpgrades;

    public static Configuration config;

    private ConfigAE(File configFile)
    {
        config = new Configuration(configFile);
        config.load();

        ConfigAE.Configs();

        config.save();
    }

    public static ConfigAE initialize(File configFile)
    {
        if (instance == null)
        {
            instance = new ConfigAE(configFile);
        }
        else
        {
            throw new IllegalStateException("Cannot initialize Crystek config twice");
        }
        return instance;
    }

    public static ConfigAE instance()
    {
        if (instance == null)
        {

            throw new IllegalStateException("Instance of Crystek requested before initialization");
        }
        return instance;
    }

    public static void Configs()
    {
        //Power
        generatorTick = config.get(CATEGORY_POWER,
                "generatorTick", 20,
                "The amount of power crated by the generator")
                .getLong();
        solarTick = config.get(CATEGORY_POWER,
                "solarTick", 5,
                "The amount of power crated by the solar panel")
                .getLong();
        solarPanelMaxUpgrades = config.get(CATEGORY_POWER,
                "solarPanelMaxUpgrades", 16,
                "The amount of upgrades the solar panel can have")
                .getInt();


        if(config.hasChanged())
        {
            config.save();
        }
    }
}
