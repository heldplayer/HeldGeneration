package me.heldplayer.HeldGeneration.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Populators.ChunkPopulator;
import me.heldplayer.HeldGeneration.profiler.Profiler;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class ChunkProvider extends ChunkGenerator {
	public ChunkProviderGenerate generator = new ChunkProviderGenerate(this);

	@Override
	public boolean canSpawn(World world, int x, int z) {
		Profiler.startSection("provider");
		Profiler.startSection("canSpawn");

		Block highest = world.getBlockAt(x, world.getHighestBlockYAt(x, z), z);

		boolean result = highest.getType() != Material.AIR && highest.getType() != Material.WATER && highest.getType() != Material.LAVA;

		Profiler.endSection();
		Profiler.endSection();

		return result;
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		Profiler.startSection("provider");
		Profiler.startSection("getDefaultPopulators");

		List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

		populators.add(new ChunkPopulator(this));

		Profiler.endSection();
		Profiler.endSection();

		return populators;
	}

	@Override
	public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes) {
		Profiler.startSection(world.getName());
		Profiler.startSection("generate");

		byte[][] result = generator.generate(world, random, cx, cz, biomes);

		Profiler.endSection();
		Profiler.endSection();

		return result;
	}

}
