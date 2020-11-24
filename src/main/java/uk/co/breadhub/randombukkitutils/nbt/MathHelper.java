package uk.co.breadhub.randombukkitutils.nbt;

/*
 https://github.com/Bukkit/mc-dev/blob/master/net/minecraft/server/MathHelper.java
 from minecraft server code
 Cleaning up and working towards
 making a nbt Utility
*/

import java.util.Random;

public class MathHelper {

    private static final float[] floatArray = new float[65536];
    private static final int[] b;

    public static float sin(float input) {
        return floatArray[(int) (input * 10430.378F) & '\uffff'];
    }

    public static float cos(float input) {
        return floatArray[(int) (input * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static float sqrtFloat(float input) {
        return (float) Math.sqrt((double) input);
    }

    public static float sqrtDouble(double input) {
        return (float) Math.sqrt(input);
    }

    public static int parseFloat(float input) {
        int i = (int) input;

        return input < (float) i ? i - 1 : i;
    }

    public static int floor(double input) {
        int i = (int) input;

        return input < (double) i ? i - 1 : i;
    }

    public static long d(double input) {
        long i = (long) input;

        return input < (double) i ? i - 1L : i;
    }

    public static float abs(float f) {
        return f >= 0.0F ? f : -f;
    }

    public static int a(int i) {
        return i >= 0 ? i : -i;
    }

    public static int f(float f) {
        int i = (int) f;

        return f > (float) i ? i + 1 : i;
    }

    public static int f(double d0) {
        int i = (int) d0;

        return d0 > (double) i ? i + 1 : i;
    }

    public static int a(int i, int j, int k) {
        return i < j ? j : (Math.min(i, k));
    }

    public static float a(float f, float f1, float f2) {
        return f < f1 ? f1 : (Math.min(f, f2));
    }

    public static double a(double d0, double d1, double d2) {
        return d0 < d1 ? d1 : (Math.min(d0, d2));
    }

    public static double b(double d0, double d1, double d2) {
        return d2 < 0.0D ? d0 : (d2 > 1.0D ? d1 : d0 + (d1 - d0) * d2);
    }

    public static double a(double d0, double d1) {
        if (d0 < 0.0D) {
            d0 = -d0;
        }

        if (d1 < 0.0D) {
            d1 = -d1;
        }

        return Math.max(d0, d1);
    }

    public static int nextInt(Random random, int i, int j) {
        return i >= j ? i : random.nextInt(j - i + 1) + i;
    }

    public static float a(Random random, float f, float f1) {
        return f >= f1 ? f : random.nextFloat() * (f1 - f) + f;
    }

    public static double a(Random random, double d0, double d1) {
        return d0 >= d1 ? d0 : random.nextDouble() * (d1 - d0) + d0;
    }

    public static double a(long[] along) {
        long i = 0L;
        int j = along.length;

        for (long l : along) {
            i += l;
        }

        return (double) i / (double) along.length;
    }

    public static float g(float f) {
        f %= 360.0F;
        if (f >= 180.0F) {
            f -= 360.0F;
        }

        if (f < -180.0F) {
            f += 360.0F;
        }

        return f;
    }

    public static double g(double d0) {
        d0 %= 360.0D;
        if (d0 >= 180.0D) {
            d0 -= 360.0D;
        }

        if (d0 < -180.0D) {
            d0 += 360.0D;
        }

        return d0;
    }

    public static int a(String s, int i) {
        int j = i;

        try {
            j = Integer.parseInt(s);
        } catch (Throwable ignored) {}

        return j;
    }

    public static int a(String s, int i, int j) {
        int k = i;

        try {
            k = Integer.parseInt(s);
        } catch (Throwable ignored) {}

        if (k < j) {
            k = j;
        }

        return k;
    }

    public static double a(String s, double d0) {
        double d1 = d0;

        try {
            d1 = Double.parseDouble(s);
        } catch (Throwable ignored) {}

        return d1;
    }

    public static double a(String s, double d0, double d1) {
        double d2 = d0;

        try {
            d2 = Double.parseDouble(s);
        } catch (Throwable throwable) {}

        if (d2 < d1) {
            d2 = d1;
        }

        return d2;
    }

    static {
        for (int i = 0; i < 65536; ++i) {
            floatArray[i] = (float) Math.sin((double) i * 3.141592653589793D * 2.0D / 65536.0D);
        }

        b = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
    }
}