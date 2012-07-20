package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenForest extends WorldGenerator {
	public WorldGenForest(boolean par1) {
		super(par1);
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var6 = par2Random.nextInt(3) + 5;
		boolean var7 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 256) {
			int var8;
			int var10;
			int var11;
			int var12;

			for (var8 = par4; var8 <= par4 + 1 + var6; ++var8) {
				byte var9 = 1;

				if (var8 == par4) {
					var9 = 0;
				}

				if (var8 >= par4 + 1 + var6 - 2) {
					var9 = 2;
				}

				for (var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10) {
					for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11) {
						if (var8 >= 0 && var8 < 256) {
							var12 = par1World.getBlockTypeIdAt(var10, var8, var11);

							if (var12 != 0 && var12 != Mat.Leaves.id) {
								var7 = false;
							}
						} else {
							var7 = false;
						}
					}
				}
			}

			if (!var7) {
				return false;
			} else {
				var8 = par1World.getBlockTypeIdAt(par3, par4 - 1, par5);

				if ((var8 == Mat.Grass.id || var8 == Mat.Dirt.id) && par4 < 256 - var6 - 1) {
					this.setBlock(par1World, par3, par4 - 1, par5, Mat.Dirt.id);
					int var16;

					for (var16 = par4 - 3 + var6; var16 <= par4 + var6; ++var16) {
						var10 = var16 - (par4 + var6);
						var11 = 1 - var10 / 2;

						for (var12 = par3 - var11; var12 <= par3 + var11; ++var12) {
							int var13 = var12 - par3;

							for (int var14 = par5 - var11; var14 <= par5 + var11; ++var14) {
								int var15 = var14 - par5;

								if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || par2Random.nextInt(2) != 0 && var10 != 0) && !BlockHelper.isOpaqueCube(par1World.getBlockTypeIdAt(var12, var16, var14))) {
									this.setBlockAndMetadata(par1World, var12, var16, var14, Mat.Leaves.id, 2);
								}
							}
						}
					}

					for (var16 = 0; var16 < var6; ++var16) {
						var10 = par1World.getBlockTypeIdAt(par3, par4 + var16, par5);

						if (var10 == 0 || var10 == Mat.Leaves.id) {
							this.setBlockAndMetadata(par1World, par3, par4 + var16, par5, Mat.Log.id, 2);
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