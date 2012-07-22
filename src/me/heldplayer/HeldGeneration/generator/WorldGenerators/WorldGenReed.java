package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenReed extends WorldGenerator {
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int var6 = 0; var6 < 20; ++var6) {
			int posX = x + rand.nextInt(4) - rand.nextInt(4);
			int posY = y;
			int posZ = z + rand.nextInt(4) - rand.nextInt(4);

			if (world.getBlockTypeIdAt(posX, y, posZ) == 0 && (BlockHelper.isWater(world.getBlockTypeIdAt(posX - 1, y - 1, posZ)) || BlockHelper.isWater(world.getBlockTypeIdAt(posX + 1, y - 1, posZ)) || BlockHelper.isWater(world.getBlockTypeIdAt(posX, y - 1, posZ - 1)) || BlockHelper.isWater(world.getBlockTypeIdAt(posX, y - 1, posZ + 1)))) {
				int randHeight = 2 + rand.nextInt(rand.nextInt(3) + 1);

				for (int height = 0; height < randHeight; ++height) {
					if (BlockHelper.canBlockStay(world.getBlockAt(posX, posY + height, posZ), Mat.SugarCane)) {
						setBlock(world, posX, posY + height, posZ, Mat.SugarCane.id);
					}
				}
			}
		}

		return true;
	}
}