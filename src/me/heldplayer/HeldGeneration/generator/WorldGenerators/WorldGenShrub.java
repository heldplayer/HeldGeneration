package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenShrub extends WorldGenerator {
	private int field_48197_a;
	private int field_48196_b;

	public WorldGenShrub(int par1, int par2) {
		this.field_48196_b = par1;
		this.field_48197_a = par2;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var15;

		for (; ((var15 = par1World.getBlockTypeIdAt(par3, par4, par5)) == 0 || var15 == Mat.Leaves.id) && par4 > 0; --par4) {
			;
		}

		int var7 = par1World.getBlockTypeIdAt(par3, par4, par5);

		if (var7 == Mat.Dirt.id || var7 == Mat.Grass.id) {
			++par4;
			this.setBlockAndMetadata(par1World, par3, par4, par5, Mat.Log.id, this.field_48196_b);

			for (int var8 = par4; var8 <= par4 + 2; ++var8) {
				int var9 = var8 - par4;
				int var10 = 2 - var9;

				for (int var11 = par3 - var10; var11 <= par3 + var10; ++var11) {
					int var12 = var11 - par3;

					for (int var13 = par5 - var10; var13 <= par5 + var10; ++var13) {
						int var14 = var13 - par5;

						if ((Math.abs(var12) != var10 || Math.abs(var14) != var10 || par2Random.nextInt(2) != 0) && !BlockHelper.isOpaqueCube(par1World.getBlockTypeIdAt(var11, var8, var13))) {
							this.setBlockAndMetadata(par1World, var11, var8, var13, Mat.Leaves.id, this.field_48197_a);
						}
					}
				}
			}
		}

		return true;
	}
}