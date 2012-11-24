package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenDeadBush extends WorldGenerator {
	/** stores the ID for WorldGenDeadBush */
	private int deadBushID;

	public WorldGenDeadBush(int typeId) {
		this.deadBushID = typeId;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int var11;

		for (; ((var11 = world.getBlockTypeIdAt(x, y, z)) == 0 || var11 == Mat.Leaves.id) && y > 0; --y) {
			;
		}

		for (int var7 = 0; var7 < 4; ++var7) {
			int posX = x + rand.nextInt(8) - rand.nextInt(8);
			int posY = y + rand.nextInt(4) - rand.nextInt(4);
			int posZ = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.getBlockTypeIdAt(posX, posY, posZ) == 0 && BlockHelper.canBlockStay(world.getBlockAt(posX, posY, posZ), Mat.fromId(deadBushID))) {
				setBlock(world, posX, posY, posZ, this.deadBushID);
			}
		}

		return true;
	}
}