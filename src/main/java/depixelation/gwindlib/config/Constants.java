package depixelation.gwindlib.config;

import depixelation.gwindlib.GlobalWindLib;
import net.minecraft.util.Identifier;

public class Constants {
    private static SimpleConfig CONFIG;
    private static ModConfigProvider configs;
    private static final String MAX_WIND_SPEED_KEY = "max-wind-speed";
    private static final String DAY_LENGTH_KEY = "long-variance-duration";
    private static final String HOUR_LENGTH_KEY = "medium-variance-duration";
    private static final String GUST_LENGTH_KEY = "short-variance-duration";
    private static final String THUNDER_KEY = "thunder-multiplier";
    private static final String RAIN_KEY = "rain-multiplier";
    private static final String CLEAR_KEY = "clear-multiplier";

    static {
        configs = new ModConfigProvider();
        configs.addKeyValuePair(MAX_WIND_SPEED_KEY, 40.0f, "(blocks per second) The maximum possible wind speed for a multiplier of 1, corresponding to a noise amplitude of 1");
        configs.addKeyValuePair(THUNDER_KEY, 1.0, "The wind speed multiplier for thundering conditions. The max wind speed for thunder is this value multiplied by " + MAX_WIND_SPEED_KEY);
        configs.addKeyValuePair(RAIN_KEY, 0.3, "The wind speed multiplier for raining conditions. The max wind speed for rain is this value multiplied by " + MAX_WIND_SPEED_KEY);
        configs.addKeyValuePair(CLEAR_KEY, 0.1, "The wind speed multiplier for raining conditions. The max wind speed for rain is this value multiplied by " + MAX_WIND_SPEED_KEY);
        configs.addKeyValuePair(DAY_LENGTH_KEY, 24000, "(game ticks) The length of the longest, \"daily\" peak-to-valley variance in wind speed");
        configs.addKeyValuePair(HOUR_LENGTH_KEY, 2000, "(game ticks) The length of medium-duration peak-to-valley variance in wind speed, throughout the day");
        configs.addKeyValuePair(GUST_LENGTH_KEY, 80, "(game ticks) The length of \"gusts,\" the shortest peak-to-valley variance in wind speed.");

        CONFIG = SimpleConfig.of(GlobalWindLib.MOD_ID).provider(configs).request();
        MAX_WIND_SPEED = (float) CONFIG.getOrDefault(MAX_WIND_SPEED_KEY, 40.0f);
        THUNDER = CONFIG.getOrDefault(THUNDER_KEY, 1.0);
        RAIN = CONFIG.getOrDefault(RAIN_KEY, 0.3);
        CLEAR = CONFIG.getOrDefault(CLEAR_KEY, 0.1);
        DAY_LENGTH = CONFIG.getOrDefault(DAY_LENGTH_KEY, 24000);
        HOUR_LENGTH = CONFIG.getOrDefault(HOUR_LENGTH_KEY, 2000);
        GUST_LENGTH = CONFIG.getOrDefault(GUST_LENGTH_KEY, 80);
    }

    public static final Identifier WIND_SEED_PACKET_ID = Identifier.of(GlobalWindLib.MOD_ID, "wind-seed-packet");

    public static final boolean DEBUG = true;

    /**
     * A global multiplier for a global wind system, applied on top of MAX_WIND_SPEED. Default 1.0.
     */
    public static final double WIND_SPEED_MULTIPLIER = 1.0;

    /**
     * The speed in m/s that a wind amplitude of 1 corresponds to.
     */
    public static final float MAX_WIND_SPEED;

    /**
     * Length of Minecraft day, in ticks
     */
    public static final int DAY_LENGTH;

    /**
     * Length of what I call an "hour," in ticks
     */
    public static final int HOUR_LENGTH;
    /**
     * length of gust in ticks
     */
    public static final int GUST_LENGTH;

    /**
     * Wind strength fraction for Thundering (0 - 1). Default 1
     */
    public static final double THUNDER;

    /**
     * Wind strength fraction for Raining (0 - 1). Default 0.3
     */
    public static final double RAIN;

    /**
     * Wind strength fraction for Clear (0 - 1). Default 0.1
     */
    public static final double CLEAR;

    public static void initConfig(){

    }
}
