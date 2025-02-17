package depixelation.gwindlib.config;

import depixelation.gwindlib.GlobalWindLib;
import net.minecraft.util.Identifier;

public class Constants {
    public static final Identifier WIND_SEED_PACKET_ID = Identifier.of(GlobalWindLib.MOD_ID, "wind-seed-packet");

    public static final boolean DEBUG = true;

    /**
     * A global multiplier for a global wind system, applied on top of MAX_WIND_SPEED. Default 1.0.
     */
    public static final double WIND_SPEED_MULTIPLIER = 1.0;

    /**
     * Length of Minecraft day, in ticks
     */
    public static final int DAY_LENGTH = 24000;

    /**
     * Length of what I call an "hour," in ticks
     */
    public static final int HOUR_LENGTH = 2000;

    /**
     * The speed in m/s that a wind amplitude of 1 corresponds to.
     */
    public static final float MAX_WIND_SPEED = 44.0f;

    /**
     * length of gust in ticks
     */
    public static final int GUST_LENGTH = 80;

    /**
     * Wind strength fraction for Thundering (0 - 1). Default 1
     */
    public static final double THUNDER = 1.0;

    /**
     * Wind strength fraction for Raining (0 - 1). Default 0.3
     */
    public static final double RAIN = 0.3;

    /**
     * Wind strength fraction for Clear (0 - 1). Default 0.1
     */
    public static final double CLEAR = 0.1;

    public static void initConstants(){

    }
}
