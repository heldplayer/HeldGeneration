package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenForest extends WorldGenerator {
	public WorldGenForest(boolean par1) {
		super(par1);
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(3) + 5;
		boolean canGrow = true;

		if (y >= 1 && y + height + 1 <= 256) {
			int posY1typeId2;
			int posX1relPosY2typeId3;
			int posZ1;
			int blockId1posX2;

			for (posY1typeId2 = y; posY1typeId2 <= y + 1 + height; ++posY1typeId2) {
				byte rad = 1;

				if (posY1typeId2 == y) {
					rad = 0;
				}

				if (posY1typeId2 >= y + 1 + height - 2) {
					rad = 2;
				}

				for (posX1relPosY2typeId3 = x - rad; posX1relPosY2typeId3 <= x + rad && canGrow; ++posX1relPosY2typeId3) {
					for (posZ1 = z - rad; posZ1 <= z + rad && canGrow; ++posZ1) {
						if (posY1typeId2 >= 0 && posY1typeId2 < 256) {
							blockId1posX2 = world.getBlockTypeIdAt(posX1relPosY2typeId3, posY1typeId2, posZ1);

							if (blockId1posX2 != 0 && blockId1posX2 != Mat.Leaves.id) {
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
					this.setBlock(world, x, y - 1, z, Mat.Dirt.id);
					int posY2;

					for (posY2 = y - 3 + height; posY2 <= y + height; ++posY2) {
						posX1relPosY2typeId3 = posY2 - (y + height);
						posZ1 = 1 - posX1relPosY2typeId3 / 2;

						for (blockId1posX2 = x - posZ1; blockId1posX2 <= x + posZ1; ++blockId1posX2) {
							int relPosX = blockId1posX2 - x;

							for (int posZ2 = z - posZ1; posZ2 <= z + posZ1; ++posZ2) {
								int relPosZ = posZ2 - z;

								if ((Math.abs(relPosX) != posZ1 || Math.abs(relPosZ) != posZ1 || rand.nextInt(2) != 0 && posX1relPosY2typeId3 != 0) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(blockId1posX2, posY2, posZ2))) {
									this.setBlockAndMetadata(world, blockId1posX2, posY2, posZ2, Mat.Leaves.id, 2);
								}
							}
						}
					}

					for (posY2 = 0; posY2 < height; ++posY2) {
						posX1relPosY2typeId3 = world.getBlockTypeIdAt(x, y + posY2, z);

						if (posX1relPosY2typeId3 == 0 || posX1relPosY2typeId3 == Mat.Leaves.id) {
							this.setBlockAndMetadata(world, x, y + posY2, z, Mat.Log.id, 2);
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
}