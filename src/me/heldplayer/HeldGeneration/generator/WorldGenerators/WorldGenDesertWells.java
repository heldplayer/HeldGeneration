package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenDesertWells extends WorldGenerator {
	public boolean generate(World world, Random rand, int x, int y, int z) {
		while (world.getBlockTypeIdAt(x, y, z) == 0 && y > 2) {
			--y;
		}

		int groundBlock = world.getBlockTypeIdAt(x, y, z);

		if (groundBlock != Mat.Sand.id) {
			return false;
		} else {
			int xyOffset;
			int zxOffset;

			for (xyOffset = -2; xyOffset <= 2; ++xyOffset) {
				for (zxOffset = -2; zxOffset <= 2; ++zxOffset) {
					if (world.getBlockTypeIdAt(x + xyOffset, y - 1, z + zxOffset) == 0 && world.getBlockTypeIdAt(x + xyOffset, y - 2, z + zxOffset) == 0) {
						return false;
					}
				}
			}

			for (xyOffset = -1; xyOffset <= 0; ++xyOffset) {
				for (zxOffset = -2; zxOffset <= 2; ++zxOffset) {
					for (int zOffset = -2; zOffset <= 2; ++zOffset) {
						setBlock(world, x + zxOffset, y + xyOffset, z + zOffset, Mat.Sandstone.id);
					}
				}
			}

			setBlock(world, x, y, z, Mat.WaterMoving.id);
			setBlock(world, x - 1, y, z, Mat.WaterMoving.id);
			setBlock(world, x + 1, y, z, Mat.WaterMoving.id);
			setBlock(world, x, y, z - 1, Mat.WaterMoving.id);
			setBlock(world, x, y, z + 1, Mat.WaterMoving.id);

			for (xyOffset = -2; xyOffset <= 2; ++xyOffset) {
				for (zxOffset = -2; zxOffset <= 2; ++zxOffset) {
					if (xyOffset == -2 || xyOffset == 2 || zxOffset == -2 || zxOffset == 2) {
						setBlock(world, x + xyOffset, y + 1, z + zxOffset, Mat.Sandstone.id);
					}
				}
			}

			setBlockAndMetadata(world, x + 2, y + 1, z, Mat.SingleSlab.id, 1);
			setBlockAndMetadata(world, x - 2, y + 1, z, Mat.SingleSlab.id, 1);
			setBlockAndMetadata(world, x, y + 1, z + 2, Mat.SingleSlab.id, 1);
			setBlockAndMetadata(world, x, y + 1, z - 2, Mat.SingleSlab.id, 1);

			for (xyOffset = -1; xyOffset <= 1; ++xyOffset) {
				for (zxOffset = -1; zxOffset <= 1; ++zxOffset) {
					if (xyOffset == 0 && zxOffset == 0) {
						setBlock(world, x + xyOffset, y + 4, z + zxOffset, Mat.Sandstone.id);
					} else {
						setBlockAndMetadata(world, x + xyOffset, y + 4, z + zxOffset, Mat.SingleSlab.id, 1);
					}
				}
			}

			for (xyOffset = 1; xyOffset <= 3; ++xyOffset) {
				setBlock(world, x - 1, y + xyOffset, z - 1, Mat.Sandstone.id);
				setBlock(world, x - 1, y + xyOffset, z + 1, Mat.Sandstone.id);
				setBlock(world, x + 1, y + xyOffset, z - 1, Mat.Sandstone.id);
				setBlock(world, x + 1, y + xyOffset, z + 1, Mat.Sandstone.id);
			}

			return true;
		}
	}
}