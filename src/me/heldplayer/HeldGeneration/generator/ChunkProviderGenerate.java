package me.heldplayer.HeldGeneration.generator;

import java.util.Random;

import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenBase;
import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenCaves;
import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenRavine;
import me.heldplayer.HeldGeneration.helpers.BiomeHelp;
import me.heldplayer.HeldGeneration.helpers.BiomeHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;
import me.heldplayer.HeldGeneration.helpers.MathHelper;
import me.heldplayer.HeldGeneration.profiler.Profiler;

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

	public BiomeHelper helper = new BiomeHelper();
	private long seed;

	private MapGenBase caveGenerator = new MapGenCaves();

	/** Holds ravine generator */
	private MapGenBase ravineGenerator = new MapGenRavine();

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

	public byte[][] generate(World world, Random random, int cx, int cz, BiomeGrid biomes) {
		if (this.rand == null) {
			Profiler.startSection("setup");

			this.rand = new Random(world.getSeed());
			this.seed = world.getSeed();
			setupNoiseGens();

			Profiler.endSection();
		}

		this.rand = new Random(world.getSeed());

		Profiler.startSection("arrayAllocate");

		byte[][] chunkBlocks = new byte[16][4096];

		Profiler.endStartSection("generateTerrain");

		generateTerrain(cx, cz, chunkBlocks);

		Profiler.endStartSection("biomes");

		this.biomesForGeneration = this.helper.loadBlockGeneratorData(this.biomesForGeneration, cx * 16, cz * 16, 16, 16);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				biomes.setBiome(x, z, this.biomesForGeneration[x + z * 16]);
			}
		}

		Profiler.startSection("replaceBlocks");

		replaceBlocksForBiome(cx, cz, chunkBlocks, this.biomesForGeneration);

		Profiler.endSection();
		Profiler.endSection();

		return chunkBlocks;
	}

	private void generateTerrain(int cx, int cz, byte[][] chunkBytes) {
		byte varOf4 = 4;
		byte chunkSections = 16;
		byte seaLevel = 63;
		int varOf5No1 = varOf4 + 1;
		byte varOf17 = 17;
		int varOf5No2 = varOf4 + 1;
		this.biomesForGeneration = this.helper.getBiomesForGeneration(this.biomesForGeneration, cx * 4 - 2, cz * 4 - 2, varOf5No1 + 5, varOf5No2 + 5);
		this.noiseArray = initializeNoiseField(this.noiseArray, cx * varOf4, 0, cz * varOf4, varOf5No1, varOf17, varOf5No2);

		for (int sectionPosX = 0; sectionPosX < varOf4; ++sectionPosX) {
			for (int sectionPosZ = 0; sectionPosZ < varOf4; ++sectionPosZ) {
				for (int sectionPosY = 0; sectionPosY < chunkSections; ++sectionPosY) {
					double modifier1 = 0.125D;
					double noiseVal1 = this.noiseArray[((sectionPosX + 0) * varOf5No2 + sectionPosZ + 0) * varOf17 + sectionPosY + 0];
					double noiseVal2 = this.noiseArray[((sectionPosX + 0) * varOf5No2 + sectionPosZ + 1) * varOf17 + sectionPosY + 0];
					double noiseVal3 = this.noiseArray[((sectionPosX + 1) * varOf5No2 + sectionPosZ + 0) * varOf17 + sectionPosY + 0];
					double noiseVal4 = this.noiseArray[((sectionPosX + 1) * varOf5No2 + sectionPosZ + 1) * varOf17 + sectionPosY + 0];
					double noiseVal1Increment = (this.noiseArray[((sectionPosX + 0) * varOf5No2 + sectionPosZ + 0) * varOf17 + sectionPosY + 1] - noiseVal1) * modifier1;
					double noiseVal2Increment = (this.noiseArray[((sectionPosX + 0) * varOf5No2 + sectionPosZ + 1) * varOf17 + sectionPosY + 1] - noiseVal2) * modifier1;
					double noiseVal3Increment = (this.noiseArray[((sectionPosX + 1) * varOf5No2 + sectionPosZ + 0) * varOf17 + sectionPosY + 1] - noiseVal3) * modifier1;
					double noiseVal4Increment = (this.noiseArray[((sectionPosX + 1) * varOf5No2 + sectionPosZ + 1) * varOf17 + sectionPosY + 1] - noiseVal4) * modifier1;

					for (int relPosY = 0; relPosY < 8; ++relPosY) {
						double modifier2 = 0.25D;
						double noiseVal1Clone = noiseVal1;
						double noiseVal2Clone = noiseVal2;
						double noiseVal1Increment2 = (noiseVal3 - noiseVal1) * modifier2;
						double noiseVal2Increment2 = (noiseVal4 - noiseVal2) * modifier2;

						for (int relPosX = 0; relPosX < 4; ++relPosX) {
							//int index = relPosX + chuckPosX * 4 << 11 | 0 + chuckPosZ * 4 << 7 | chuckPosY * 8 + relPosY;
							//short worldHeight = 128;
							//index -= worldHeight;
							double modifier3 = 0.25D;
							double noiseValClone2 = (noiseVal2Clone - noiseVal1Clone) * modifier3;
							double var47 = noiseVal1Clone - noiseValClone2;

							// result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = chunkBlocks[(x * 16 + z) * 128 + y];
							for (int relPosZ = 0; relPosZ < 4; ++relPosZ) {
								if ((var47 += noiseValClone2) > 0.0D) {
									chunkBytes[(sectionPosY >> 1)][(((relPosY + sectionPosY * 8) & 0xF) << 8) | ((relPosZ + sectionPosZ * 4) << 4) | (relPosX + sectionPosX * 4)] = (byte) Mat.Stone.id;
									//chunkBytes[index += worldHeight] = (byte) Mat.Stone.id;
								} else if (sectionPosY * 8 + relPosY < seaLevel) {
									//chunkBytes[index += worldHeight] = (byte) Mat.WaterStill.id;
									chunkBytes[(sectionPosY >> 1)][(((relPosY + sectionPosY * 8) & 0xF) << 8) | ((relPosZ + sectionPosZ * 4) << 4) | (relPosX + sectionPosX * 4)] = (byte) Mat.WaterStill.id;
								} else {
									//chunkBytes[index += worldHeight] = 0;
									chunkBytes[(sectionPosY >> 1)][(((relPosY + sectionPosY * 8) & 0xF) << 8) | ((relPosZ + sectionPosZ * 4) << 4) | (relPosX + sectionPosX * 4)] = 0;
								}
							}

							noiseVal1Clone += noiseVal1Increment2;
							noiseVal2Clone += noiseVal2Increment2;
						}

						noiseVal1 += noiseVal1Increment;
						noiseVal2 += noiseVal2Increment;
						noiseVal3 += noiseVal3Increment;
						noiseVal4 += noiseVal4Increment;
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
	private double[] initializeNoiseField(double[] noiseArray, int par2, int par3, int par4, int par5, int par6, int par7) {
		if (noiseArray == null) {
			noiseArray = new double[par5 * par6 * par7];
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

					noiseArray[var12] = var30;
					++var12;
				}
			}
		}

		return noiseArray;
	}

	/**
	 * Replaces the stone that was placed in with blocks that match the biome
	 */
	public void replaceBlocksForBiome(int cx, int cz, byte[][] chunkBlocks, Biome[] biomes) {
		byte var5 = 63;
		double var6 = 0.03125D;
		this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, cx * 16, cz * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

		for (int posZ = 0; posZ < 16; ++posZ) {
			for (int posX = 0; posX < 16; ++posX) {
				Biome biome = biomes[posX + posZ * 16];
				float temperature = BiomeHelp.getTemperature(biome);
				int var12 = (int) (this.stoneNoise[posZ + posX * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var13 = -1;
				byte topBlock = (byte) BiomeHelp.getTopBlock(biome);
				byte fillerBlock = (byte) BiomeHelp.getFillerBlock(biome);

				// result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = chunkBlocks[(x * 16 + z) * 128 + y];
				for (int posY = 127; posY >= 0; --posY) {
					int index = (posX * 16 + posZ) * 128 + posY;

					if (posY <= 0 + this.rand.nextInt(5)) {
						//chunkBlocks[index] = (byte) Mat.Bedrock.id;
						chunkBlocks[posY >> 4][((posY & 0xF) << 8) | (posZ << 4) | posX] = (byte) Mat.Stone.id;
					} else {
						byte var18 = chunkBlocks[posY >> 4][((posY & 0xF) << 8) | (posZ << 4) | posX];

						if (var18 == 0) {
							var13 = -1;
						} else if (var18 == 1) {
							if (var13 == -1) {
								if (var12 <= 0) {
									topBlock = 0;
									fillerBlock = (byte) 1;
								} else if (posY >= var5 - 4 && posY <= var5 + 1) {
									topBlock = (byte) BiomeHelp.getTopBlock(biome);
									fillerBlock = (byte) BiomeHelp.getFillerBlock(biome);
								}

								if (posY < var5 && topBlock == 0) {
									if (temperature < 0.15F) {
										topBlock = (byte) Mat.Snow.id;
									} else {
										topBlock = (byte) Mat.WaterStill.id;
									}
								}

								var13 = var12;

								if (posY >= var5 - 1) {
									chunkBlocks[posY >> 4][((posY & 0xF) << 8) | (posZ << 4) | posX] = topBlock;
								} else {
									chunkBlocks[posY >> 4][((posY & 0xF) << 8) | (posZ << 4) | posX] = fillerBlock;
								}
							} else if (var13 > 0) {
								--var13;
								chunkBlocks[posY >> 4][((posY & 0xF) << 8) | (posZ << 4) | posX] = fillerBlock;

								if (var13 == 0 && fillerBlock == Mat.Sand.id) {
									var13 = this.rand.nextInt(4);
									fillerBlock = (byte) Mat.Sandstone.id;
								}
							}
						}
					}
				}
			}
		}
	}
}