package me.heldplayer.HeldGeneration.helpers;

import java.util.List;

import me.heldplayer.HeldGeneration.HeldGeneration;
import me.heldplayer.HeldGeneration.GenLayers.GenLayer;
import me.heldplayer.HeldGeneration.GenLayers.IntCache;

import org.bukkit.WorldType;
import org.bukkit.block.Biome;

public final class BiomeHelper {
	public void setSeed(long seed) {
		GenLayer[] var4 = GenLayer.func_48425_a(seed, WorldType.NORMAL);
		this.genBiomes = var4[0];
		this.biomeIndexLayer = var4[1];
	}

	private GenLayer genBiomes;

	/** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
	private GenLayer biomeIndexLayer;

	/**
	 * Returns an array of biomes for the location input.
	 */
	public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int depth) {
		IntCache.resetIntCache();

		if (biomes == null || biomes.length < width * depth) {
			biomes = new Biome[width * depth];
		}

		int[] ints = this.genBiomes.getInts(x, z, width, depth);

		for (int i = 0; i < width * depth; ++i) {
			biomes[i] = BiomeHelp.getBiome(ints[i]);
		}

		if (HeldGeneration.instance.biomes != null) {
			for (int relX = 0; relX + x < x + width; relX++) {
				for (int relZ = 0; relZ + z < z + depth; relZ++) {
					biomes[relX + relZ * width] = HeldGeneration.instance.getBiome(relX + x, relZ + z);
				}
			}
		}

		return biomes;
	}

	public Biome[] loadBlockGeneratorData(Biome[] biomes, int x, int z, int width, int depth) {
		IntCache.resetIntCache();

		if (biomes == null || biomes.length < width * depth) {
			biomes = new Biome[width * depth];
		}

		int[] ints = this.biomeIndexLayer.getInts(x, z, width, depth);

		for (int i = 0; i < width * depth; ++i) {
			biomes[i] = BiomeHelp.getBiome(ints[i]);
		}

		if (HeldGeneration.instance.biomes != null) {
			for (int relX = 0; relX + x < x + width; relX++) {
				for (int relZ = 0; relZ + z < z + depth; relZ++) {
					biomes[relX + relZ * width] = HeldGeneration.instance.getBiome(relX + x, relZ + z);
				}
			}
		}

		return biomes;
	}

	/**
	 * checks given Chunk's Biomes against List of allowed ones
	 */
	public boolean areBiomesViable(int par1, int par2, int par3, List<Biome> par4List) {
		int var5 = par1 - par3 >> 2;
		int var6 = par2 - par3 >> 2;
		int var7 = par1 + par3 >> 2;
		int var8 = par2 + par3 >> 2;
		int var9 = var7 - var5 + 1;
		int var10 = var8 - var6 + 1;
		int[] var11 = this.genBiomes.getInts(var5, var6, var9, var10);

		for (int var12 = 0; var12 < var9 * var10; ++var12) {
			Biome var13 = BiomeHelp.getBiome(var11[var12]);

			if (!par4List.contains(var13)) {
				return false;
			}
		}

		return true;
	}

	public Biome getBiomeAt(int x, int z) {
		Biome[] biomes = loadBlockGeneratorData(null, x, z, 1, 1);

		return biomes[0];
	}
}
