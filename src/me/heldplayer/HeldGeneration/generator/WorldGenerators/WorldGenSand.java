package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenSand extends WorldGenerator {
	/** Stores ID for WorldGenSand */
	private int sandID;

	/** The maximum radius used when generating a patch of blocks. */
	private int radius;

	public WorldGenSand(int radius, int typeId) {
		this.sandID = typeId;
		this.radius = radius;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockTypeIdAt(x, y, z) != Mat.WaterMoving.id && world.getBlockTypeIdAt(x, y, z) != Mat.WaterStill.id) {
			return false;
		} else {
			int var6 = rand.nextInt(this.radius - 2) + 2;
			byte var7 = 2;

			for (int var8 = x - var6; var8 <= x + var6; ++var8) {
				for (int var9 = z - var6; var9 <= z + var6; ++var9) {
					int var10 = var8 - x;
					int var11 = var9 - z;

					if (var10 * var10 + var11 * var11 <= var6 * var6) {
						for (int var12 = y - var7; var12 <= y + var7; ++var12) {
							int var13 = world.getBlockTypeIdAt(var8, var12, var9);

							if (var13 == Mat.Dirt.id || var13 == Mat.Grass.id) {
								world.getBlockAt(var8, var12, var9).setTypeIdAndData(this.sandID, (byte) 0, false);
							}
						}
					}
				}
			}

			return true;
		}
	}
}