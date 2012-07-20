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
		int var11;

		for (; ((var11 = world.getBlockTypeIdAt(x, y, z)) == 0 || var11 == Mat.Leaves.id) && y > 0; --y) {
			;
		}

		for (int var7 = 0; var7 < 128; ++var7) {
			int var8 = x + rand.nextInt(8) - rand.nextInt(8);
			int var9 = y + rand.nextInt(4) - rand.nextInt(4);
			int var10 = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.getBlockTypeIdAt(var8, var9, var10) == 0 && BlockHelper.canBlockStay(world.getBlockAt(var8, var9, var10), Mat.fromId(tallGrassID))) {
				world.getBlockAt(var8, var9, var10).setTypeIdAndData(this.tallGrassID, (byte) this.tallGrassMetadata, false);
			}
		}

		return true;
	}
}