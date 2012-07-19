package me.heldplayer.HeldGeneration.GenLayers;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;

public class GenLayerRiver extends GenLayer {
	public GenLayerRiver(long seed, GenLayer parent) {
		super(seed);
		super.parent = parent;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be
	 * interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer
	 * subclass.
	 */
	@Override
	public int[] getInts(int startX, int startZ, int sizeX, int sizeZ) {
		int var5 = startX - 1;
		int var6 = startZ - 1;
		int var7 = sizeX + 2;
		int var8 = sizeZ + 2;
		int[] var9 = this.parent.getInts(var5, var6, var7, var8);
		int[] var10 = IntCache.getIntCache(sizeX * sizeZ);

		for (int var11 = 0; var11 < sizeZ; ++var11) {
			for (int var12 = 0; var12 < sizeX; ++var12) {
				int var13 = var9[var12 + 0 + (var11 + 1) * var7];
				int var14 = var9[var12 + 2 + (var11 + 1) * var7];
				int var15 = var9[var12 + 1 + (var11 + 0) * var7];
				int var16 = var9[var12 + 1 + (var11 + 2) * var7];
				int var17 = var9[var12 + 1 + (var11 + 1) * var7];

				if (var17 != 0 && var13 != 0 && var14 != 0 && var15 != 0 && var16 != 0) {
					if (var17 == var13 && var17 == var15 && var17 == var14 && var17 == var16) {
						var10[var12 + var11 * sizeX] = -1;
					} else {
						var10[var12 + var11 * sizeX] = BiomeHelp.RIVER.id;
					}
				} else {
					var10[var12 + var11 * sizeX] = BiomeHelp.RIVER.id;
				}
			}
		}

		return var10;
	}
}