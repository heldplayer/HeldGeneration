package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenReed extends WorldGenerator {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		for (int var6 = 0; var6 < 20; ++var6) {
			int var7 = par3 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var8 = par4;
			int var9 = par5 + par2Random.nextInt(4) - par2Random.nextInt(4);

			if (par1World.getBlockTypeIdAt(var7, par4, var9) == 0 && (BlockHelper.isWater(par1World.getBlockTypeIdAt(var7 - 1, par4 - 1, var9)) || BlockHelper.isWater(par1World.getBlockTypeIdAt(var7 + 1, par4 - 1, var9)) || BlockHelper.isWater(par1World.getBlockTypeIdAt(var7, par4 - 1, var9 - 1)) || BlockHelper.isWater(par1World.getBlockTypeIdAt(var7, par4 - 1, var9 + 1)))) {
				int var10 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);

				for (int var11 = 0; var11 < var10; ++var11) {
					if (BlockHelper.canBlockStay(par1World.getBlockAt(var7, var8 + var11, var9), Mat.SugarCane)) {
						par1World.getBlockAt(var7, var8 + var11, var9).setTypeIdAndData(Mat.SugarCane.id, (byte) 0, false);
					}
				}
			}
		}

		return true;
	}
}