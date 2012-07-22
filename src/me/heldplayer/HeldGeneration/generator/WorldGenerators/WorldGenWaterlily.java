package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenWaterlily extends WorldGenerator {
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int attempts = 0; attempts < 10; ++attempts) {
			int posX = x + rand.nextInt(8) - rand.nextInt(8);
			int posY = y + rand.nextInt(4) - rand.nextInt(4);
			int posZ = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.getBlockTypeIdAt(posX, posY, posZ) == 0 && BlockHelper.canBlockStay(world.getBlockAt(posX, posY, posZ), Mat.WaterLily)) {
				setBlock(world, posX, posY, posZ, Mat.WaterLily.id);
			}
		}

		return true;
	}
}