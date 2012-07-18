package me.heldplayer.HeldGeneration.generator.MapGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.generator.ChunkProvider;
import me.heldplayer.HeldGeneration.helpers.BiomeHelp;
import me.heldplayer.HeldGeneration.helpers.MathHelper;

import org.bukkit.World;

public class MapGenRavine extends MapGenBase {
	private float[] field_35627_a = new float[1024];

	protected void generateRavine(long seed, int cx, int cz, byte[] chunkBlocks, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17, ChunkProvider provider) {
		Random var19 = new Random(seed);
		double var20 = (cx * 16 + 8);
		double var22 = (cz * 16 + 8);
		float var24 = 0.0F;
		float var25 = 0.0F;

		if (par16 <= 0) {
			int var26 = this.range * 16 - 16;
			par16 = var26 - var19.nextInt(var26 / 4);
		}

		boolean var54 = false;

		if (par15 == -1) {
			par15 = par16 / 2;
			var54 = true;
		}

		float var27 = 1.0F;

		for (int var28 = 0; var28 < 128; ++var28) {
			if (var28 == 0 || var19.nextInt(3) == 0) {
				var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
			}

			this.field_35627_a[var28] = var27 * var27;
		}

		for (; par15 < par16; ++par15) {
			double var53 = 1.5D + (MathHelper.sin(par15 * (float) Math.PI / par16) * par12 * 1.0F);
			double var30 = var53 * par17;
			var53 *= var19.nextFloat() * 0.25D + 0.75D;
			var30 *= var19.nextFloat() * 0.25D + 0.75D;
			float var32 = MathHelper.cos(par14);
			float var33 = MathHelper.sin(par14);
			par6 += (MathHelper.cos(par13) * var32);
			par8 += var33;
			par10 += (MathHelper.sin(par13) * var32);
			par14 *= 0.7F;
			par14 += var25 * 0.05F;
			par13 += var24 * 0.05F;
			var25 *= 0.8F;
			var24 *= 0.5F;
			var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
			var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;

			if (var54 || var19.nextInt(4) != 0) {
				double var34 = par6 - var20;
				double var36 = par10 - var22;
				double var38 = (par16 - par15);
				double var40 = (par12 + 2.0F + 16.0F);

				if (var34 * var34 + var36 * var36 - var38 * var38 > var40 * var40) {
					return;
				}

				if (par6 >= var20 - 16.0D - var53 * 2.0D && par10 >= var22 - 16.0D - var53 * 2.0D && par6 <= var20 + 16.0D + var53 * 2.0D && par10 <= var22 + 16.0D + var53 * 2.0D) {
					int var56 = MathHelper.floor_double(par6 - var53) - cx * 16 - 1;
					int var35 = MathHelper.floor_double(par6 + var53) - cx * 16 + 1;
					int var55 = MathHelper.floor_double(par8 - var30) - 1;
					int var37 = MathHelper.floor_double(par8 + var30) + 1;
					int var57 = MathHelper.floor_double(par10 - var53) - cz * 16 - 1;
					int var39 = MathHelper.floor_double(par10 + var53) - cz * 16 + 1;

					if (var56 < 0) {
						var56 = 0;
					}

					if (var35 > 16) {
						var35 = 16;
					}

					if (var55 < 1) {
						var55 = 1;
					}

					if (var37 > 120) {
						var37 = 120;
					}

					if (var57 < 0) {
						var57 = 0;
					}

					if (var39 > 16) {
						var39 = 16;
					}

					boolean var58 = false;
					int var41;
					int var44;

					for (var41 = var56; !var58 && var41 < var35; ++var41) {
						for (int var42 = var57; !var58 && var42 < var39; ++var42) {
							for (int var43 = var37 + 1; !var58 && var43 >= var55 - 1; --var43) {
								var44 = (var41 * 16 + var42) * 128 + var43;

								if (var43 >= 0 && var43 < 128) {
									if (chunkBlocks[var44] == 8 || chunkBlocks[var44] == 9) {
										var58 = true;
									}

									if (var43 != var55 - 1 && var41 != var56 && var41 != var35 - 1 && var42 != var57 && var42 != var39 - 1) {
										var43 = var55;
									}
								}
							}
						}
					}

					if (!var58) {
						for (var41 = var56; var41 < var35; ++var41) {
							double var59 = ((var41 + cx * 16) + 0.5D - par6) / var53;

							for (var44 = var57; var44 < var39; ++var44) {
								double var45 = ((var44 + cz * 16) + 0.5D - par10) / var53;
								int var47 = (var41 * 16 + var44) * 128 + var37;
								boolean var48 = false;

								if (var59 * var59 + var45 * var45 < 1.0D) {
									for (int var49 = var37 - 1; var49 >= var55; --var49) {
										double var50 = (var49 + 0.5D - par8) / var30;

										if ((var59 * var59 + var45 * var45) * this.field_35627_a[var49] + var50 * var50 / 6.0D < 1.0D) {
											byte var52 = chunkBlocks[var47];

											if (var52 == 2) {
												var48 = true;
											}

											if (var52 == 1 || var52 == 3 || var52 == 2) {
												if (var49 < 10) {
													chunkBlocks[var47] = (byte) 10;
												} else {
													chunkBlocks[var47] = 0;

													if (var48 && chunkBlocks[var47 - 1] == 3) {
														chunkBlocks[var47 - 1] = (byte) BiomeHelp.getTopBlock(provider.generator.helper.getBiomeAt(var41 + cx * 16, var44 + cz * 16));
													}
												}
											}
										}

										--var47;
									}
								}
							}
						}

						if (var54) {
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Recursively called by generate() (generate) and optionally by itself.
	 */
	@Override
	protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte, ChunkProvider provider) {
		if (this.rand.nextInt(50) == 0) {
			double var7 = (par2 * 16 + this.rand.nextInt(16));
			double var9 = (this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
			double var11 = (par3 * 16 + this.rand.nextInt(16));
			byte var13 = 1;

			for (int var14 = 0; var14 < var13; ++var14) {
				float var15 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float var16 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float var17 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
				generateRavine(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var7, var9, var11, var17, var15, var16, 0, 0, 3.0D, provider);
			}
		}
	}
}
