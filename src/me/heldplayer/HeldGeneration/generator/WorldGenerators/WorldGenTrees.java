package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTrees extends WorldGenerator {
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** Indicator that the tree generator needs to grown vines on the trees. */
	private final boolean growVines;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;

	public WorldGenTrees(boolean par1) {
		this(par1, 4, 0, 0, false);
	}

	public WorldGenTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
		super(par1);
		this.minTreeHeight = par2;
		this.metaWood = par3;
		this.metaLeaves = par4;
		this.growVines = par5;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int var6 = par2Random.nextInt(3) + this.minTreeHeight;
		boolean var7 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 256) {
			int var8;
			byte var9;
			int var11;
			int var12;

			for (var8 = par4; var8 <= par4 + 1 + var6; ++var8) {
				var9 = 1;

				if (var8 == par4) {
					var9 = 0;
				}

				if (var8 >= par4 + 1 + var6 - 2) {
					var9 = 2;
				}

				for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10) {
					for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11) {
						if (var8 >= 0 && var8 < 256) {
							var12 = par1World.getBlockTypeIdAt(var10, var8, var11);

							if (var12 != 0 && var12 != Mat.Leaves.id && var12 != Mat.Grass.id && var12 != Mat.Dirt.id && var12 != Mat.Log.id) {
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
					var9 = 3;
					byte var18 = 0;
					int var13;
					int var14;
					int var15;

					for (var11 = par4 - var9 + var6; var11 <= par4 + var6; ++var11) {
						var12 = var11 - (par4 + var6);
						var13 = var18 + 1 - var12 / 2;

						for (var14 = par3 - var13; var14 <= par3 + var13; ++var14) {
							var15 = var14 - par3;

							for (int var16 = par5 - var13; var16 <= par5 + var13; ++var16) {
								int var17 = var16 - par5;

								if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0) && !BlockHelper.isOpaqueCube(par1World.getBlockTypeIdAt(var14, var11, var16))) {
									this.setBlockAndMetadata(par1World, var14, var11, var16, Mat.Leaves.id, this.metaLeaves);
								}
							}
						}
					}

					for (var11 = 0; var11 < var6; ++var11) {
						var12 = par1World.getBlockTypeIdAt(par3, par4 + var11, par5);

						if (var12 == 0 || var12 == Mat.Leaves.id) {
							this.setBlockAndMetadata(par1World, par3, par4 + var11, par5, Mat.Log.id, this.metaWood);

							if (this.growVines && var11 > 0) {
								if (par2Random.nextInt(3) > 0 && par1World.getBlockTypeIdAt(par3 - 1, par4 + var11, par5) == 0) {
									this.setBlockAndMetadata(par1World, par3 - 1, par4 + var11, par5, Mat.Vine.id, 8);
								}

								if (par2Random.nextInt(3) > 0 && par1World.getBlockTypeIdAt(par3 + 1, par4 + var11, par5) == 0) {
									this.setBlockAndMetadata(par1World, par3 + 1, par4 + var11, par5, Mat.Vine.id, 2);
								}

								if (par2Random.nextInt(3) > 0 && par1World.getBlockTypeIdAt(par3, par4 + var11, par5 - 1) == 0) {
									this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 - 1, Mat.Vine.id, 1);
								}

								if (par2Random.nextInt(3) > 0 && par1World.getBlockTypeIdAt(par3, par4 + var11, par5 + 1) == 0) {
									this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 + 1, Mat.Vine.id, 4);
								}
							}
						}
					}

					if (this.growVines) {
						for (var11 = par4 - 3 + var6; var11 <= par4 + var6; ++var11) {
							var12 = var11 - (par4 + var6);
							var13 = 2 - var12 / 2;

							for (var14 = par3 - var13; var14 <= par3 + var13; ++var14) {
								for (var15 = par5 - var13; var15 <= par5 + var13; ++var15) {
									if (par1World.getBlockTypeIdAt(var14, var11, var15) == Mat.Leaves.id) {
										if (par2Random.nextInt(4) == 0 && par1World.getBlockTypeIdAt(var14 - 1, var11, var15) == 0) {
											this.func_48198_a(par1World, var14 - 1, var11, var15, 8);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockTypeIdAt(var14 + 1, var11, var15) == 0) {
											this.func_48198_a(par1World, var14 + 1, var11, var15, 2);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockTypeIdAt(var14, var11, var15 - 1) == 0) {
											this.func_48198_a(par1World, var14, var11, var15 - 1, 1);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockTypeIdAt(var14, var11, var15 + 1) == 0) {
											this.func_48198_a(par1World, var14, var11, var15 + 1, 4);
										}
									}
								}
							}
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

	private void func_48198_a(World par1World, int par2, int par3, int par4, int par5) {
		this.setBlockAndMetadata(par1World, par2, par3, par4, Mat.Vine.id, par5);
		int var6 = 4;

		while (true) {
			--par3;

			if (par1World.getBlockTypeIdAt(par2, par3, par4) != 0 || var6 <= 0) {
				return;
			}

			this.setBlockAndMetadata(par1World, par2, par3, par4, Mat.Vine.id, par5);
			--var6;
		}
	}
}