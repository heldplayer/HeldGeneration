package me.heldplayer.HeldGeneration.GenLayers;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;

public class GenLayerShore extends GenLayer {
	public GenLayerShore(long par1, GenLayer par3GenLayer) {
		super(par1);
		this.parent = par3GenLayer;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be
	 * interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer
	 * subclass.
	 */
	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7) {
			for (int var8 = 0; var8 < par3; ++var8) {
				initChunkSeed((var8 + par1), (var7 + par2));
				int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
				int var10;
				int var11;
				int var12;
				int var13;

				if (var9 == BiomeHelp.MUSHROOM_ISLAND.id) {
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 != BiomeHelp.OCEAN.id && var11 != BiomeHelp.OCEAN.id && var12 != BiomeHelp.OCEAN.id && var13 != BiomeHelp.OCEAN.id) {
						var6[var8 + var7 * par3] = var9;
					} else {
						var6[var8 + var7 * par3] = BiomeHelp.MUSHROOM_ISLAND_SHORE.id;
					}
				} else if (var9 != BiomeHelp.OCEAN.id && var9 != BiomeHelp.RIVER.id && var9 != BiomeHelp.SWAMPLAND.id && var9 != BiomeHelp.EXTREME_HILLS.id) {
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 != BiomeHelp.OCEAN.id && var11 != BiomeHelp.OCEAN.id && var12 != BiomeHelp.OCEAN.id && var13 != BiomeHelp.OCEAN.id) {
						var6[var8 + var7 * par3] = var9;
					} else {
						var6[var8 + var7 * par3] = BiomeHelp.BEACH.id;
					}
				} else if (var9 == BiomeHelp.EXTREME_HILLS.id) {
					var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
					var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
					var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
					var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

					if (var10 == BiomeHelp.EXTREME_HILLS.id && var11 == BiomeHelp.EXTREME_HILLS.id && var12 == BiomeHelp.EXTREME_HILLS.id && var13 == BiomeHelp.EXTREME_HILLS.id) {
						var6[var8 + var7 * par3] = var9;
					} else {
						var6[var8 + var7 * par3] = BiomeHelp.EXTREME_HILLS_EDGE.id;
					}
				} else {
					var6[var8 + var7 * par3] = var9;
				}
			}
		}

		return var6;
	}
}