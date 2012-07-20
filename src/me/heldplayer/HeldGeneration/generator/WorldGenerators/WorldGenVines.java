package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Direction;
import me.heldplayer.HeldGeneration.helpers.Facing;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenVines extends WorldGenerator {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var6 = par3;

		for (int var7 = par5; par4 < 128; ++par4) {
			if (par1World.getBlockTypeIdAt(par3, par4, par5) == 0) {
				for (int var8 = 2; var8 <= 5; ++var8) {
					if (BlockHelper.canPlcaeVineOnSide(par1World.getBlockAt(par3, par4, par5), var8)) {
						par1World.getBlockAt(par3, par4, par5).setTypeIdAndData(Mat.Vine.id, (byte) (1 << Direction.vineGrowth[Facing.faceToSide[var8]]), false);
						break;
					}
				}
			} else {
				par3 = var6 + par2Random.nextInt(4) - par2Random.nextInt(4);
				par5 = var7 + par2Random.nextInt(4) - par2Random.nextInt(4);
			}
		}

		return true;
	}
}