package me.heldplayer.HeldGeneration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;
import me.heldplayer.HeldGeneration.helpers.BiomeHelp;
import me.heldplayer.HeldGeneration.profiler.Profiler;

import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HeldGeneration extends JavaPlugin {
	private PluginDescriptionFile pdf;
	public BiomeHelp[] biomes = null;
	public byte[] heightMap = null;
	public byte[] temperature = null;
	public int mapsWidth = 0, mapsHeight = 0;
	public static HeldGeneration instance = null;

	@Override
	public void onDisable() {
		Profiler.startSection("disable");

		instance = null;

		String disabledMessage = this.pdf.getFullName() + " is now disabled!";

		this.pdf = null;

		getLogger().info(disabledMessage);

		Profiler.endSection();

		Profiler.endAll();

		Profiler.saveResults(new File(this.getDataFolder(), "profiler.txt"));
	}

	@Override
	public void onEnable() {
		Profiler.startSection("enable");

		instance = this;

		File dataFolder = this.getDataFolder();

		if (!dataFolder.exists()) {
			dataFolder.mkdir();
		}

		this.pdf = getDescription();

		File biomes = new File(this.getDataFolder(), "biomes.png");

		if (biomes.exists()) {
			getLogger().info("Initializing biome map...");

			try {
				BufferedImage biomeImage = ImageIO.read(biomes);

				mapsWidth = biomeImage.getWidth();
				mapsHeight = biomeImage.getHeight();

				this.biomes = new BiomeHelp[mapsWidth * mapsHeight];

				for (int x = 0; x < mapsWidth; x++) {
					for (int y = 0; y < mapsHeight; y++) {
						this.biomes[x + y * mapsWidth] = BiomeHelp.getFromColor(biomeImage.getRGB(x, y) & 0xFFFFFF);
					}
				}

				biomeImage.flush();
			} catch (IOException e) {
				getLogger().severe("Failed loading biome image");
				e.printStackTrace();
			}
		}

		File maps = new File(this.getDataFolder(), "maps.png");

		maps: {
			if (maps.exists()) {
				getLogger().info("Initializing height and temperature map...");

				try {
					BufferedImage mapsImage = ImageIO.read(maps);

					if (mapsWidth != mapsImage.getWidth() && mapsHeight != mapsImage.getHeight()) {
						getLogger().severe("biomes.png and maps.png don't share the same dimensions, ignoring maps.png!");

						break maps;
					}

					heightMap = new byte[mapsWidth * mapsHeight];
					temperature = new byte[mapsWidth * mapsHeight];

					for (int x = 0; x < mapsWidth; x++) {
						for (int y = 0; y < mapsHeight; y++) {
							heightMap[x + y * mapsWidth] = (byte) ((mapsImage.getRGB(x, y) & 0xFF0000) >> 16);
							temperature[x + y * mapsWidth] = (byte) (mapsImage.getRGB(x, y) & 0xFF);
						}
					}

					mapsImage.flush();
				} catch (IOException e) {
					getLogger().severe("Failed loading maps image");
					e.printStackTrace();
				}
			}
		}

		getLogger().info(this.pdf.getFullName() + " is now enabled!");

		Profiler.endSection();
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		Profiler.startSection("provider");
		Profiler.startSection("instantiate");

		ChunkProvider provider = new ChunkProvider();

		Profiler.endSection();
		Profiler.endSection();

		return provider;
	}

	public Biome getBiome(int x, int z) {
		int index = 0;

		if (x < 0) {
			index += -x % mapsWidth;
		} else {
			index += x % mapsWidth;
		}

		if (z < 0) {
			index += (-z % mapsHeight) * mapsWidth;
		} else {
			index += (z % mapsHeight) * mapsWidth;
		}

		return biomes[index].biome;
	}

	public byte getHeight(int x, int z) {
		int index = 0;

		if (x < 0) {
			index += -x % mapsWidth;
		} else {
			index += x % mapsWidth;
		}

		if (z < 0) {
			index += (-z % mapsHeight) * mapsWidth;
		} else {
			index += (z % mapsHeight) * mapsWidth;
		}

		return heightMap[index];
	}

	public byte getTemperature(int x, int z) {
		int index = 0;

		if (x < 0) {
			index += -x % mapsWidth;
		} else {
			index += x % mapsWidth;
		}

		if (z < 0) {
			index += (-z % mapsHeight) * mapsWidth;
		} else {
			index += (z % mapsHeight) * mapsWidth;
		}

		return temperature[index];
	}
}
