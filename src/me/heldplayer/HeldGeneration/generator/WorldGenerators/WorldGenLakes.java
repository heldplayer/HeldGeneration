package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BiomeHelp;
import me.heldplayer.HeldGeneration.helpers.BlockHelper;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class WorldGenLakes extends WorldGenerator {
	private int blockIndex;

	public WorldGenLakes(int par1) {
		this.blockIndex = par1;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		par3 -= 8;

		for (par5 -= 8; par4 > 5 && par1World.getBlockAt(par3, par4, par5).getTypeId() == 0; --par4) {
			;
		}

		if (par4 <= 4) {
			return false;
		} else {
			par4 -= 4;
			boolean[] var6 = new boolean[2048];
			int var7 = par2Random.nextInt(4) + 4;
			int var8;

			for (var8 = 0; var8 < var7; ++var8) {
				double var9 = par2Random.nextDouble() * 6.0D + 3.0D;
				double var11 = par2Random.nextDouble() * 4.0D + 2.0D;
				double var13 = par2Random.nextDouble() * 6.0D + 3.0D;
				double var15 = par2Random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
				double var17 = par2Random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
				double var19 = par2Random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

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
							short var12 = (short) par1World.getBlockAt(par3 + var8, par4 + var10, par5 + var32).getTypeId();

							if (var10 >= 4 && BlockHelper.isLiquid(var12)) {
								return false;
							}

							if (var10 < 4 && !BlockHelper.isSolid(var12) && par1World.getBlockTypeIdAt(par3 + var8, par4 + var10, par5 + var32) != this.blockIndex) {
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
							par1World.getBlockAt(par3 + var8, par4 + var10, par5 + var32).setTypeIdAndData(var10 >= 4 ? 0 : this.blockIndex, (byte) 0, false);
						}
					}
				}
			}

			for (var8 = 0; var8 < 16; ++var8) {
				for (var32 = 0; var32 < 16; ++var32) {
					for (var10 = 4; var10 < 8; ++var10) {
						if (var6[(var8 * 16 + var32) * 8 + var10] && par1World.getBlockTypeIdAt(par3 + var8, par4 + var10 - 1, par5 + var32) == 3 && par1World.getBlockAt(par3 + var8, par4 + var10, par5 + var32).getLightFromSky() > 0) {
							Biome var35 = par1World.getBiome(par3 + var8, par5 + var32);

							if (BiomeHelp.getTopBlock(var35) == 110) {
								par1World.getBlockAt(par3 + var8, par4 + var10 - 1, par5 + var32).setTypeIdAndData(110, (byte) 0, false);
							} else {
								par1World.getBlockAt(par3 + var8, par4 + var10 - 1, par5 + var32).setTypeIdAndData(2, (byte) 0, false);
							}
						}
					}
				}
			}

			if (this.blockIndex == 10 || this.blockIndex == 11) {
				for (var8 = 0; var8 < 16; ++var8) {
					for (var32 = 0; var32 < 16; ++var32) {
						for (var10 = 0; var10 < 8; ++var10) {
							var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

							if (var33 && (var10 < 4 || par2Random.nextInt(2) != 0) && BlockHelper.isSolid(par1World.getBlockTypeIdAt(par3 + var8, par4 + var10, par5 + var32))) {
								par1World.getBlockAt(par3 + var8, par4 + var10, par5 + var32).setTypeId(1);
							}
						}
					}
				}
			}

			if (this.blockIndex == 8 || this.blockIndex == 9) {
				for (var8 = 0; var8 < 16; ++var8) {
					for (var32 = 0; var32 < 16; ++var32) {
						byte var34 = 4;

						Block block = par1World.getBlockAt(par3 + var8, par4 + var34, par5 + var32);

						if ((block.getTypeId() == 8 || block.getTypeId() == 9) && block.getTemperature() < 0.15D) {
							block.setTypeId(79);
						}
					}
				}
			}

			return true;
		}
	}
}