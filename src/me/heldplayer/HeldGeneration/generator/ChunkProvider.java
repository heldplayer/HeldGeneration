package me.heldplayer.HeldGeneration.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Populators.ChunkPopulator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class ChunkProvider extends ChunkGenerator {
	public ChunkProviderGenerate generator = new ChunkProviderGenerate(this);

	@Override
	public boolean canSpawn(World world, int x, int z) {
		Block highest = world.getBlockAt(x, world.getHighestBlockYAt(x, z), z);

		return highest.getType() != Material.AIR && highest.getType() != Material.WATER && highest.getType() != Material.LAVA;
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

		populators.add(new ChunkPopulator(this));

		return populators;
	}

	@Override
	public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes) {
		byte[] chunkBlocks = generator.generate(world, random, cx, cz, biomes);

		byte[][] result = new byte[16][4096];

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 128; y++) {
					result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = chunkBlocks[(x * 16 + z) * 128 + y];
				}
			}
		}

		return result;
	}

}
