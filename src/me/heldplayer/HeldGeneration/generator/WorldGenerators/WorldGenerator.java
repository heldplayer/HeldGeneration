package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.profiler.Profiler;

import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;

public abstract class WorldGenerator {
	/**
	 * Sets wither or not the generator should notify blocks of blocks it
	 * changes. When the world is first generated,
	 * this is false, when saplings grow, this is true.
	 */
	private final boolean doBlockNotify;

	public WorldGenerator() {
		this.doBlockNotify = false;
	}

	public WorldGenerator(boolean notify) {
		this.doBlockNotify = notify;
	}

	public abstract boolean generate(World world, Random rand, int x, int y, int z);

	/**
	 * Rescales the generator settings, only used in WorldGenBigTree
	 */
	public void setScale(double x, double y, double z) {
	}

	/**
	 * Sets the block without metadata in the world, notifying neighbors if
	 * enabled.
	 */
	protected void setBlock(World world, int x, int y, int z, int typeId) {
		setBlockAndMetadata(world, x, y, z, typeId, 0);
	}

	/**
	 * Sets the block in the world, notifying neighbors if enabled.
	 */
	protected void setBlockAndMetadata(World world, int x, int y, int z, int typeId, int data) {
		Profiler.startSection("setBlock");

		net.minecraft.server.World nWorld = ((CraftWorld) world).getHandle();

		if (this.doBlockNotify) {
			// XXX: requires craftbukkit.jar
			nWorld.setTypeIdAndData(x, y, z, typeId, data);
			//world.getBlockAt(x, y, z).setTypeIdAndData(typeId, (byte) data, true);
		} else {
			nWorld.setRawTypeIdAndData(x, y, z, typeId, data);
			//world.getBlockAt(x, y, z).setTypeIdAndData(typeId, (byte) data, false);
		}
		Profiler.endSection();
	}
}