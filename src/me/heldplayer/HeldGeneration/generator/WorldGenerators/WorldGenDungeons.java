package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.ItemStack;

public class WorldGenDungeons extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		byte var6 = 3;
		int xRad = rand.nextInt(2) + 2;
		int zRad = rand.nextInt(2) + 2;
		int var9 = 0;
		int posX1;
		int posY1chestCount;
		int posZ1X2;

		for (posX1 = x - xRad - 1; posX1 <= x + xRad + 1; ++posX1) {
			for (posY1chestCount = y - 1; posY1chestCount <= y + var6 + 1; ++posY1chestCount) {
				for (posZ1X2 = z - zRad - 1; posZ1X2 <= z + zRad + 1; ++posZ1X2) {
					int typeId = world.getBlockTypeIdAt(posX1, posY1chestCount, posZ1X2);

					if (posY1chestCount == y - 1 && !BlockHelper.isSolid(typeId)) {
						return false;
					}

					if (posY1chestCount == y + var6 + 1 && !BlockHelper.isSolid(typeId)) {
						return false;
					}

					if ((posX1 == x - xRad - 1 || posX1 == x + xRad + 1 || posZ1X2 == z - zRad - 1 || posZ1X2 == z + zRad + 1) && posY1chestCount == y && world.getBlockTypeIdAt(posX1, posY1chestCount, posZ1X2) == 0 && world.getBlockTypeIdAt(posX1, posY1chestCount + 1, posZ1X2) == 0) {
						++var9;
					}
				}
			}
		}

		if (var9 >= 1 && var9 <= 5) {
			for (posX1 = x - xRad - 1; posX1 <= x + xRad + 1; ++posX1) {
				for (posY1chestCount = y + var6; posY1chestCount >= y - 1; --posY1chestCount) {
					for (posZ1X2 = z - zRad - 1; posZ1X2 <= z + zRad + 1; ++posZ1X2) {
						if (posX1 != x - xRad - 1 && posY1chestCount != y - 1 && posZ1X2 != z - zRad - 1 && posX1 != x + xRad + 1 && posY1chestCount != y + var6 + 1 && posZ1X2 != z + zRad + 1) {
							world.getBlockAt(posX1, posY1chestCount, posZ1X2).setTypeId(0);
						} else if (posY1chestCount >= 0 && !BlockHelper.isSolid(world.getBlockTypeIdAt(posX1, posY1chestCount - 1, posZ1X2))) {
							world.getBlockAt(posX1, posY1chestCount, posZ1X2).setTypeId(0);
						} else if (BlockHelper.isSolid(world.getBlockTypeIdAt(posX1, posY1chestCount, posZ1X2))) {
							if (posY1chestCount == y - 1 && rand.nextInt(4) != 0) {
								world.getBlockAt(posX1, posY1chestCount, posZ1X2).setTypeId(Mat.MossyCobblestone.id);
							} else {
								world.getBlockAt(posX1, posY1chestCount, posZ1X2).setTypeId(Mat.Cobblestone.id);
							}
						}
					}
				}
			}

			posX1 = 0;

			while (posX1 < 2) {
				posY1chestCount = 0;

				while (true) {
					if (posY1chestCount < 3) {
						label210: {
							posZ1X2 = x + rand.nextInt(xRad * 2 + 1) - xRad;
							int posZ2 = z + rand.nextInt(zRad * 2 + 1) - zRad;

							if (world.getBlockTypeIdAt(posZ1X2, y, posZ2) == 0) {
								int walls = 0;

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(posZ1X2 - 1, y, posZ2))) {
									++walls;
								}

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(posZ1X2 + 1, y, posZ2))) {
									++walls;
								}

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(posZ1X2, y, posZ2 - 1))) {
									++walls;
								}

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(posZ1X2, y, posZ2 + 1))) {
									++walls;
								}

								if (walls == 1) {
									world.getBlockAt(posZ1X2, y, posZ2).setTypeId(Mat.Chest.id);
									Chest lootChest = (Chest) world.getBlockAt(posZ1X2, y, posZ2).getState();

									if (lootChest != null) {
										for (int lootLimit = 0; lootLimit < 8; ++lootLimit) {
											ItemStack loot = pickCheckLootItem(rand);

											if (loot != null) {
												lootChest.getInventory().setItem(rand.nextInt(lootChest.getInventory().getSize()), loot);
											}
										}
									}

									break label210;
								}
							}

							++posY1chestCount;
							continue;
						}
					}

					++posX1;
					break;
				}
			}

			world.getBlockAt(x, y, z).setTypeId(Mat.MobSpawner.id);
			CreatureSpawner mobSpawner = (CreatureSpawner) world.getBlockAt(x, y, z).getState();

			if (mobSpawner != null) {
				mobSpawner.setCreatureTypeByName(pickMobSpawner(rand));
			} else {
				System.err.println("Failed to fetch mob spawner entity at (" + x + ", " + y + ", " + z + ")");
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Picks potentially a random item to add to a dungeon chest.
	 */
	private ItemStack pickCheckLootItem(Random rand) {
		int item = rand.nextInt(11);
		return item == 0 ? new ItemStack(329) : (item == 1 ? new ItemStack(265, rand.nextInt(4) + 1) : (item == 2 ? new ItemStack(297) : (item == 3 ? new ItemStack(296, rand.nextInt(4) + 1) : (item == 4 ? new ItemStack(289, rand.nextInt(4) + 1) : (item == 5 ? new ItemStack(287, rand.nextInt(4) + 1) : (item == 6 ? new ItemStack(325) : (item == 7 && rand.nextInt(100) == 0 ? new ItemStack(322) : (item == 8 && rand.nextInt(2) == 0 ? new ItemStack(331, rand.nextInt(4) + 1) : (item == 9 && rand.nextInt(10) == 0 ? new ItemStack(2256 + rand.nextInt(2)) : (item == 10 ? new ItemStack(351, 1, (short) 3) : null))))))))));
	}

	/**
	 * Randomly decides which spawner to use in a dungeon
	 */
	private String pickMobSpawner(Random rand) {
		int mob = rand.nextInt(4);
		return mob == 0 ? "Skeleton" : (mob == 1 ? "Zombie" : (mob == 2 ? "Zombie" : (mob == 3 ? "Spider" : "")));
	}
}