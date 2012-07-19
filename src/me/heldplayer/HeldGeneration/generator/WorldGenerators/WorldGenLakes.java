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
			boolean[] var6 = new boolean[2048];
			int var7 = rand.nextInt(4) + 4;
			int var8;

			for (var8 = 0; var8 < var7; ++var8) {
				double var9 = rand.nextDouble() * 6.0D + 3.0D;
				double var11 = rand.nextDouble() * 4.0D + 2.0D;
				double var13 = rand.nextDouble() * 6.0D + 3.0D;
				double var15 = rand.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
				double var17 = rand.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
				double var19 = rand.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

				for (int var21 = 1; var21 < 15; ++var21) {
					for (int var22 = 1; var22 < 15; ++var22) {
						for (int var23 = 1; var23 < 7; ++var23) {
							double var24 = (var21 - var15) / (var9 / 2.0D);
							double var26 = (var23 - var17) / (var11 / 2.0D);
							double var28 = (var22 - var19) / (var13 / 2.0D);
							double var30 = var24 * var24 + var26 * var26 + var28 * var28;

							if (var30 < 1.0D) {
								var6[(var21 * 16 + var22) * 8 + var23] = true;
							}
						}
					}
				}
			}

			int var10;
			int var32;
			boolean var33;

			for (var8 = 0; var8 < 16; ++var8) {
				for (var32 = 0; var32 < 16; ++var32) {
					for (var10 = 0; var10 < 8; ++var10) {
						var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

						if (var33) {
							short var12 = (short) world.getBlockAt(x + var8, y + var10, z + var32).getTypeId();

							if (var10 >= 4 && BlockHelper.isLiquid(var12)) {
								return false;
							}

							if (var10 < 4 && !BlockHelper.isSolid(var12) && world.getBlockTypeIdAt(x + var8, y + var10, z + var32) != this.blockIndex) {
								return false;
							}
						}
					}
				}
			}

			for (var8 = 0; var8 < 16; ++var8) {
				for (var32 = 0; var32 < 16; ++var32) {
					for (var10 = 0; var10 < 8; ++var10) {
						if (var6[(var8 * 16 + var32) * 8 + var10]) {
							world.getBlockAt(x + var8, y + var10, z + var32).setTypeIdAndData(var10 >= 4 ? 0 : this.blockIndex, (byte) 0, false);
						}
					}
				}
			}

			for (var8 = 0; var8 < 16; ++var8) {
				for (var32 = 0; var32 < 16; ++var32) {
					for (var10 = 4; var10 < 8; ++var10) {
						if (var6[(var8 * 16 + var32) * 8 + var10] && world.getBlockTypeIdAt(x + var8, y + var10 - 1, z + var32) == 3 && world.getBlockAt(x + var8, y + var10, z + var32).getLightFromSky() > 0) {
							Biome var35 = world.getBiome(x + var8, z + var32);

							if (BiomeHelp.getTopBlock(var35) == 110) {
								world.getBlockAt(x + var8, y + var10 - 1, z + var32).setTypeIdAndData(Mat.Mycelium.id, (byte) 0, false);
							} else {
								world.getBlockAt(x + var8, y + var10 - 1, z + var32).setTypeIdAndData(Mat.Grass.id, (byte) 0, false);
							}
						}
					}
				}
			}

			if (this.blockIndex == Mat.LavaMoving.id || this.blockIndex == Mat.LavaStill.id) {
				for (var8 = 0; var8 < 16; ++var8) {
					for (var32 = 0; var32 < 16; ++var32) {
						for (var10 = 0; var10 < 8; ++var10) {
							var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

							if (var33 && (var10 < 4 || rand.nextInt(2) != 0) && BlockHelper.isSolid(world.getBlockTypeIdAt(x + var8, y + var10, z + var32))) {
								world.getBlockAt(x + var8, y + var10, z + var32).setTypeId(Mat.Stone.id);
							}
						}
					}
				}
			}

			if (this.blockIndex == Mat.WaterMoving.id || this.blockIndex == Mat.WaterStill.id) {
				for (var8 = 0; var8 < 16; ++var8) {
					for (var32 = 0; var32 < 16; ++var32) {
						byte var34 = 4;

						Block block = world.getBlockAt(x + var8, y + var34, z + var32);

						if ((block.getTypeId() == 8 || block.getTypeId() == 9) && block.getTemperature() < 0.15D) {
							block.setTypeId(Mat.Ice.id);
						}
					}
				}
			}

			return true;
		}
	}
}