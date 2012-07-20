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
		for (int var6 = 0; var6 < 64; ++var6) {
			int var7 = x + rand.nextInt(8) - rand.nextInt(8);
			int var8 = y + rand.nextInt(4) - rand.nextInt(4);
			int var9 = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.getBlockTypeIdAt(var7, var8, var9) == 0 && BlockHelper.canBlockStay(world.getBlockAt(var7, var8, var9), Mat.fromId(plantBlockId))) {
				world.getBlockAt(var7, var8, var9).setTypeIdAndData(this.plantBlockId, (byte) 0, false);
			}
		}

		return true;
	}
}