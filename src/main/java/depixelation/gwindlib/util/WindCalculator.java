package depixelation.gwindlib.util;

import depixelation.gwindlib.config.Constants;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WindCalculator {
    public static Vec3d calculate(long t, long seed, World world){
        double θ = Noise.positiveNoise((double) t / (Constants.DAY_LENGTH * 3), (int) seed) * Math.PI * 2;
        double a = Noise.positiveNoise((double) t / (Constants.DAY_LENGTH), -(int) seed);

        if (world.isThundering()) {
            a *= Constants.THUNDER;
        } else if (world.isRaining()) {
            a *= Constants.RAIN;
        } else {
            a *= Constants.CLEAR;
        }

        double hourlyVariance = Noise.positiveNoise((double) t / Constants.HOUR_LENGTH, ((int) seed) + 1);
        hourlyVariance /= 0.5;
        hourlyVariance += 0.5;
        a *= hourlyVariance;

        double gustiness = Noise.positiveNoise((double) t / (Constants.HOUR_LENGTH * 2.5), ((int) seed) + 2);

        double gusts = Noise.positiveNoise((double) t / Constants.GUST_LENGTH, ((int) seed) + 3);
        gusts = sCurve(gusts, 2);

        a = mul(a, gusts, gustiness);

        a *= Constants.MAX_WIND_SPEED/20;

        a *= Constants.WIND_SPEED_MULTIPLIER;

        return new Vec3d(a * MathHelper.cos((float) θ), 0,  a * MathHelper.sin((float) θ));
    }

    private static double mix (double a, double b, double factor){
        return a + (b - a) * factor;
    }

    private static double mul (double a, double b, double factor){
        return mix(a, a * b, factor);
    }

    // S-curve function with scaling factor
    public static double sCurve(double x, double f) {
        double innerTerm = x / (1 - x);  // Calculate (x / (1 - x))
        double denominator = 1 + Math.pow(innerTerm, -f);  // (x / (1 - x))^(-f) + 1
        return 1 / denominator;  // Return 1 / denominator
    }
}
