package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenSwamp extends WorldGenerator {
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height;

		for (height = rand.nextInt(4) + 5; BlockHelper.isWater(world.getBlockTypeIdAt(x, y - 1, z)); --y) {
			;
		}

		boolean canGrow = true;

		if (y >= 1 && y + height + 1 <= 128) {
			int posY1typeId2;
			int posX1relPosY2;
			int posZ1relRelPosY2;
			int typeId1posX2;

			for (posY1typeId2 = y; posY1typeId2 <= y + 1 + height; ++posY1typeId2) {
				byte rad = 1;

				if (posY1typeId2 == y) {
					rad = 0;
				}

				if (posY1typeId2 >= y + 1 + height - 2) {
					rad = 3;
				}

				for (posX1relPosY2 = x - rad; posX1relPosY2 <= x + rad && canGrow; ++posX1relPosY2) {
					for (posZ1relRelPosY2 = z - rad; posZ1relRelPosY2 <= z + rad && canGrow; ++posZ1relRelPosY2) {
						if (posY1typeId2 >= 0 && posY1typeId2 < 128) {
							typeId1posX2 = world.getBlockTypeIdAt(posX1relPosY2, posY1typeId2, posZ1relRelPosY2);

							if (typeId1posX2 != 0 && typeId1posX2 != Mat.Leaves.id) {
								if (typeId1posX2 != Mat.WaterStill.id && typeId1posX2 != Mat.WaterMoving.id) {
									canGrow = false;
								} else if (posY1typeId2 > y) {
									canGrow = false;
								}
							}
						} else {
							canGrow = false;
						}
					}
				}
			}

			if (!canGrow) {
				return false;
			} else {
				posY1typeId2 = world.getBlockTypeIdAt(x, y - 1, z);

				if ((posY1typeId2 == Mat.Grass.id || posY1typeId2 == Mat.Dirt.id) && y < 128 - height - 1) {
					this.setBlock(world, x, y - 1, z, Mat.Dirt.id);
					int relPosX;
					int posY;

					for (posY = y - 3 + height; posY <= y + height; ++posY) {
						posX1relPosY2 = posY - (y + height);
						posZ1relRelPosY2 = 2 - posX1relPosY2 / 2;

						for (typeId1posX2 = x - posZ1relRelPosY2; typeId1posX2 <= x + posZ1relRelPosY2; ++typeId1posX2) {
							relPosX = typeId1posX2 - x;

							for (int posZ = z - posZ1relRelPosY2; posZ <= z + posZ1relRelPosY2; ++posZ) {
								int relPosZ = posZ - z;

								if ((Math.abs(relPosX) != posZ1relRelPosY2 || Math.abs(relPosZ) != posZ1relRelPosY2 || rand.nextInt(2) != 0 && posX1relPosY2 != 0) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(typeId1posX2, posY, posZ))) {
									this.setBlock(world, typeId1posX2, posY, posZ, Mat.Leaves.id);
								}
							}
						}
					}

					for (posY = 0; posY < height; ++posY) {
						posX1relPosY2 = world.getBlockTypeIdAt(x, y + posY, z);

						if (posX1relPosY2 == 0 || posX1relPosY2 == Mat.Leaves.id || posX1relPosY2 == Mat.WaterMoving.id || posX1relPosY2 == Mat.WaterStill.id) {
							this.setBlock(world, x, y + posY, z, Mat.Log.id);
						}
					}

					for (posY = y - 3 + height; posY <= y + height; ++posY) {
						posX1relPosY2 = posY - (y + height);
						posZ1relRelPosY2 = 2 - posX1relPosY2 / 2;

						for (typeId1posX2 = x - posZ1relRelPosY2; typeId1posX2 <= x + posZ1relRelPosY2; ++typeId1posX2) {
							for (relPosX = z - posZ1relRelPosY2; relPosX <= z + posZ1relRelPosY2; ++relPosX) {
								if (world.getBlockTypeIdAt(typeId1posX2, posY, relPosX) == Mat.Leaves.id) {
									if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(typeId1posX2 - 1, posY, relPosX) == 0) {
										this.generateVines(world, typeId1posX2 - 1, posY, relPosX, 8);
									}

									if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(typeId1posX2 + 1, posY, relPosX) == 0) {
										this.generateVines(world, typeId1posX2 + 1, posY, relPosX, 2);
									}

									if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(typeId1posX2, posY, relPosX - 1) == 0) {
										this.generateVines(world, typeId1posX2, posY, relPosX - 1, 1);
									}

									if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(typeId1posX2, posY, relPosX + 1) == 0) {
										this.generateVines(world, typeId1posX2, posY, relPosX + 1, 4);
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

	/**
	 * Generates vines at the given position until it hits a block.
	 */
	private void generateVines(World par1World, int par2, int par3, int par4, int par5) {
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