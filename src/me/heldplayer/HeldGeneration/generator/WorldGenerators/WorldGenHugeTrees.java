package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;
import me.heldplayer.HeldGeneration.helpers.MathHelper;

import org.bukkit.World;

public class WorldGenHugeTrees extends WorldGenerator {
	private final int minHeight;

	/** Sets the metadata for the wood blocks used */
	private final int woodMetadata;

	/** Sets the metadata for the leaves used in huge trees */
	private final int leavesMetadata;

	public WorldGenHugeTrees(boolean notify, int minHeight, int logData, int leavesData) {
		super(notify);
		this.minHeight = minHeight;
		this.woodMetadata = logData;
		this.leavesMetadata = leavesData;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(3) + this.minHeight;
		boolean canGrow = true;

		if (y >= 1 && y + height + 1 <= 256) {
			int posY1typeId2;
			int posX1posY2;
			int posZ1posX2;
			int typeId1posZ2;

			for (posY1typeId2 = y; posY1typeId2 <= y + 1 + height; ++posY1typeId2) {
				byte rad = 2;

				if (posY1typeId2 == y) {
					rad = 1;
				}

				if (posY1typeId2 >= y + 1 + height - 2) {
					rad = 2;
				}

				for (posX1posY2 = x - rad; posX1posY2 <= x + rad && canGrow; ++posX1posY2) {
					for (posZ1posX2 = z - rad; posZ1posX2 <= z + rad && canGrow; ++posZ1posX2) {
						if (posY1typeId2 >= 0 && posY1typeId2 < 256) {
							typeId1posZ2 = world.getBlockTypeIdAt(posX1posY2, posY1typeId2, posZ1posX2);

							if (typeId1posZ2 != 0 && typeId1posZ2 != Mat.Leaves.id && typeId1posZ2 != Mat.Grass.id && typeId1posZ2 != Mat.Dirt.id && typeId1posZ2 != Mat.Log.id && typeId1posZ2 != Mat.Sapling.id) {
								canGrow = false;
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

				if ((posY1typeId2 == Mat.Grass.id || posY1typeId2 == Mat.Dirt.id) && y < 256 - height - 1) {
					setBlock(world, x, y - 1, z, Mat.Dirt.id);
					setBlock(world, x + 1, y - 1, z, Mat.Dirt.id);
					setBlock(world, x, y - 1, z + 1, Mat.Dirt.id);
					setBlock(world, x + 1, y - 1, z + 1, Mat.Dirt.id);
					this.growLeaves(world, x, z, y + height, 2, rand);

					for (int posY = y + height - 2 - rand.nextInt(4); posY > y + height / 2; posY -= 2 + rand.nextInt(4)) {
						float rads = rand.nextFloat() * (float) Math.PI * 2.0F;
						posZ1posX2 = x + (int) (0.5F + MathHelper.cos(rads) * 4.0F);
						typeId1posZ2 = z + (int) (0.5F + MathHelper.sin(rads) * 4.0F);
						this.growLeaves(world, posZ1posX2, typeId1posZ2, posY, 0, rand);

						for (int relPosY = 0; relPosY < 5; ++relPosY) {
							posZ1posX2 = x + (int) (1.5F + MathHelper.cos(rads) * (float) relPosY);
							typeId1posZ2 = z + (int) (1.5F + MathHelper.sin(rads) * (float) relPosY);
							this.setBlockAndMetadata(world, posZ1posX2, posY - 3 + relPosY / 2, typeId1posZ2, Mat.Log.id, this.woodMetadata);
						}
					}

					for (posX1posY2 = 0; posX1posY2 < height; ++posX1posY2) {
						posZ1posX2 = world.getBlockTypeIdAt(x, y + posX1posY2, z);

						if (posZ1posX2 == 0 || posZ1posX2 == Mat.Leaves.id) {
							this.setBlockAndMetadata(world, x, y + posX1posY2, z, Mat.Log.id, this.woodMetadata);

							if (posX1posY2 > 0) {
								if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x - 1, y + posX1posY2, z) == 0) {
									this.setBlockAndMetadata(world, x - 1, y + posX1posY2, z, Mat.Vine.id, 8);
								}

								if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x, y + posX1posY2, z - 1) == 0) {
									this.setBlockAndMetadata(world, x, y + posX1posY2, z - 1, Mat.Vine.id, 1);
								}
							}
						}

						if (posX1posY2 < height - 1) {
							posZ1posX2 = world.getBlockTypeIdAt(x + 1, y + posX1posY2, z);

							if (posZ1posX2 == 0 || posZ1posX2 == Mat.Leaves.id) {
								this.setBlockAndMetadata(world, x + 1, y + posX1posY2, z, Mat.Log.id, this.woodMetadata);

								if (posX1posY2 > 0) {
									if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x + 2, y + posX1posY2, z) == 0) {
										this.setBlockAndMetadata(world, x + 2, y + posX1posY2, z, Mat.Vine.id, 2);
									}

									if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x + 1, y + posX1posY2, z - 1) == 0) {
										this.setBlockAndMetadata(world, x + 1, y + posX1posY2, z - 1, Mat.Vine.id, 1);
									}
								}
							}

							posZ1posX2 = world.getBlockTypeIdAt(x + 1, y + posX1posY2, z + 1);

							if (posZ1posX2 == 0 || posZ1posX2 == Mat.Leaves.id) {
								this.setBlockAndMetadata(world, x + 1, y + posX1posY2, z + 1, Mat.Log.id, this.woodMetadata);

								if (posX1posY2 > 0) {
									if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x + 2, y + posX1posY2, z + 1) == 0) {
										this.setBlockAndMetadata(world, x + 2, y + posX1posY2, z + 1, Mat.Vine.id, 2);
									}

									if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x + 1, y + posX1posY2, z + 2) == 0) {
										this.setBlockAndMetadata(world, x + 1, y + posX1posY2, z + 2, Mat.Vine.id, 4);
									}
								}
							}

							posZ1posX2 = world.getBlockTypeIdAt(x, y + posX1posY2, z + 1);

							if (posZ1posX2 == 0 || posZ1posX2 == Mat.Leaves.id) {
								this.setBlockAndMetadata(world, x, y + posX1posY2, z + 1, Mat.Log.id, this.woodMetadata);

								if (posX1posY2 > 0) {
									if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x - 1, y + posX1posY2, z + 1) == 0) {
										this.setBlockAndMetadata(world, x - 1, y + posX1posY2, z + 1, Mat.Vine.id, 8);
									}

									if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x, y + posX1posY2, z + 2) == 0) {
										this.setBlockAndMetadata(world, x, y + posX1posY2, z + 2, Mat.Vine.id, 4);
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

	private void growLeaves(World world, int x, int z, int y, int rad, Random rand) {
		byte height = 2;

		for (int posY = y - height; posY <= y; ++posY) {
			int relPosY = posY - y;
			int boxRad = rad + 1 - relPosY;

			for (int posX = x - boxRad; posX <= x + boxRad + 1; ++posX) {
				int relPosX = posX - x;

				for (int posZ = z - boxRad; posZ <= z + boxRad + 1; ++posZ) {
					int relPosZ = posZ - z;

					if ((relPosX >= 0 || relPosZ >= 0 || relPosX * relPosX + relPosZ * relPosZ <= boxRad * boxRad) && (relPosX <= 0 && relPosZ <= 0 || relPosX * relPosX + relPosZ * relPosZ <= (boxRad + 1) * (boxRad + 1)) && (rand.nextInt(4) != 0 || relPosX * relPosX + relPosZ * relPosZ <= (boxRad - 1) * (boxRad - 1)) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(posX, posY, posZ))) {
						this.setBlockAndMetadata(world, posX, posY, posZ, Mat.Leaves.id, this.leavesMetadata);
					}
				}
			}
		}
	}
}