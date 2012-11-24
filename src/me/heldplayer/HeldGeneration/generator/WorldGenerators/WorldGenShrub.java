package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenShrub extends WorldGenerator {
	private int leavesData;
	private int logData;

	public WorldGenShrub(int logData, int leavesData) {
		this.logData = logData;
		this.leavesData = leavesData;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int typeId1;

		for (; ((typeId1 = world.getBlockTypeIdAt(x, y, z)) == 0 || typeId1 == Mat.Leaves.id) && y > 0; --y) {
			;
		}

		int typeId2 = world.getBlockTypeIdAt(x, y, z);

		if (typeId2 == Mat.Dirt.id || typeId2 == Mat.Grass.id) {
			++y;
			this.setBlockAndMetadata(world, x, y, z, Mat.Log.id, this.logData);

			for (int posY = y; posY <= y + 2; ++posY) {
				int relPosY = posY - y;
				int relRelPosY = 2 - relPosY;

				for (int relPosX = x - relRelPosY; relPosX <= x + relRelPosY; ++relPosX) {
					int relRelPosX = relPosX - x;

					for (int relPosZ = z - relRelPosY; relPosZ <= z + relRelPosY; ++relPosZ) {
						int relRelPosZ = relPosZ - z;

						if ((Math.abs(relRelPosX) != relRelPosY || Math.abs(relRelPosZ) != relRelPosY || rand.nextInt(2) != 0) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(relPosX, posY, relPosZ))) {
							this.setBlockAndMetadata(world, relPosX, posY, relPosZ, Mat.Leaves.id, this.leavesData);
						}
					}
				}
			}
		}

		return true;
	}
}