package me.heldplayer.HeldGeneration.generator.MapGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.BiomeHelp;
import me.heldplayer.HeldGeneration.MathHelper;
import me.heldplayer.HeldGeneration.generator.ChunkProviderGenerate;

import org.bukkit.World;

public class MapGenCaves extends MapGenBase {
	/**
	 * Generates a larger initial cave node than usual. Called 25% of the time.
	 */
	protected void generateLargeCaveNode(long seed, int cx, int cz, byte[] chunkBlocks, double startX, double startY, double startZ, ChunkProviderGenerate provider) {
		generateCaveNode(seed, cx, cz, chunkBlocks, startX, startY, startZ, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D, provider);
	}

	/**
	 * Generates a node in the current cave system recursion tree.
	 */
	protected void generateCaveNode(long seed, int cx, int cz, byte[] chunkBlocks, double startX, double startY, double startZ, float exitX, float exitY, float exitZ, int par15, int par16, double par17, ChunkProviderGenerate provider) {
		double centerChunkX = (cx * 16 + 8);
		double centerChunkZ = (cz * 16 + 8);
		float var23 = 0.0F;
		float var24 = 0.0F;
		Random rand = new Random(seed);

		if (par16 <= 0) {
			int var26 = this.range * 16 - 16;
			par16 = var26 - rand.nextInt(var26 / 4);
		}

		boolean var54 = false;

		if (par15 == -1) {
			par15 = par16 / 2;
			var54 = true;
		}

		int var27 = rand.nextInt(par16 / 2) + par16 / 4;

		for (boolean var28 = rand.nextInt(6) == 0; par15 < par16; ++par15) {
			double var29 = 1.5D + (MathHelper.sin(par15 * (float) Math.PI / par16) * exitX * 1.0F);
			double var31 = var29 * par17;
			float var33 = MathHelper.cos(exitZ);
			float var34 = MathHelper.sin(exitZ);
			startX += (MathHelper.cos(exitY) * var33);
			startY += var34;
			startZ += (MathHelper.sin(exitY) * var33);

			if (var28) {
				exitZ *= 0.92F;
			} else {
				exitZ *= 0.7F;
			}

			exitZ += var24 * 0.1F;
			exitY += var23 * 0.1F;
			var24 *= 0.9F;
			var23 *= 0.75F;
			var24 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
			var23 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;

			if (!var54 && par15 == var27 && exitX > 1.0F && par16 > 0) {
				generateCaveNode(rand.nextLong(), cx, cz, chunkBlocks, startX, startY, startZ, rand.nextFloat() * 0.5F + 0.5F, exitY - ((float) Math.PI / 2F), exitZ / 3.0F, par15, par16, 1.0D, provider);
				generateCaveNode(rand.nextLong(), cx, cz, chunkBlocks, startX, startY, startZ, rand.nextFloat() * 0.5F + 0.5F, exitY + ((float) Math.PI / 2F), exitZ / 3.0F, par15, par16, 1.0D, provider);
				return;
			}

			if (var54 || rand.nextInt(4) != 0) {
				double var35 = startX - centerChunkX;
				double var37 = startZ - centerChunkZ;
				double var39 = (par16 - par15);
				double var41 = (exitX + 2.0F + 16.0F);

				if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
					return;
				}

				if (startX >= centerChunkX - 16.0D - var29 * 2.0D && startZ >= centerChunkZ - 16.0D - var29 * 2.0D && startX <= centerChunkX + 16.0D + var29 * 2.0D && startZ <= centerChunkZ + 16.0D + var29 * 2.0D) {
					int var55 = MathHelper.floor_double(startX - var29) - cx * 16 - 1;
					int var36 = MathHelper.floor_double(startX + var29) - cx * 16 + 1;
					int var57 = MathHelper.floor_double(startY - var31) - 1;
					int var38 = MathHelper.floor_double(startY + var31) + 1;
					int var56 = MathHelper.floor_double(startZ - var29) - cz * 16 - 1;
					int var40 = MathHelper.floor_double(startZ + var29) - cz * 16 + 1;

					if (var55 < 0) {
						var55 = 0;
					}

					if (var36 > 16) {
						var36 = 16;
					}

					if (var57 < 1) {
						var57 = 1;
					}

					if (var38 > 120) {
						var38 = 120;
					}

					if (var56 < 0) {
						var56 = 0;
					}

					if (var40 > 16) {
						var40 = 16;
					}

					boolean var58 = false;
					int var42;
					int var45;

					for (var42 = var55; !var58 && var42 < var36; ++var42) {
						for (int var43 = var56; !var58 && var43 < var40; ++var43) {
							for (int var44 = var38 + 1; !var58 && var44 >= var57 - 1; --var44) {
								var45 = (var42 * 16 + var43) * 128 + var44;

								if (var44 >= 0 && var44 < 128) {
									if (chunkBlocks[var45] == 0x8 || chunkBlocks[var45] == 0x9) {
										var58 = true;
									}

									if (var44 != var57 - 1 && var42 != var55 && var42 != var36 - 1 && var43 != var56 && var43 != var40 - 1) {
										var44 = var57;
									}
								}
							}
						}
					}

					if (!var58) {
						for (var42 = var55; var42 < var36; ++var42) {
							double var59 = ((var42 + cx * 16) + 0.5D - startX) / var29;

							for (var45 = var56; var45 < var40; ++var45) {
								double var46 = ((var45 + cz * 16) + 0.5D - startZ) / var29;
								int var48 = (var42 * 16 + var45) * 128 + var38;
								boolean var49 = false;

								if (var59 * var59 + var46 * var46 < 1.0D) {
									for (int var50 = var38 - 1; var50 >= var57; --var50) {
										double var51 = (var50 + 0.5D - startY) / var31;

										if (var51 > -0.7D && var59 * var59 + var51 * var51 + var46 * var46 < 1.0D) {
											byte var53 = chunkBlocks[var48];

											if (var53 == 0x2) {
												var49 = true;
											}

											if (var53 == 0x1 || var53 == 0x3 || var53 == 0x2) {
												if (var50 < 10) {
													chunkBlocks[var48] = (byte) 0xa;
												} else {
													chunkBlocks[var48] = 0;

													if (var49 && chunkBlocks[var48 - 1] == 0x3) {
														chunkBlocks[var48 - 1] = (byte) BiomeHelp.getTopBlock(provider.helper.getBiomeAt(var42 + cx * 16, var45 + cz * 16));
													}
												}
											}
										}

										--var48;
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
	protected void recursiveGenerate(World world, int ccx, int ccz, int cx, int cz, byte[] chunkBlocks, ChunkProviderGenerate provider) {
		int totalStarts = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);

		if (this.rand.nextInt(15) != 0) {
			totalStarts = 0;
		}

		for (int currentStart = 0; currentStart < totalStarts; ++currentStart) {
			double startX = (ccx * 16 + this.rand.nextInt(16));
			double startY = this.rand.nextInt(this.rand.nextInt(120) + 8);
			double startZ = (ccz * 16 + this.rand.nextInt(16));
			int exits = 1;

			if (this.rand.nextInt(4) == 0) {
				generateLargeCaveNode(this.rand.nextLong(), cx, cz, chunkBlocks, startX, startY, startZ, provider);
				exits += this.rand.nextInt(4);
			}

			for (int exit = 0; exit < exits; ++exit) {
				float exitX = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float exitY = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float exitZ = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0) {
					exitZ *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
				}

				generateCaveNode(this.rand.nextLong(), cx, cz, chunkBlocks, startX, startY, startZ, exitZ, exitX, exitY, 0, 0, 1.0D, provider);
			}
		}
	}
}