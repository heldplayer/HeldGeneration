package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTaiga1 extends WorldGenerator {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var6 = par2Random.nextInt(5) + 7;
		int var7 = var6 - par2Random.nextInt(2) - 3;
		int var8 = var6 - var7;
		int var9 = 1 + par2Random.nextInt(var8 + 1);
		boolean var10 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 128) {
			int var11;
			int var13;
			int var14;
			int var15;
			int var18;

			for (var11 = par4; var11 <= par4 + 1 + var6 && var10; ++var11) {
				if (var11 - par4 < var7) {
					var18 = 0;
				} else {
					var18 = var9;
				}

				for (var13 = par3 - var18; var13 <= par3 + var18 && var10; ++var13) {
					for (var14 = par5 - var18; var14 <= par5 + var18 && var10; ++var14) {
						if (var11 >= 0 && var11 < 128) {
							var15 = par1World.getBlockTypeIdAt(var13, var11, var14);

							if (var15 != 0 && var15 != Mat.Leaves.id) {
								var10 = false;
							}
						} else {
							var10 = false;
						}
					}
				}
			}

			if (!var10) {
				return false;
			} else {
				var11 = par1World.getBlockTypeIdAt(par3, par4 - 1, par5);

				if ((var11 == Mat.Grass.id || var11 == Mat.Dirt.id) && par4 < 128 - var6 - 1) {
					this.setBlock(par1World, par3, par4 - 1, par5, Mat.Dirt.id);
					var18 = 0;

					for (var13 = par4 + var6; var13 >= par4 + var7; --var13) {
						for (var14 = par3 - var18; var14 <= par3 + var18; ++var14) {
							var15 = var14 - par3;

							for (int var16 = par5 - var18; var16 <= par5 + var18; ++var16) {
								int var17 = var16 - par5;

								if ((Math.abs(var15) != var18 || Math.abs(var17) != var18 || var18 <= 0) && !BlockHelper.isOpaqueCube(par1World.getBlockTypeIdAt(var14, var13, var16))) {
									this.setBlockAndMetadata(par1World, var14, var13, var16, Mat.Leaves.id, 1);
								}
							}
						}

						if (var18 >= 1 && var13 == par4 + var7 + 1) {
							--var18;
						} else if (var18 < var9) {
							++var18;
						}
					}

					for (var13 = 0; var13 < var6 - 1; ++var13) {
						var14 = par1World.getBlockTypeIdAt(par3, par4 + var13, par5);

						if (var14 == 0 || var14 == Mat.Leaves.id) {
							this.setBlockAndMetadata(par1World, par3, par4 + var13, par5, Mat.Log.id, 1);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}