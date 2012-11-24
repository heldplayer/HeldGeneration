package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenLiquids extends WorldGenerator {
	/** The ID of the liquid block used in this liquid generator. */
	private int liquidBlockId;

	public WorldGenLiquids(int typeId) {
		this.liquidBlockId = typeId;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlockTypeIdAt(x, y + 1, z) != Mat.Stone.id) {
			return false;
		} else if (world.getBlockTypeIdAt(x, y - 1, z) != Mat.Stone.id) {
			return false;
		} else if (world.getBlockTypeIdAt(x, y, z) != 0 && world.getBlockTypeIdAt(x, y, z) != Mat.Stone.id) {
			return false;
		} else {
			int walls = 0;

			if (world.getBlockTypeIdAt(x - 1, y, z) == Mat.Stone.id) {
				++walls;
			}

			if (world.getBlockTypeIdAt(x + 1, y, z) == Mat.Stone.id) {
				++walls;
			}

			if (world.getBlockTypeIdAt(x, y, z - 1) == Mat.Stone.id) {
				++walls;
			}

			if (world.getBlockTypeIdAt(x, y, z + 1) == Mat.Stone.id) {
				++walls;
			}

			int spaces = 0;

			if (world.getBlockTypeIdAt(x - 1, y, z) == 0) {
				++spaces;
			}

			if (world.getBlockTypeIdAt(x + 1, y, z) == 0) {
				++spaces;
			}

			if (world.getBlockTypeIdAt(x, y, z - 1) == 0) {
				++spaces;
			}

			if (world.getBlockTypeIdAt(x, y, z + 1) == 0) {
				++spaces;
			}

			if (walls == 3 && spaces == 1) {
				setBlock(world, x, y, z, this.liquidBlockId);
			}

			return true;
		}
	}
}