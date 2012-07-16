package me.heldplayer.HeldGeneration;

import org.bukkit.block.Biome;

public enum BiomeHelp {
	OCEAN(Biome.OCEAN, 0, -1.0F, 0.4F),
	PLAINS(Biome.PLAINS, 1, 0.1F, 0.3F, 0.8F, 0.4F),
	DESERT(Biome.DESERT, 2, 0.1F, 0.2F, (short) 12, (short) 12, 2.0F, 0.0F),
	EXTREME_HILLS(Biome.EXTREME_HILLS, 3, 0.2F, 1.3F, 0.2F, 0.3F),
	FOREST(Biome.FOREST, 4, 0.1F, 0.3F, 0.7F, 0.8F),
	TAIGA(Biome.TAIGA, 5, 0.1F, 0.4F, 0.05F, 0.8F),
	SWAMPLAND(Biome.SWAMPLAND, 6, -0.2F, 0.1F, 0.8F, 0.5F),
	RIVER(Biome.RIVER, 7, -0.5F, 0.0F),
	HELL(Biome.HELL, 8, 0.1F, 0.3F, 2.0F, 0.0F),
	SKY(Biome.SKY, 9),
	FROZEN_OCEAN(Biome.FROZEN_OCEAN, 10, -1.0F, 0.5F, 0.0F, 0.5F),
	FROZEN_RIVER(Biome.FROZEN_RIVER, 11, -0.5F, 0.0F, 0.0F, 0.5F),
	ICE_PLAINS(Biome.ICE_PLAINS, 12, 0.1F, 0.3F, 0.0F, 0.5F),
	ICE_MOUNTAINS(Biome.ICE_MOUNTAINS, 13, 0.2F, 1.2F, 0.0F, 0.5F),
	MUSHROOM_ISLAND(Biome.MUSHROOM_ISLAND, 14, 0.9F, 1.0F, 0.9F, 1.0F),
	MUSHROOM_ISLAND_SHORE(Biome.MUSHROOM_SHORE, 15, -1.0F, 0.1F, 0.9F, 1.0F),
	BEACH(Biome.BEACH, 16, 0.0F, 0.1F, (short) 12, (short) 12, 0.8F, 0.4F),
	DESERT_HILLS(Biome.DESERT_HILLS, 17, 0.2F, 0.7F, (short) 12, (short) 12, 2.0F, 0.0F),
	FOREST_HILLS(Biome.FOREST_HILLS, 18, 0.2F, 0.6F, 0.7F, 0.8F),
	TAIGA_HILLS(Biome.TAIGA_HILLS, 19, 0.2F, 0.7F, 0.05F, 0.8F),
	EXTREME_HILLS_EDGE(Biome.SMALL_MOUNTAINS, 20, 0.2F, 0.8F, 0.2F, 0.3F),
	JUNGLE(Biome.JUNGLE, 21, 0.2F, 0.4F, 1.2F, 0.9F),
	JUNGLE_HILLS(Biome.JUNGLE_HILLS, 22, 1.8F, 0.2F, 1.2F, 0.9F);

	public final Biome biome;
	public final int id;
	public final float minH, maxH;
	public final short topBlock, fillerBlock;
	public final float temperature, rainfall;

	private BiomeHelp(Biome biome, int id, float minH, float maxH, short topBlock, short fillerBlock, float temperature, float rainfall) {
		this.biome = biome;
		this.id = id;
		this.minH = minH;
		this.maxH = maxH;
		this.topBlock = topBlock;
		this.fillerBlock = fillerBlock;
		this.temperature = temperature;
		this.rainfall = rainfall;
	}

	private BiomeHelp(Biome biome, int id, float minH, float maxH, float temperature, float rainfall) {
		this(biome, id, minH, maxH, (short) 2, (short) 3, temperature, rainfall);
	}

	private BiomeHelp(Biome biome, int id, float minH, float maxH) {
		this(biome, id, minH, maxH, (short) 2, (short) 3, 0.5F, 0.5F);
	}

	private BiomeHelp(Biome biome, int id) {
		this(biome, id, 0.1F, 0.3F, (short) 2, (short) 3, 0.5F, 0.5F);
	}

	public static Biome getBiome(int id) {
		for (BiomeHelp help : values()) {
			if (help.id == id) {
				return help.biome;
			}
		}

		return Biome.PLAINS;
	}

	public static int getId(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.id;
			}
		}

		return 1;
	}

	public static float getMinHeight(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.minH;
			}
		}

		return 0.1F;
	}

	public static float getMaxHeight(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.maxH;
			}
		}

		return 0.3F;
	}

	public static short getTopBlock(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.topBlock;
			}
		}

		return 2;
	}

	public static short getFillerBlock(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.fillerBlock;
			}
		}

		return 3;
	}

	public static float getTemperature(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.temperature;
			}
		}

		return 0.5F;
	}

	public static float getRainfall(Biome biome) {
		for (BiomeHelp help : values()) {
			if (help.biome == biome) {
				return help.rainfall;
			}
		}

		return 0.5F;
	}
}
