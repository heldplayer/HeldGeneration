package me.heldplayer.HeldGeneration.helpers;

import java.util.ArrayList;

import org.bukkit.block.Biome;

public class BiomeHelp {
	public final static ArrayList<BiomeHelp> values = new ArrayList<BiomeHelp>();

	public static BiomeHelp OCEAN = new BiomeHelp(Biome.OCEAN, 0).setColor(0x003f80).setHeights(-1.0F, 0.4F);
	public static BiomeHelp PLAINS = new BiomeHelp(Biome.PLAINS, 1).setColor(0xbeff00).setTempAndRain(0.8F, 0.4F);
	public static BiomeHelp DESERT = new BiomeHelp(Biome.DESERT, 2).setColor(0xffff00).setHeights(0.1F, 0.2F).setTempAndRain(2.0F, 0.0F).setBlocks(Mat.Sand.id, Mat.Sand.id);
	public static BiomeHelp EXTREME_HILLS = new BiomeHelp(Biome.EXTREME_HILLS, 3).setColor(0x738080).setHeights(0.2F, 1.3F).setTempAndRain(0.2F, 0.3F);
	public static BiomeHelp FOREST = new BiomeHelp(Biome.FOREST, 4).setColor(0x00ff00).setTempAndRain(0.7F, 0.8F);
	public static BiomeHelp TAIGA = new BiomeHelp(Biome.TAIGA, 5).setColor(0x00ff00).setHeights(0.1F, 0.4F).setTempAndRain(0.05F, 0.8F);
	public static BiomeHelp SWAMPLAND = new BiomeHelp(Biome.SWAMPLAND, 6).setColor(0x608000).setHeights(-0.2F, 0.1F).setTempAndRain(0.8F, 0.9F);
	public static BiomeHelp RIVER = new BiomeHelp(Biome.RIVER, 7).setColor(0x608000).setHeights(-0.5F, 0.0F);
	public static BiomeHelp HELL = new BiomeHelp(Biome.HELL, 8).setColor(0x608000).setTempAndRain(2.0F, 0.0F);
	public static BiomeHelp SKY = new BiomeHelp(Biome.SKY, 9).setColor(0xbfbfbf);
	public static BiomeHelp FROZEN_OCEAN = new BiomeHelp(Biome.FROZEN_OCEAN, 10).setColor(0x3f3f80).setHeights(-1.0F, 0.5F).setTempAndRain(0.0F, 0.5F);
	public static BiomeHelp FROZEN_RIVER = new BiomeHelp(Biome.FROZEN_RIVER, 11).setColor(0x6080ff).setHeights(-0.5F, 0.0F).setTempAndRain(0.0F, 0.5F);
	public static BiomeHelp ICE_PLAINS = new BiomeHelp(Biome.ICE_PLAINS, 12).setColor(0xbeff80).setTempAndRain(0.0F, 0.5F);
	public static BiomeHelp ICE_MOUNTAINS = new BiomeHelp(Biome.ICE_MOUNTAINS, 13).setColor(0x7380ac).setHeights(0.2F, 1.2F).setTempAndRain(0.0F, 0.5F);
	public static BiomeHelp MUSHROOM_ISLAND = new BiomeHelp(Biome.MUSHROOM_ISLAND, 14).setColor(0xac80ac).setHeights(0.9F, 1.0F).setTempAndRain(0.9F, 1.0F);
	public static BiomeHelp MUSHROOM_ISLAND_SHORE = new BiomeHelp(Biome.MUSHROOM_SHORE, 15).setColor(0xac80dc).setHeights(-1.0F, 0.1F).setTempAndRain(0.9F, 1.0F);
	public static BiomeHelp BEACH = new BiomeHelp(Biome.BEACH, 16).setColor(0xffff80).setHeights(0.0F, 0.1F).setTempAndRain(0.8F, 0.4F);
	public static BiomeHelp DESERT_HILLS = new BiomeHelp(Biome.DESERT_HILLS, 17).setColor(0xcccc29).setHeights(0.2F, 0.7F).setTempAndRain(2.0F, 0.0F);
	public static BiomeHelp FOREST_HILLS = new BiomeHelp(Biome.FOREST_HILLS, 18).setColor(0x008000).setHeights(0.2F, 0.6F).setTempAndRain(0.7F, 0.8F);
	public static BiomeHelp TAIGA_HILLS = new BiomeHelp(Biome.TAIGA_HILLS, 19).setColor(0x6d8080).setHeights(0.2F, 0.7F).setTempAndRain(0.05F, 0.8F);
	public static BiomeHelp EXTREME_HILLS_EDGE = new BiomeHelp(Biome.SMALL_MOUNTAINS, 20).setColor(0x454c4c).setHeights(0.2F, 0.8F).setTempAndRain(0.2F, 0.3F);
	public static BiomeHelp JUNGLE = new BiomeHelp(Biome.JUNGLE, 21).setHeights(0.2F, 0.4F).setColor(0x00ff20).setTempAndRain(1.2F, 0.9F);
	public static BiomeHelp JUNGLE_HILLS = new BiomeHelp(Biome.JUNGLE_HILLS, 22).setColor(0x36b245).setHeights(1.8F, 0.2F).setTempAndRain(1.2F, 0.9F);

	public final Biome biome;
	public final int id;
	public float minH = 0.1F, maxH = 0.3F;
	public short topBlock = Mat.Grass.id, fillerBlock = Mat.Dirt.id;
	public float temperature = 0.5F, rainfall = 0.5F;
	public int color = 0x000000;

	private BiomeHelp(Biome biome, int id) {
		this.biome = biome;
		this.id = id;

		values.add(this);
	}

	private BiomeHelp setBlocks(short topBlock, short fillerBlock) {
		this.topBlock = topBlock;
		this.fillerBlock = fillerBlock;
		return this;
	}

	private BiomeHelp setHeights(float minH, float maxH) {
		this.minH = minH;
		this.maxH = maxH;
		return this;
	}

	private BiomeHelp setTempAndRain(float temperature, float rainfall) {
		this.temperature = temperature;
		this.rainfall = rainfall;
		return this;
	}

	private BiomeHelp setColor(int color) {
		this.color = color;
		return this;
	}

	public static Biome getBiome(int id) {
		for (BiomeHelp help : values) {
			if (help.id == id) {
				return help.biome;
			}
		}

		return Biome.PLAINS;
	}

	public static int getId(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.id;
			}
		}

		return 1;
	}

	public static float getMinHeight(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.minH;
			}
		}

		return -0.85F;
	}

	public static float getMaxHeight(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.maxH;
			}
		}

		return -0.95F;
	}

	public static short getTopBlock(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.topBlock;
			}
		}

		return Mat.Grass.id;
	}

	public static short getFillerBlock(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.fillerBlock;
			}
		}

		return Mat.Dirt.id;
	}

	public static float getTemperature(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.temperature;
			}
		}

		return 0.5F;
	}

	public static float getRainfall(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.rainfall;
			}
		}

		return 0.5F;
	}

	public static int getColor(Biome biome) {
		for (BiomeHelp help : values) {
			if (help.biome == biome) {
				return help.color;
			}
		}

		return 0x000000;
	}
}
