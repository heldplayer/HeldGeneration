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

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var11;

		for (; ((var11 = par1World.getBlockTypeIdAt(par3, par4, par5)) == 0 || var11 == Mat.Leaves.id) && par4 > 0; --par4) {
			;
		}

		for (int var7 = 0; var7 < 4; ++var7) {
			int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.getBlockTypeIdAt(var8, var9, var10) == 0 && BlockHelper.canBlockStay(par1World.getBlockAt(var8, var9, var10), Mat.fromId(deadBushID))) {
				par1World.getBlockAt(var8, var9, var10).setTypeIdAndData(this.deadBushID, (byte) 0, false);
			}
		}

		return true;
	}
}