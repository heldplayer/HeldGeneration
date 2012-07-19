package me.heldplayer.HeldGeneration.generator;

import java.util.Random;

import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenBase;
import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenCaves;
import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenRavine;
import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenVillage;
import me.heldplayer.HeldGeneration.helpers.BiomeHelp;
import me.heldplayer.HeldGeneration.helpers.BiomeHelper;
import me.heldplayer.HeldGeneration.helpers.MathHelper;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;

public class ChunkProviderGenerate {
	/** RNG. */
	private Random rand;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen1;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen2;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen3;

	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen4;

	/** A NoiseGeneratorOctaves used in generating terrain */
	public NoiseGeneratorOctaves noiseGen5;

	/** A NoiseGeneratorOctaves used in generating terrain */
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;

	/** Holds the overall noise array used in chunk generation */
	private double[] noiseArray;
	private double[] stoneNoise = new double[256];

	/** The biomes that are used to generate the chunk */
	private Biome[] biomesForGeneration;

	/** A double array that hold terrain noise from noiseGen3 */
	double[] noise3;

	/** A double array that hold terrain noise */
	double[] noise1;

	/** A double array that hold terrain noise from noiseGen2 */
	double[] noise2;

	/** A double array that hold terrain noise from noiseGen5 */
	double[] noise5;

	/** A double array that holds terrain noise from noiseGen6 */
	double[] noise6;
	float[] field_35388_l;
	int[][] field_914_i = new int[32][32];

	public BiomeHelper helper;
	private long seed;

	private MapGenBase caveGenerator = new MapGenCaves();

	/** Holds ravine generator */
	private MapGenBase ravineGenerator = new MapGenRavine();

	/** Holds Village Generator */
	public MapGenVillage villageGenerator = new MapGenVillage(0, this.helper = new BiomeHelper());

	private ChunkProvider provider;

	public ChunkProviderGenerate(ChunkProvider provider) {
		this.provider = provider;
	}

	private void setupNoiseGens() {
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);

