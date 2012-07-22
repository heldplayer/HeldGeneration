package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenFlowers extends WorldGenerator {
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;

	public WorldGenFlowers(int typeId) {
		this.plantBlockId = typeId;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int attempts = 0; attempts < 64; ++attempts) {
			int posX = x + rand.nextInt(8) - rand.nextInt(8);
			int posY = y + rand.nextInt(4) - rand.nextInt(4);
			int posZ = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.getBlockTypeIdAt(posX, posY, posZ) == 0 && BlockHelper.canBlockStay(world.getBlockAt(posX, posY, posZ), Mat.fromId(plantBlockId))) {
				setBlock(world, posX, posY, posZ, this.plantBlockId);
			}
		}

		return true;
	}
}