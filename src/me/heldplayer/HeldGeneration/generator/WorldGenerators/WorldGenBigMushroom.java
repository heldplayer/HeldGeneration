package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenBigMushroom extends WorldGenerator {
	/** The mushroom type. 0 for brown, 1 for red. */
	private int mushroomType = -1;

	public WorldGenBigMushroom(int mushroomType) {
		super(true);
		this.mushroomType = mushroomType;
	}

	public WorldGenBigMushroom() {
		super(false);
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int type = rand.nextInt(2);

		if (this.mushroomType >= 0) {
			type = this.mushroomType;
		}

		int height = rand.nextInt(3) + 4;
		boolean canGrow = true;

		if (y >= 1 && y + height + 1 < 256) {
			int posY1typeId2;
			int posX1posY2;
			int posZ1rad;
			int typeId1posX2;

			for (posY1typeId2 = y; posY1typeId2 <= y + 1 + height; ++posY1typeId2) {
				byte var10 = 3;

				if (posY1typeId2 == y) {
					var10 = 0;
				}

				for (posX1posY2 = x - var10; posX1posY2 <= x + var10 && canGrow; ++posX1posY2) {
					for (posZ1rad = z - var10; posZ1rad <= z + var10 && canGrow; ++posZ1rad) {
						if (posY1typeId2 >= 0 && posY1typeId2 < 256) {
							typeId1posX2 = world.getBlockTypeIdAt(posX1posY2, posY1typeId2, posZ1rad);

							if (typeId1posX2 != 0 && typeId1posX2 != Mat.Leaves.id) {
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

				if (posY1typeId2 != Mat.Dirt.id && posY1typeId2 != Mat.Grass.id && posY1typeId2 != Mat.Mycelium.id) {
					return false;
				} else if (BlockHelper.canBlockStay(world.getBlockAt(x, y, z), Mat.BrownMushroom)) {
					return false;
				} else {
					this.setBlockAndMetadata(world, x, y - 1, z, Mat.Dirt.id, 0);
					int startY = y + height;

					if (type == 1) {
						startY = y + height - 3;
					}

					for (posX1posY2 = startY; posX1posY2 <= y + height; ++posX1posY2) {
						posZ1rad = 1;

						if (posX1posY2 < y + height) {
							++posZ1rad;
						}

						if (type == 0) {
							posZ1rad = 3;
						}

						for (typeId1posX2 = x - posZ1rad; typeId1posX2 <= x + posZ1rad; ++typeId1posX2) {
							for (int posZ = z - posZ1rad; posZ <= z + posZ1rad; ++posZ) {
								int data = 5;

								if (typeId1posX2 == x - posZ1rad) {
									--data;
								}

								if (typeId1posX2 == x + posZ1rad) {
									++data;
								}

								if (posZ == z - posZ1rad) {
									data -= 3;
								}

								if (posZ == z + posZ1rad) {
									data += 3;
								}

								if (type == 0 || posX1posY2 < y + height) {
									if ((typeId1posX2 == x - posZ1rad || typeId1posX2 == x + posZ1rad) && (posZ == z - posZ1rad || posZ == z + posZ1rad)) {
										continue;
									}

									if (typeId1posX2 == x - (posZ1rad - 1) && posZ == z - posZ1rad) {
										data = 1;
									}

									if (typeId1posX2 == x - posZ1rad && posZ == z - (posZ1rad - 1)) {
										data = 1;
									}

									if (typeId1posX2 == x + (posZ1rad - 1) && posZ == z - posZ1rad) {
										data = 3;
									}

									if (typeId1posX2 == x + posZ1rad && posZ == z - (posZ1rad - 1)) {
										data = 3;
									}

									if (typeId1posX2 == x - (posZ1rad - 1) && posZ == z + posZ1rad) {
										data = 7;
									}

									if (typeId1posX2 == x - posZ1rad && posZ == z + (posZ1rad - 1)) {
										data = 7;
									}

									if (typeId1posX2 == x + (posZ1rad - 1) && posZ == z + posZ1rad) {
										data = 9;
									}

									if (typeId1posX2 == x + posZ1rad && posZ == z + (posZ1rad - 1)) {
										data = 9;
									}
								}

								if (data == 5 && posX1posY2 < y + height) {
									data = 0;
								}

								if ((data != 0 || y >= y + height - 1) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(typeId1posX2, posX1posY2, posZ))) {
									this.setBlockAndMetadata(world, typeId1posX2, posX1posY2, posZ, Mat.GiantMushroomBrown.id + type, data);
								}
							}
						}
					}

					for (posX1posY2 = 0; posX1posY2 < height; ++posX1posY2) {
						posZ1rad = world.getBlockTypeIdAt(x, y + posX1posY2, z);

						if (!BlockHelper.isOpaqueCube(posZ1rad)) {
							this.setBlockAndMetadata(world, x, y + posX1posY2, z, Mat.BrownMushroom.id + type, 10);
						}
					}

					return true;
				}
			}
		} else {
			return false;
		}
	}
}