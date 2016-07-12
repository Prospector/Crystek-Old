package crystekteam.crystek.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigAE
{
    public static ConfigAE instance = null;

    public static String CATEGORY_RECIPE = "recipe";
    public static String CATEGORY_POWER = "power";
    public static String CATEGORY_UPGRADES = "upgrade";

    public static long generatorTick;
    public static long generatorStorage;
    public static long solarTick;
    public static int solarPanelMaxUpgrades;

    public static long teslacellBaseStorage;

    public static long acceleratorCost;
    public static int acceleratorRange;
    public static int acceleratorSecondsBetweenGrowthTicks;

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
        solarPanelMaxUpgrades = config.get(CATEGORY_UPGRADES,
                "solarPanelMaxUpgrades", 16,
                "The amount of upgrades the solar panel can have")
                .getInt();
        teslacellBaseStorage = config.get(CATEGORY_POWER,
                "teslacellBaseStorage", 5000000,
                "The amount of base power the tesla cell can store")
                .getLong();
        generatorStorage = config.get(CATEGORY_POWER,
                "generatorStorage", 500000,
                "The amount of power the generator can store")
                .getLong();
        acceleratorCost = config.get(CATEGORY_POWER,
                "acceleratorCost", 100,
                "The amount of power consumed")
                .getLong();
        acceleratorRange = config.get(CATEGORY_POWER,
                "acceleratorRange", 5,
                "The range of the accelerator")
                .getInt();
        acceleratorSecondsBetweenGrowthTicks = config.get(CATEGORY_POWER,
                "acceleratorSecondsBetweenGrowthTicks", 5,
                "The range of seconds between uses")
                .getInt();


        if(config.hasChanged())
        {
            config.save();
        }
    }
}
