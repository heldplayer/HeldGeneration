package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTaiga2 extends WorldGenerator {
	public WorldGenTaiga2(boolean par1) {
		super(par1);
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var6 = par2Random.nextInt(4) + 6;
		int var7 = 1 + par2Random.nextInt(2);
		int var8 = var6 - var7;
		int var9 = 2 + par2Random.nextInt(2);
		boolean var10 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 256) {
			int var11;
			int var13;
			int var15;
			int var21;

			for (var11 = par4; var11 <= par4 + 1 + var6 && var10; ++var11) {
				if (var11 - par4 < var7) {
					var21 = 0;
				} else {
					var21 = var9;
				}

				for (var13 = par3 - var21; var13 <= par3 + var21 && var10; ++var13) {
					for (int var14 = par5 - var21; var14 <= par5 + var21 && var10; ++var14) {
						if (var11 >= 0 && var11 < 256) {
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

				if ((var11 == Mat.Grass.id || var11 == Mat.Dirt.id) && par4 < 256 - var6 - 1) {
					this.setBlock(par1World, par3, par4 - 1, par5, Mat.Dirt.id);
					var21 = par2Random.nextInt(2);
					var13 = 1;
					byte var22 = 0;
					int var17;
					int var16;

					for (var15 = 0; var15 <= var8; ++var15) {
						var16 = par4 + var6 - var15;

						for (var17 = par3 - var21; var17 <= par3 + var21; ++var17) {
							int var18 = var17 - par3;

							for (int var19 = par5 - var21; var19 <= par5 + var21; ++var19) {
								int var20 = var19 - par5;

								if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !BlockHelper.isOpaqueCube(par1World.getBlockTypeIdAt(var17, var16, var19))) {
									this.setBlockAndMetadata(par1World, var17, var16, var19, Mat.Leaves.id, 1);
								}
							}
						}

						if (var21 >= var13) {
							var21 = var22;
							var22 = 1;
							++var13;

							if (var13 > var9) {
								var13 = var9;
							}
						} else {
							++var21;
						}
					}

					var15 = par2Random.nextInt(3);

					for (var16 = 0; var16 < var6 - var15; ++var16) {
						var17 = par1World.getBlockTypeIdAt(par3, par4 + var16, par5);

						if (var17 == 0 || var17 == Mat.Leaves.id) {
							this.setBlockAndMetadata(par1World, par3, par4 + var16, par5, Mat.Log.id, 1);
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