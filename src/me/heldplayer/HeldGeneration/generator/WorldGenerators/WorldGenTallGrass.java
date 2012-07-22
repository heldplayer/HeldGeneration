package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTallGrass extends WorldGenerator {
	/** Stores ID for WorldGenTallGrass */
	private int tallGrassID;
	private int tallGrassMetadata;

	public WorldGenTallGrass(int typeId, int dataValue) {
		this.tallGrassID = typeId;
		this.tallGrassMetadata = dataValue;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int posTop;

		for (; ((posTop = world.getBlockTypeIdAt(x, y, z)) == 0 || posTop == Mat.Leaves.id) && y > 0; --y) {
			;
		}

		for (int attempts = 0; attempts < 128; ++attempts) {
			int posX = x + rand.nextInt(8) - rand.nextInt(8);
			int posY = y + rand.nextInt(4) - rand.nextInt(4);
			int posZ = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.getBlockTypeIdAt(posX, posY, posZ) == 0 && BlockHelper.canBlockStay(world.getBlockAt(posX, posY, posZ), Mat.fromId(tallGrassID))) {
				world.getBlockAt(posX, posY, posZ).setTypeIdAndData(this.tallGrassID, (byte) this.tallGrassMetadata, false);
			}
		}

		return true;
	}
}