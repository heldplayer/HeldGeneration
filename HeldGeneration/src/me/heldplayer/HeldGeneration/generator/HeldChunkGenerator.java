package me.heldplayer.HeldGeneration.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class HeldChunkGenerator extends ChunkGenerator {
	@Override
	public boolean canSpawn(World world, int x, int z) {
		Block highest = world.getBlockAt(x, world.getHighestBlockYAt(x, z), z);

		return highest.getType() != Material.AIR && highest.getType() != Material.WATER && highest.getType() != Material.LAVA;
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		List<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		return populators;
	}

	@Override
	public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {

		return super.generateBlockSections(world, random, x, z, biomes);
	}

	void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
		if (result[y >> 4] == null) {
			result[y >> 4] = new byte[4096];
		}
		result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
	}

	byte getBlock(byte[][] result, int x, int y, int z) {
		if (result[y >> 4] == null) {
			return (byte) 0;
		}
		return result[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
	}
}
