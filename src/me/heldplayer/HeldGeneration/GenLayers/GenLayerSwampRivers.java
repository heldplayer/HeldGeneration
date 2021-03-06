
package me.heldplayer.HeldGeneration.GenLayers;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;

public class GenLayerSwampRivers extends GenLayer {
    public GenLayerSwampRivers(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be
     * interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer
     * subclass.
     */
    @Override
    public int[] getInts(int startX, int startZ, int sizeX, int sizeZ) {
        int[] var5 = this.parent.getInts(startX - 1, startZ - 1, sizeX + 2, sizeZ + 2);
        int[] var6 = IntCache.getIntCache(sizeX * sizeZ);

        for (int var7 = 0; var7 < sizeZ; ++var7) {
            for (int var8 = 0; var8 < sizeX; ++var8) {
                initChunkSeed((var8 + startX), (var7 + startZ));
                int var9 = var5[var8 + 1 + (var7 + 1) * (sizeX + 2)];

                if (var9 == BiomeHelp.SWAMPLAND.id && nextInt(6) == 0) {
                    var6[var8 + var7 * sizeX] = BiomeHelp.RIVER.id;
                }
                else if ((var9 == BiomeHelp.JUNGLE.id || var9 == BiomeHelp.JUNGLE_HILLS.id) && nextInt(8) == 0) {
                    var6[var8 + var7 * sizeX] = BiomeHelp.RIVER.id;
                }
                else {
                    var6[var8 + var7 * sizeX] = var9;
                }
            }
        }

        return var6;
    }
}
