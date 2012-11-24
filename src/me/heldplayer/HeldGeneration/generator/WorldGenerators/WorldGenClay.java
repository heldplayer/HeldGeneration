package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenClay extends WorldGenerator {
	/** The block ID for clay. */
	private int clayBlockId;

	/** The number of blocks to generate. */
	private int numberOfBlocks;

	public WorldGenClay(int numberOfBlocks) {
		this.clayBlockId = Mat.Clay.id;
		this.numberOfBlocks = numberOfBlocks;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (!BlockHelper.isWater(world.getBlockTypeIdAt(x, y, z))) {
			return false;
		} else {
			int xzSize = rand.nextInt(this.numberOfBlocks - 2) + 2;
			byte zSize = 1;

			for (int posX = x - xzSize; posX <= x + xzSize; ++posX) {
				for (int posZ = z - xzSize; posZ <= z + xzSize; ++posZ) {
					int xOffset = posX - x;
					int zOffset = posZ - z;

					if (xOffset * xOffset + zOffset * zOffset <= xzSize * xzSize) {
						for (int posY = y - zSize; posY <= y + zSize; ++posY) {
							int blockId = world.getBlockTypeIdAt(posX, posY, posZ);

							if (blockId == Mat.Dirt.id || blockId == Mat.Clay.id) {
								setBlock(world, posX, posY, posZ, this.clayBlockId);
							}
						}
					}
				}
			}

			return true;
		}
	}
}