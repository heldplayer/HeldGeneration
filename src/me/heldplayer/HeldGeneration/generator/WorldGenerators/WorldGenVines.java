package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Direction;
import me.heldplayer.HeldGeneration.helpers.Facing;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenVines extends WorldGenerator {
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int countX = x;

		for (int countZ = z; y < 128; ++y) {
			if (world.getBlockTypeIdAt(x, y, z) == 0) {
				for (int face = 2; face <= 5; ++face) {
					if (BlockHelper.canPlcaeVineOnSide(world.getBlockAt(x, y, z), face)) {
						setBlockAndMetadata(world, x, y, z, Mat.Vine.id, 1 << Direction.vineGrowth[Facing.faceToSide[face]]);
						break;
					}
				}
			} else {
				x = countX + rand.nextInt(4) - rand.nextInt(4);
				z = countZ + rand.nextInt(4) - rand.nextInt(4);
			}
		}

		return true;
	}
}