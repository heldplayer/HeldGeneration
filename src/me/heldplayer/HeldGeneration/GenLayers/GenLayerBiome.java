package me.heldplayer.HeldGeneration.GenLayers;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;

import org.bukkit.WorldType;
import org.bukkit.block.Biome;

public class GenLayerBiome extends GenLayer {
	/** this sets all the biomes that are allowed to appear in the overworld */
	private Biome[] allowedBiomes;

	public GenLayerBiome(long par1, GenLayer par3GenLayer, WorldType par4WorldType) {
		super(par1);
		this.allowedBiomes = new Biome[] { Biome.DESERT, Biome.FOREST, Biome.EXTREME_HILLS, Biome.SWAMPLAND, Biome.PLAINS, Biome.TAIGA, Biome.JUNGLE };
		this.parent = par3GenLayer;

		if (par4WorldType == WorldType.VERSION_1_1) {
			this.allowedBiomes = new Biome[] { Biome.DESERT, Biome.FOREST, Biome.EXTREME_HILLS, Biome.SWAMPLAND, Biome.PLAINS, Biome.TAIGA };
		}
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be
	 * interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer
	 * subclass.
	 */
	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] var5 = this.parent.getInts(par1, par2, par3, par4);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7) {
			for (int var8 = 0; var8 < par3; ++var8) {
				initChunkSeed((var8 + par1), (var7 + par2));
				int var9 = var5[var8 + var7 * par3];

				if (var9 == 0) {
					var6[var8 + var7 * par3] = 0;
				} else if (var9 == BiomeHelp.MUSHROOM_ISLAND.id) {
					var6[var8 + var7 * par3] = var9;
				} else if (var9 == 1) {
					var6[var8 + var7 * par3] = BiomeHelp.getId(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
				} else {
					var6[var8 + var7 * par3] = BiomeHelp.ICE_PLAINS.id;
				}
			}
		}

		return var6;
	}
}