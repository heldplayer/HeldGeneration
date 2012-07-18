package me.heldplayer.HeldGeneration.generator.MapGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;

import org.bukkit.World;

public class MapGenBase {
	/** The number of Chunks to gen-check in any given direction. */
	protected int range = 8;

	/** The RNG used by the MapGen classes. */
	protected Random rand = new Random();

	/** This world object. */
	protected World worldObj;

	public void generate(World world, int cx, int cz, byte[] chunkBlocks, ChunkProvider provider) {
		int range = this.range;
		this.worldObj = world;
		this.rand.setSeed(world.getSeed());
		long var7 = this.rand.nextLong();
		long var9 = this.rand.nextLong();

		for (int x = cx - range; x <= cx + range; ++x) {
			for (int z = cz - range; z <= cz + range; ++z) {
				long var13 = x * var7;
				long var15 = z * var9;
				this.rand.setSeed(var13 ^ var15 ^ world.getSeed());
				recursiveGenerate(world, x, z, cx, cz, chunkBlocks, provider);
			}
		}
	}

	/**
	 * Recursively called by generate() (generate) and optionally by itself.
	 */
	protected void recursiveGenerate(World world, int ccx, int ccz, int cx, int cz, byte[] chunkBlocks, ChunkProvider provider) {
	}
}