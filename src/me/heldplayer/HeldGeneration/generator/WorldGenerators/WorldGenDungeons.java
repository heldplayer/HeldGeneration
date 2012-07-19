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
		int var7 = rand.nextInt(2) + 2;
		int var8 = rand.nextInt(2) + 2;
		int var9 = 0;
		int var10;
		int var11;
		int var12;

		for (var10 = x - var7 - 1; var10 <= x + var7 + 1; ++var10) {
			for (var11 = y - 1; var11 <= y + var6 + 1; ++var11) {
				for (var12 = z - var8 - 1; var12 <= z + var8 + 1; ++var12) {
					int var13 = world.getBlockTypeIdAt(var10, var11, var12);

					if (var11 == y - 1 && !BlockHelper.isSolid(var13)) {
						return false;
					}

					if (var11 == y + var6 + 1 && !BlockHelper.isSolid(var13)) {
						return false;
					}

					if ((var10 == x - var7 - 1 || var10 == x + var7 + 1 || var12 == z - var8 - 1 || var12 == z + var8 + 1) && var11 == y && world.getBlockTypeIdAt(var10, var11, var12) == 0 && world.getBlockTypeIdAt(var10, var11 + 1, var12) == 0) {
						++var9;
					}
				}
			}
		}

		if (var9 >= 1 && var9 <= 5) {
			for (var10 = x - var7 - 1; var10 <= x + var7 + 1; ++var10) {
				for (var11 = y + var6; var11 >= y - 1; --var11) {
					for (var12 = z - var8 - 1; var12 <= z + var8 + 1; ++var12) {
						if (var10 != x - var7 - 1 && var11 != y - 1 && var12 != z - var8 - 1 && var10 != x + var7 + 1 && var11 != y + var6 + 1 && var12 != z + var8 + 1) {
							world.getBlockAt(var10, var11, var12).setTypeId(0);
						} else if (var11 >= 0 && !BlockHelper.isSolid(world.getBlockTypeIdAt(var10, var11 - 1, var12))) {
							world.getBlockAt(var10, var11, var12).setTypeId(0);
						} else if (BlockHelper.isSolid(world.getBlockTypeIdAt(var10, var11, var12))) {
							if (var11 == y - 1 && rand.nextInt(4) != 0) {
								world.getBlockAt(var10, var11, var12).setTypeId(Mat.MossyCobblestone.id);
							} else {
								world.getBlockAt(var10, var11, var12).setTypeId(Mat.Cobblestone.id);
							}
						}
					}
				}
			}

			var10 = 0;

			while (var10 < 2) {
				var11 = 0;

				while (true) {
					if (var11 < 3) {
						label210: {
							var12 = x + rand.nextInt(var7 * 2 + 1) - var7;
							int var14 = z + rand.nextInt(var8 * 2 + 1) - var8;

							if (world.getBlockTypeIdAt(var12, y, var14) == 0) {
								int var15 = 0;

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(var12 - 1, y, var14))) {
									++var15;
								}

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(var12 + 1, y, var14))) {
									++var15;
								}

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(var12, y, var14 - 1))) {
									++var15;
								}

								if (BlockHelper.isSolid(world.getBlockTypeIdAt(var12, y, var14 + 1))) {
									++var15;
								}

								if (var15 == 1) {
									world.getBlockAt(var12, y, var14).setTypeId(Mat.Chest.id);
									Chest lootChest = (Chest) world.getBlockAt(var12, y, var14).getState();

									if (lootChest != null) {
										for (int var17 = 0; var17 < 8; ++var17) {
											ItemStack loot = pickCheckLootItem(rand);

											if (loot != null) {
												lootChest.getInventory().setItem(rand.nextInt(lootChest.getInventory().getSize()), loot);
											}
										}
									}

									break label210;
								}
							}

							++var11;
							continue;
						}
					}

					++var10;
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