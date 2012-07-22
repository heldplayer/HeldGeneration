package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTaiga2 extends WorldGenerator {
	public WorldGenTaiga2(boolean notify) {
		super(notify);
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int totalHeight = rand.nextInt(4) + 6;
		int leavesStart = 1 + rand.nextInt(2);
		int height = totalHeight - leavesStart;
		int rad = 2 + rand.nextInt(2);
		boolean canGrow = true;

		if (y >= 1 && y + totalHeight + 1 <= 256) {
			int posY1typeId2;
			int posX1;
			int typeId1relPosY2leavesOffset3;
			int ringRad;

			for (posY1typeId2 = y; posY1typeId2 <= y + 1 + totalHeight && canGrow; ++posY1typeId2) {
				if (posY1typeId2 - y < leavesStart) {
					ringRad = 0;
				} else {
					ringRad = rad;
				}

				for (posX1 = x - ringRad; posX1 <= x + ringRad && canGrow; ++posX1) {
					for (int posZ1 = z - ringRad; posZ1 <= z + ringRad && canGrow; ++posZ1) {
						if (posY1typeId2 >= 0 && posY1typeId2 < 256) {
							typeId1relPosY2leavesOffset3 = world.getBlockTypeIdAt(posX1, posY1typeId2, posZ1);

							if (typeId1relPosY2leavesOffset3 != 0 && typeId1relPosY2leavesOffset3 != Mat.Leaves.id) {
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

				if ((posY1typeId2 == Mat.Grass.id || posY1typeId2 == Mat.Dirt.id) && y < 256 - totalHeight - 1) {
					this.setBlock(world, x, y - 1, z, Mat.Dirt.id);
					ringRad = rand.nextInt(2);
					posX1 = 1;
					byte otherRingRad = 0;
					int posX2typeId3;
					int posY2;

					for (typeId1relPosY2leavesOffset3 = 0; typeId1relPosY2leavesOffset3 <= height; ++typeId1relPosY2leavesOffset3) {
						posY2 = y + totalHeight - typeId1relPosY2leavesOffset3;

						for (posX2typeId3 = x - ringRad; posX2typeId3 <= x + ringRad; ++posX2typeId3) {
							int relPosX = posX2typeId3 - x;

							for (int posZ2 = z - ringRad; posZ2 <= z + ringRad; ++posZ2) {
								int relPosZ = posZ2 - z;

								if ((Math.abs(relPosX) != ringRad || Math.abs(relPosZ) != ringRad || ringRad <= 0) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(posX2typeId3, posY2, posZ2))) {
									this.setBlockAndMetadata(world, posX2typeId3, posY2, posZ2, Mat.Leaves.id, 1);
								}
							}
						}

						if (ringRad >= posX1) {
							ringRad = otherRingRad;
							otherRingRad = 1;
							++posX1;

							if (posX1 > rad) {
								posX1 = rad;
							}
						} else {
							++ringRad;
						}
					}

					typeId1relPosY2leavesOffset3 = rand.nextInt(3);

					for (posY2 = 0; posY2 < totalHeight - typeId1relPosY2leavesOffset3; ++posY2) {
						posX2typeId3 = world.getBlockTypeIdAt(x, y + posY2, z);

						if (posX2typeId3 == 0 || posX2typeId3 == Mat.Leaves.id) {
							this.setBlockAndMetadata(world, x, y + posY2, z, Mat.Log.id, 1);
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