package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenClay extends WorldGenerator {
	/** The block ID for clay. */
	private int clayBlockId;

	/** The number of blocks to generate. */
	private int numberOfBlocks;

	public WorldGenClay(int numberOfBlocks) {
		this.clayBlockId = Mat.Clay.id;
		this.numberOfBlocks = numberOfBlocks;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		if (!BlockHelper.isWater(par1World.getBlockTypeIdAt(par3, par4, par5))) {
			return false;
		} else {
			int var6 = par2Random.nextInt(this.numberOfBlocks - 2) + 2;
			byte var7 = 1;

			for (int var8 = par3 - var6; var8 <= par3 + var6; ++var8) {
				for (int var9 = par5 - var6; var9 <= par5 + var6; ++var9) {
					int var10 = var8 - par3;
					int var11 = var9 - par5;

					if (var10 * var10 + var11 * var11 <= var6 * var6) {
						for (int var12 = par4 - var7; var12 <= par4 + var7; ++var12) {
							int var13 = par1World.getBlockTypeIdAt(var8, var12, var9);

							if (var13 == Mat.Dirt.id || var13 == Mat.Clay.id) {
								par1World.getBlockAt(var8, var12, var9).setTypeIdAndData(this.clayBlockId, (byte) 0, false);
							}
						}
					}
				}
			}

			return true;
		}
	}
}