package me.heldplayer.HeldGeneration.generator.Populators;

import java.util.Random;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenDungeons;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenLakes;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class ChunkPopulator extends BlockPopulator {
	private ChunkProvider provider;

	public ChunkPopulator(ChunkProvider provider) {
		this.provider = provider;
	}

	@Override
	public void populate(World world, Random uselessRand, Chunk chunk) {
		int cx = chunk.getX();
		int cz = chunk.getZ();
		int blockX = cx * 16;
		int blockZ = cz * 16;

		Random rand = new Random(world.getSeed());
		long randX = rand.nextLong() / 2L * 2L + 1L;
		long randZ = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(cx * randX + cz * randZ ^ world.getSeed());

		//Biome biome = world.getBiome(blockX + 16, blockZ + 16);
		//PopulatorAssist assist = PopulatorAssist.getAssist(biome);

		boolean hasVillage = false;

		if (world.canGenerateStructures()) {
			// Mineshaft gen
			if (hasVillage = this.provider.generator.villageGenerator.generateStructuresInChunk(world, rand, cx, cx, this.provider))
				System.out.println("Generated village " + cx + " " + cz);
			// Stronghold gen
		}

		int lakeX;
		int lakeY;
		int lakeZ;

		if (!hasVillage && rand.nextInt(4) == 0) {
			lakeX = blockX + rand.nextInt(16 + 8);
			lakeY = rand.nextInt(128);
			lakeZ = blockZ + rand.nextInt(16) + 8;
			if ((new WorldGenLakes(9)).generate(world, rand, lakeX, lakeY, lakeZ))
				;//System.out.println("Generated water lake " + lakeX + " " + lakeY + " " + lakeZ);
		}

		if (!hasVillage && rand.nextInt(8) == 0) {
			lakeX = blockX + rand.nextInt(16 + 8);
			lakeY = rand.nextInt(120);
			lakeZ = blockZ + rand.nextInt(16) + 8;

			if (lakeY < 63 || rand.nextInt(10) == 0) {
				if ((new WorldGenLakes(11)).generate(world, rand, lakeX, lakeY, lakeZ))
					;//System.out.println("Generated lava lake " + lakeX + " " + lakeY + " " + lakeZ);
			}
		}

		for (lakeX = 0; lakeX < 8; ++lakeX) {
			lakeY = blockX + rand.nextInt(16) + 8;
			lakeZ = rand.nextInt(128);
			int dungeonZ = blockZ + rand.nextInt(16) + 8;

			if ((new WorldGenDungeons()).generate(world, rand, lakeY, lakeZ, dungeonZ))
				;//System.out.println("Generated dungeon " + lakeY + " " + lakeZ + " " + dungeonZ);

		}

		// Biome specific decorating gen
		// Spawn animals according to biome

		for (lakeX = 0; lakeX < 16; ++lakeX) {
			for (lakeY = 0; lakeY < 16; ++lakeY) {
				int highestY = world.getHighestBlockYAt(lakeX + blockX, lakeY + blockZ);
				Block iceBlock = world.getBlockAt(lakeX + blockX, highestY - 1, lakeY + blockZ);

				if ((iceBlock.getTypeId() == 8 || iceBlock.getTypeId() == 9) && iceBlock.getData() == 0 && iceBlock.getTemperature() < 0.15F) {
					//iceBlock.setTypeId(79);
					continue;
				}

				Block snowBlock = world.getBlockAt(lakeX + blockX, highestY, lakeY + blockZ);

				if (snowBlock.getTypeId() == 0 && snowBlock.getTemperature() < 0.15F) {
					//snowBlock.setTypeId(78);
				}
			}
		}
	}

}
