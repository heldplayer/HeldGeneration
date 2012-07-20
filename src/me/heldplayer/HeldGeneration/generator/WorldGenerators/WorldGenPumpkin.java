package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenPumpkin extends WorldGenerator {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		for (int var6 = 0; var6 < 64; ++var6) {
			int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.getBlockTypeIdAt(var7, var8, var9) == 0 && par1World.getBlockTypeIdAt(var7, var8 - 1, var9) == Mat.Grass.id && BlockHelper.canBlockStay(par1World.getBlockAt(var7, var8, var9), Mat.Pumpkin)) {
				par1World.getBlockAt(var7, var8, var9).setTypeIdAndData(Mat.Pumpkin.id, (byte) par2Random.nextInt(4), false);
			}
		}

		return true;
	}
}