		this.helper.setSeed(this.seed);
	}

	public byte[] generate(World world, Random random, int cx, int cz, BiomeGrid biomes) {
		if (this.rand == null) {
			this.rand = new Random(world.getSeed());
			this.seed = world.getSeed();
			setupNoiseGens();
		}

		this.rand = new Random(world.getSeed());

		byte[] chunkBlocks = new byte[65536];

		generateTerrain(cx, cz, chunkBlocks);
		this.biomesForGeneration = this.helper.loadBlockGeneratorData(this.biomesForGeneration, cx * 16, cz * 16, 16, 16);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				biomes.setBiome(x, z, this.biomesForGeneration[x + z * 16]);
			}
		}

		replaceBlocksForBiome(cx, cz, chunkBlocks, this.biomesForGeneration);

		this.caveGenerator.generate(world, cx, cz, chunkBlocks, provider);
		this.ravineGenerator.generate(world, cx, cz, chunkBlocks, provider);

		if (world.canGenerateStructures()) {
			//mineshaftGenerator.generate(world, cx, cz, chunkBlocks, provider);
			//this.villageGenerator.generate(world, cx, cz, chunkBlocks, provider);
			//strongholdGenerator.generate(world, cx, cz, chunkBlocks, provider);
		}

		return chunkBlocks;
	}

	private void generateTerrain(int cx, int cz, byte[] par3ArrayOfByte) {
		byte var4 = 4;
		byte var5 = 16;
		byte var6 = 63;
		int var7 = var4 + 1;
		byte var8 = 17;
		int var9 = var4 + 1;
		this.biomesForGeneration = this.helper.getBiomesForGeneration(this.biomesForGeneration, cx * 4 - 2, cz * 4 - 2, var7 + 5, var9 + 5);
		this.noiseArray = initializeNoiseField(this.noiseArray, cx * var4, 0, cz * var4, var7, var8, var9);

		for (int var10 = 0; var10 < var4; ++var10) {
			for (int var11 = 0; var11 < var4; ++var11) {
				for (int var12 = 0; var12 < var5; ++var12) {
					double var13 = 0.125D;
					double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
					double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
					double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
					double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
					double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
					double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
					double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
					double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

					for (int var31 = 0; var31 < 8; ++var31) {
						double var32 = 0.25D;
						double var34 = var15;
						double var36 = var17;
						double var38 = (var19 - var15) * var32;
						double var40 = (var21 - var17) * var32;

						for (int var42 = 0; var42 < 4; ++var42) {
							int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
							short var44 = 128;
							var43 -= var44;
							double var45 = 0.25D;
							double var49 = (var36 - var34) * var45;
							double var47 = var34 - var49;

							for (int var51 = 0; var51 < 4; ++var51) {
								if ((var47 += var49) > 0.0D) {
									par3ArrayOfByte[var43 += var44] = 0x1;
								} else if (var12 * 8 + var31 < var6) {
									par3ArrayOfByte[var43 += var44] = 0x9;
								} else {
									par3ArrayOfByte[var43 += var44] = 0;
								}
							}

							var34 += var38;
							var36 += var40;
						}

						var15 += var23;
						var17 += var25;
						var19 += var27;
						var21 += var29;
					}
				}
			}
		}
	}

	/**
	 * generates a subset of the level's terrain data. Takes 7 arguments: the
	 * [empty] noise array, the position, and the
	 * size.
	 */
	private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
		if (par1ArrayOfDouble == null) {
			par1ArrayOfDouble = new double[par5 * par6 * par7];
		}

		if (this.field_35388_l == null) {
			this.field_35388_l = new float[25];

			for (int var8 = -2; var8 <= 2; ++var8) {
				for (int var9 = -2; var9 <= 2; ++var9) {
					float var10 = 10.0F / MathHelper.sqrt_float((var8 * var8 + var9 * var9) + 0.2F);
					this.field_35388_l[var8 + 2 + (var9 + 2) * 5] = var10;
				}
			}
		}

		double var44 = 684.412D;
		double var45 = 684.412D;
		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var44, var45, var44);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var44, var45, var44);
		int var12 = 0;
		int var13 = 0;

		for (int var14 = 0; var14 < par5; ++var14) {
			for (int var15 = 0; var15 < par7; ++var15) {
				float var16 = 0.0F;
				float var17 = 0.0F;
				float var18 = 0.0F;
				byte var19 = 2;
				Biome var20 = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];

				for (int var21 = -var19; var21 <= var19; ++var21) {
					for (int var22 = -var19; var22 <= var19; ++var22) {
						Biome var23 = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5)];

						float var24 = this.field_35388_l[var21 + 2 + (var22 + 2) * 5] / (BiomeHelp.getMinHeight(var23) + 2.0F);

						if (BiomeHelp.getMinHeight(var23) > BiomeHelp.getMinHeight(var20)) {
							var24 /= 2.0F;
						}

						var16 += BiomeHelp.getMaxHeight(var23) * var24;
						var17 += BiomeHelp.getMinHeight(var23) * var24;
						var18 += var24;
					}
				}

				var16 /= var18;
				var17 /= var18;
				var16 = var16 * 0.9F + 0.1F;
				var17 = (var17 * 4.0F - 1.0F) / 8.0F;
				double var47 = this.noise6[var13] / 8000.0D;

				if (var47 < 0.0D) {
					var47 = -var47 * 0.3D;
				}

				var47 = var47 * 3.0D - 2.0D;

				if (var47 < 0.0D) {
					var47 /= 2.0D;

					if (var47 < -1.0D) {
						var47 = -1.0D;
					}

					var47 /= 1.4D;
					var47 /= 2.0D;
				} else {
					if (var47 > 1.0D) {
						var47 = 1.0D;
					}

					var47 /= 8.0D;
				}

				++var13;

				for (int var46 = 0; var46 < par6; ++var46) {
					double var48 = var17;
					double var26 = var16;
					var48 += var47 * 0.2D;
					var48 = var48 * par6 / 16.0D;
					double var28 = par6 / 2.0D + var48 * 4.0D;
					double var30 = 0.0D;
					double var32 = (var46 - var28) * 12.0D * 128.0D / 128.0D / var26;

					if (var32 < 0.0D) {
						var32 *= 4.0D;
					}

					double var34 = this.noise1[var12] / 512.0D;
					double var36 = this.noise2[var12] / 512.0D;
					double var38 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;

					if (var38 < 0.0D) {
						var30 = var34;
					} else if (var38 > 1.0D) {
						var30 = var36;
					} else {
						var30 = var34 + (var36 - var34) * var38;
					}

					var30 -= var32;

					if (var46 > par6 - 4) {
						double var40 = ((var46 - (par6 - 4)) / 3.0F);
						var30 = var30 * (1.0D - var40) + -10.0D * var40;
					}

					par1ArrayOfDouble[var12] = var30;
					++var12;
				}
			}
		}

		return par1ArrayOfDouble;
	}

	/**
	 * Replaces the stone that was placed in with blocks that match the biome
	 */
	public void replaceBlocksForBiome(int cx, int cz, byte[] chunkBlocks, Biome[] biomes) {
		byte var5 = 63;
		double var6 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, cx * 16, cz * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int var8 = 0; var8 < 16; ++var8) {
			for (int var9 = 0; var9 < 16; ++var9) {
				Biome var10 = biomes[var9 + var8 * 16];
				float var11 = BiomeHelp.getTemperature(var10);
				int var12 = (int) (this.stoneNoise[var8 + var9 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var13 = -1;
				byte var14 = (byte) BiomeHelp.getTopBlock(var10);
				byte var15 = (byte) BiomeHelp.getFillerBlock(var10);

				for (int var16 = 127; var16 >= 0; --var16) {
					int var17 = (var9 * 16 + var8) * 128 + var16;

					if (var16 <= 0 + this.rand.nextInt(5)) {
						chunkBlocks[var17] = 7;
					} else {
						byte var18 = chunkBlocks[var17];

						if (var18 == 0) {
							var13 = -1;
						} else if (var18 == 1) {
							if (var13 == -1) {
								if (var12 <= 0) {
									var14 = 0;
									var15 = (byte) 1;
								} else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
									var14 = (byte) BiomeHelp.getTopBlock(var10);
									var15 = (byte) BiomeHelp.getFillerBlock(var10);
								}

								if (var16 < var5 && var14 == 0) {
									if (var11 < 0.15F) {
										var14 = (byte) 79;
									} else {
										var14 = (byte) 9;
									}
								}

								var13 = var12;

								if (var16 >= var5 - 1) {
									chunkBlocks[var17] = var14;
								} else {
									chunkBlocks[var17] = var15;
								}
							} else if (var13 > 0) {
								--var13;
								chunkBlocks[var17] = var15;

								if (var13 == 0 && var15 == 12) {
									var13 = this.rand.nextInt(4);
									var15 = (byte) 24;
								}
							}
						}
					}
				}
			}
		}
	}
}