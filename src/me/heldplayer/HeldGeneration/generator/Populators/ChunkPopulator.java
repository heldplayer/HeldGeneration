package me.heldplayer.HeldGeneration.generator.Populators;

import java.util.Random;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenDungeons;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenLakes;
import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;
import me.heldplayer.HeldGeneration.helpers.PopulatorAssist;
import me.heldplayer.HeldGeneration.helpers.SpawnerAnimals;
import me.heldplayer.HeldGeneration.profiler.Profiler;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.generator.BlockPopulator;

public class ChunkPopulator extends BlockPopulator {
	private ChunkProvider provider;

	public ChunkPopulator(ChunkProvider provider) {
		this.provider = provider;
	}

	@Override
	public void populate(World world, Random uselessRand, Chunk chunk) {
		Profiler.startSection(world.getName());
		Profiler.startSection("populate");

		Profiler.startSection("setup");

		int cx = chunk.getX();
		int cz = chunk.getZ();
		int blockX = cx * 16;
		int blockZ = cz * 16;

		Random rand = new Random(world.getSeed());
		long randX = rand.nextLong() / 2L * 2L + 1L;
		long randZ = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(cx * randX + cz * randZ ^ world.getSeed());

		Biome biome = world.getBiome(blockX + 16, blockZ + 16);
		PopulatorAssist assist = PopulatorAssist.getAssist(biome);
		assist.setRandomSeed(rand);

		net.minecraft.server.World nWorld = ((CraftWorld) world).getHandle();

		boolean hasVillage = false;

		Profiler.endStartSection("structures");

		if (world.canGenerateStructures()) {
			// Mineshaft gen
			//if (hasVillage = this.provider.generator.villageGenerator.generateStructuresInChunk(world, rand, cx, cx, this.provider))
			//hasVillage = true;//System.out.println("Generated village " + cx + " " + cz);
			// Stronghold gen
		}

		Profiler.endStartSection("waterLake");

		int lakeX;
		int lakeY;
		int lakeZ;

		if (!hasVillage && rand.nextInt(4) == 0) {
			lakeX = blockX + rand.nextInt(16 + 8);
			lakeY = rand.nextInt(128);
			lakeZ = blockZ + rand.nextInt(16) + 8;
			(new WorldGenLakes(Mat.WaterStill.id)).generate(world, rand, lakeX, lakeY, lakeZ);
		}

		Profiler.endStartSection("lavaLake");

		if (!hasVillage && rand.nextInt(8) == 0) {
			lakeX = blockX + rand.nextInt(16 + 8);
			lakeY = rand.nextInt(120);
			lakeZ = blockZ + rand.nextInt(16) + 8;

			if (lakeY < 63 || rand.nextInt(10) == 0) {
				(new WorldGenLakes(Mat.LavaStill.id)).generate(world, rand, lakeX, lakeY, lakeZ);
			}
		}

		Profiler.endStartSection("dungeon");

		for (lakeX = 0; lakeX < 8; ++lakeX) {
			lakeY = blockX + rand.nextInt(16) + 8;
			lakeZ = rand.nextInt(128);
			int dungeonZ = blockZ + rand.nextInt(16) + 8;

			(new WorldGenDungeons()).generate(world, rand, lakeY, lakeZ, dungeonZ);

		}

		Profiler.endStartSection("biome");

		PopulatorAssist.decorator.decorate(world, rand, blockX, blockZ, assist);

		Profiler.endStartSection("animals");

		SpawnerAnimals.performWorldGenSpawning(world, biome, blockX, blockZ, 16, 16, rand, assist);

		Profiler.endStartSection("frost");

		blockX += 8;
		blockZ += 8;

		for (lakeX = 0; lakeX < 16; ++lakeX) {
			for (lakeY = 0; lakeY < 16; ++lakeY) {
				// XXX: requires craftbukkit.jar
				int highestY = world.getHighestBlockYAt(lakeX + blockX, lakeY + blockZ);

				boolean isWater = BlockHelper.isWater(world.getBlockTypeIdAt(lakeX + blockX, highestY - 1, lakeY + blockZ));
				boolean isSource = nWorld.getData(lakeX + blockX, highestY - 1, lakeY + blockZ) == 0;

				if (isWater && isSource && world.getTemperature(lakeX + blockX, lakeY + blockZ) < 0.15F) {
					nWorld.setRawTypeId(lakeX + blockX, highestY - 1, lakeY + blockZ, Mat.Ice.id);
					continue;
				}

				if (world.getBlockTypeIdAt(lakeX + blockX, highestY - 1, lakeY + blockZ) == 0 && world.getTemperature(lakeX + blockX, lakeY + blockZ) < 0.15F) {
					nWorld.setRawTypeId(lakeX + blockX, highestY - 1, lakeY + blockZ, Mat.Snow.id);
				}
			}
		}

		Profiler.endSection();

		assist.setRandomSeed(null);

		Profiler.endSection();
		Profiler.endSection();
	}

}
