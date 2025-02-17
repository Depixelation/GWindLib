package depixelation.gwindlib.util;

/**
 * Credit ChatGPT
 */
class Noise {

    // Hash function for deterministic and chaotic randomness
    private static long hash(int x, int seed) {
        x ^= seed; // Mix seed into hash
        x ^= x >> 16;
        x *= 0x85ebca6b;
        x ^= x >> 13;
        x *= 0xc2b2ae35;
        x ^= x >> 16;
        return x;
    }

    // Smoothstep interpolation
    private static double smoothstep(double t) {
        return t * t * (3.0f - 2.0f * t);
    }

    /**
     * 1D Seeded Smooth Noise in range [-1, 1]
     */
    static double balancedNoise(double x, int seed) {
        int xi = (int) Math.floor(x); // Floor(x) to get integer grid point
        double xf = x - xi; // Fractional part

        // Get hashed pseudo-random values at two surrounding integer points
        double v0 = ((hash(xi, seed) & 0xFFFF) / 65535.0f) * 2 - 1; // Normalize to [-1,1]
        double v1 = ((hash(xi + 1, seed) & 0xFFFF) / 65535.0f) * 2 - 1;

        // Smooth interpolation
        double t = smoothstep(xf);
        return v0 + t * (v1 - v0);
    }

    /**
     * 1D Seeded Smooth Noise in range [0, 1]
     */
    static double positiveNoise(double x, int seed) {
        int xi = (int) Math.floor(x); // Floor(x) to get integer grid point
        double xf = x - xi; // Fractional part

        // Get hashed pseudo-random values at two surrounding integer points
        double v0 = ((hash(xi, seed) & 0xFFFF) / 65535.0f);
        double v1 = ((hash(xi + 1, seed) & 0xFFFF) / 65535.0f);

        // Smooth interpolation
        double t = smoothstep(xf);
        return v0 + t * (v1 - v0);
    }
}

