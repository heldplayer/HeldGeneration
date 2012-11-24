package me.heldplayer.HeldGeneration.GenLayers;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;

import org.bukkit.WorldType;
import org.bukkit.block.Biome;

public class GenLayerBiome extends GenLayer {
	/** this sets all the biomes that are allowed to appear in the overworld */
	private Biome[] allowedBiomes;

	public GenLayerBiome(long seed, GenLayer parent, WorldType worldType) {
		super(seed);
		this.allowedBiomes = new Biome[] { Biome.DESERT, Biome.FOREST, Biome.EXTREME_HILLS, Biome.SWAMPLAND, Biome.PLAINS, Biome.TAIGA, Biome.JUNGLE };
		this.parent = parent;

		if (worldType == WorldType.VERSION_1_1) {
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
	public int[] getInts(int startX, int startZ, int sizeX, int sizeZ) {
		int[] var5 = this.parent.getInts(startX, startZ, sizeX, sizeZ);
		int[] var6 = IntCache.getIntCache(sizeX * sizeZ);

		for (int var7 = 0; var7 < sizeZ; ++var7) {
			for (int var8 = 0; var8 < sizeX; ++var8) {
				initChunkSeed((var8 + startX), (var7 + startZ));
				int var9 = var5[var8 + var7 * sizeX];

				if (var9 == 0) {
					var6[var8 + var7 * sizeX] = 0;
				} else if (var9 == BiomeHelp.MUSHROOM_ISLAND.id) {
					var6[var8 + var7 * sizeX] = var9;
				} else if (var9 == 1) {
					var6[var8 + var7 * sizeX] = BiomeHelp.getId(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
				} else {
					var6[var8 + var7 * sizeX] = BiomeHelp.ICE_PLAINS.id;
				}
			}
		}

		return var6;
	}
}