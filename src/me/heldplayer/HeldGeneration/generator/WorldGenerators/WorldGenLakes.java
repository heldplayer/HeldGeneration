package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;
import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class WorldGenLakes extends WorldGenerator {
	private int blockIndex;

	public WorldGenLakes(int typeId) {
		this.blockIndex = typeId;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		x -= 8;

		for (z -= 8; y > 5 && world.getBlockAt(x, y, z).getTypeId() == 0; --y) {
			;
		}

		if (y <= 4) {
			return false;
		} else {
			y -= 4;
			boolean[] lakeBlocks = new boolean[2048];
			int var7 = rand.nextInt(4) + 4;
			int counterRelPosX;

			for (counterRelPosX = 0; counterRelPosX < var7; ++counterRelPosX) {
				double initialRandX = rand.nextDouble() * 6.0D + 3.0D;
				double initialRandY = rand.nextDouble() * 4.0D + 2.0D;
				double initialRandZ = rand.nextDouble() * 6.0D + 3.0D;
				double randX = rand.nextDouble() * (16.0D - initialRandX - 2.0D) + 1.0D + initialRandX / 2.0D;
				double randY = rand.nextDouble() * (8.0D - initialRandY - 4.0D) + 2.0D + initialRandY / 2.0D;
				double randZ = rand.nextDouble() * (16.0D - initialRandZ - 2.0D) + 1.0D + initialRandZ / 2.0D;

				for (int relPosX = 1; relPosX < 15; ++relPosX) {
					for (int relPosZ = 1; relPosZ < 15; ++relPosZ) {
						for (int relPosY = 1; relPosY < 7; ++relPosY) {
							double resultX = (relPosX - randX) / (initialRandX / 2.0D);
							double resultY = (relPosY - randY) / (initialRandY / 2.0D);
							double resultZ = (relPosZ - randZ) / (initialRandZ / 2.0D);
							double resultRand = resultX * resultX + resultY * resultY + resultZ * resultZ;

							if (resultRand < 1.0D) {
								lakeBlocks[(relPosX * 16 + relPosZ) * 8 + relPosY] = true;
							}
						}
					}
				}
			}

			int relPosY;
			int relPosZ;
			boolean isSource;

			for (counterRelPosX = 0; counterRelPosX < 16; ++counterRelPosX) {
				for (relPosZ = 0; relPosZ < 16; ++relPosZ) {
					for (relPosY = 0; relPosY < 8; ++relPosY) {
						isSource = !lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + relPosY] && (counterRelPosX < 15 && lakeBlocks[((counterRelPosX + 1) * 16 + relPosZ) * 8 + relPosY] || counterRelPosX > 0 && lakeBlocks[((counterRelPosX - 1) * 16 + relPosZ) * 8 + relPosY] || relPosZ < 15 && lakeBlocks[(counterRelPosX * 16 + relPosZ + 1) * 8 + relPosY] || relPosZ > 0 && lakeBlocks[(counterRelPosX * 16 + (relPosZ - 1)) * 8 + relPosY] || relPosY < 7 && lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + relPosY + 1] || relPosY > 0 && lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + (relPosY - 1)]);

						if (isSource) {
							short typeId = (short) world.getBlockTypeIdAt(x + counterRelPosX, y + relPosY, z + relPosZ);

							if (relPosY >= 4 && BlockHelper.isLiquid(typeId)) {
								return false;
							}

							if (relPosY < 4 && !BlockHelper.isSolid(typeId) && world.getBlockTypeIdAt(x + counterRelPosX, y + relPosY, z + relPosZ) != this.blockIndex) {
								return false;
							}
						}
					}
				}
			}

			for (counterRelPosX = 0; counterRelPosX < 16; ++counterRelPosX) {
				for (relPosZ = 0; relPosZ < 16; ++relPosZ) {
					for (relPosY = 0; relPosY < 8; ++relPosY) {
						if (lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + relPosY]) {
							setBlock(world, x + counterRelPosX, y + relPosY, z + relPosZ, relPosY >= 4 ? 0 : this.blockIndex);
						}
					}
				}
			}

			for (counterRelPosX = 0; counterRelPosX < 16; ++counterRelPosX) {
				for (relPosZ = 0; relPosZ < 16; ++relPosZ) {
					for (relPosY = 4; relPosY < 8; ++relPosY) {
						if (lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + relPosY] && world.getBlockTypeIdAt(x + counterRelPosX, y + relPosY - 1, z + relPosZ) == 3 && world.getBlockAt(x + counterRelPosX, y + relPosY, z + relPosZ).getLightFromSky() > 0) {
							Biome biome = world.getBiome(x + counterRelPosX, z + relPosZ);

							if (BiomeHelp.getTopBlock(biome) == Mat.Mycelium.id) {
								setBlock(world, x + counterRelPosX, y + relPosY - 1, z + relPosZ, Mat.Mycelium.id);
							} else {
								setBlock(world, x + counterRelPosX, y + relPosY - 1, z + relPosZ, Mat.Grass.id);
							}
						}
					}
				}
			}

			if (this.blockIndex == Mat.LavaMoving.id || this.blockIndex == Mat.LavaStill.id) {
				for (counterRelPosX = 0; counterRelPosX < 16; ++counterRelPosX) {
					for (relPosZ = 0; relPosZ < 16; ++relPosZ) {
						for (relPosY = 0; relPosY < 8; ++relPosY) {
							isSource = !lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + relPosY] && (counterRelPosX < 15 && lakeBlocks[((counterRelPosX + 1) * 16 + relPosZ) * 8 + relPosY] || counterRelPosX > 0 && lakeBlocks[((counterRelPosX - 1) * 16 + relPosZ) * 8 + relPosY] || relPosZ < 15 && lakeBlocks[(counterRelPosX * 16 + relPosZ + 1) * 8 + relPosY] || relPosZ > 0 && lakeBlocks[(counterRelPosX * 16 + (relPosZ - 1)) * 8 + relPosY] || relPosY < 7 && lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + relPosY + 1] || relPosY > 0 && lakeBlocks[(counterRelPosX * 16 + relPosZ) * 8 + (relPosY - 1)]);

							if (isSource && (relPosY < 4 || rand.nextInt(2) != 0) && BlockHelper.isSolid(world.getBlockTypeIdAt(x + counterRelPosX, y + relPosY, z + relPosZ))) {
								setBlock(world, x + counterRelPosX, y + relPosY, z + relPosZ, Mat.Stone.id);
							}
						}
					}
				}
			}

			if (this.blockIndex == Mat.WaterMoving.id || this.blockIndex == Mat.WaterStill.id) {
				for (counterRelPosX = 0; counterRelPosX < 16; ++counterRelPosX) {
					for (relPosZ = 0; relPosZ < 16; ++relPosZ) {
						byte iceRelPos = 4;

						Block block = world.getBlockAt(x + counterRelPosX, y + iceRelPos, z + relPosZ);

						if ((block.getTypeId() == Mat.WaterMoving.id || block.getTypeId() == Mat.WaterStill.id) && block.getTemperature() < 0.15D) {
							setBlock(world, x + counterRelPosX, y + iceRelPos, z + relPosZ, Mat.Ice.id);
						}
					}
				}
			}

			return true;
		}
	}
}