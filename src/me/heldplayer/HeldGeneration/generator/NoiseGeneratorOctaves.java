
package me.heldplayer.HeldGeneration.generator;

import java.util.Random;

public class NoiseGeneratorOctaves extends NoiseGenerator {
    /**
     * Collection of noise generation functions. Output is combined to produce
     * different octaves of noise.
     */
    private NoiseGeneratorPerlin[] generatorCollection;
    private int octaves;

    public NoiseGeneratorOctaves(Random par1Random, int par2) {
        this.octaves = par2;
        this.generatorCollection = new NoiseGeneratorPerlin[par2];

        for (int var3 = 0; var3 < par2; ++var3) {
            this.generatorCollection[var3] = new NoiseGeneratorPerlin(par1Random);
        }
    }

    public double[] generateNoiseOctaves(double[] noiseArray, int par2, int par3, int par4, int par5, int par6, int par7, double par8, double par10, double par12) {
        if (noiseArray == null) {
            noiseArray = new double[par5 * par6 * par7];
        }
        else {
            for (int var14 = 0; var14 < noiseArray.length; ++var14) {
                noiseArray[var14] = 0.0D;
            }
        }

        double var27 = 1.0D;

        for (int var16 = 0; var16 < this.octaves; ++var16) {
            double var17 = par2 * var27 * par8;
            double var19 = par3 * var27 * par10;
            double var21 = par4 * var27 * par12;
            long var23 = floor_double_long(var17);
            long var25 = floor_double_long(var21);
            var17 -= var23;
            var21 -= var25;
            var23 %= 16777216L;
            var25 %= 16777216L;
            var17 += var23;
            var21 += var25;
            this.generatorCollection[var16].func_805_a(noiseArray, var17, var19, var21, par5, par6, par7, par8 * var27, par10 * var27, par12 * var27, var27);
            var27 /= 2.0D;
        }

        return noiseArray;
    }

    /**
     * Bouncer function to the main one with some default arguments.
     */
    public double[] generateNoiseOctaves(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, double par6, double par8, double par10) {
        return this.generateNoiseOctaves(par1ArrayOfDouble, par2, 10, par3, par4, 1, par5, par6, 1.0D, par8);
    }

    private static long floor_double_long(double par0) {
        long var2 = (long) par0;
        return par0 < var2 ? var2 - 1L : var2;
    }
}
