package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;

import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.inventory.ItemStack;

public class WorldGenDungeons extends WorldGenerator {
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		byte var6 = 3;
		int var7 = par2Random.nextInt(2) + 2;
		int var8 = par2Random.nextInt(2) + 2;
		int var9 = 0;
		int var10;
		int var11;
		int var12;

		for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10) {
			for (var11 = par4 - 1; var11 <= par4 + var6 + 1; ++var11) {
				for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12) {
					int var13 = par1World.getBlockTypeIdAt(var10, var11, var12);

					if (var11 == par4 - 1 && !BlockHelper.isSolid(var13)) {
						return false;
					}

					if (var11 == par4 + var6 + 1 && !BlockHelper.isSolid(var13)) {
						return false;
					}

					if ((var10 == par3 - var7 - 1 || var10 == par3 + var7 + 1 || var12 == par5 - var8 - 1 || var12 == par5 + var8 + 1) && var11 == par4 && par1World.getBlockTypeIdAt(var10, var11, var12) == 0 && par1World.getBlockTypeIdAt(var10, var11 + 1, var12) == 0) {
						++var9;
					}
				}
			}
		}

		if (var9 >= 1 && var9 <= 5) {
			for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10) {
				for (var11 = par4 + var6; var11 >= par4 - 1; --var11) {
					for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12) {
						if (var10 != par3 - var7 - 1 && var11 != par4 - 1 && var12 != par5 - var8 - 1 && var10 != par3 + var7 + 1 && var11 != par4 + var6 + 1 && var12 != par5 + var8 + 1) {
							par1World.getBlockAt(var10, var11, var12).setTypeId(0);
						} else if (var11 >= 0 && !BlockHelper.isSolid(par1World.getBlockTypeIdAt(var10, var11 - 1, var12))) {
							par1World.getBlockAt(var10, var11, var12).setTypeId(0);
						} else if (BlockHelper.isSolid(par1World.getBlockTypeIdAt(var10, var11, var12))) {
							if (var11 == par4 - 1 && par2Random.nextInt(4) != 0) {
								par1World.getBlockAt(var10, var11, var12).setTypeId(48);
							} else {
								par1World.getBlockAt(var10, var11, var12).setTypeId(4);
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
							var12 = par3 + par2Random.nextInt(var7 * 2 + 1) - var7;
							int var14 = par5 + par2Random.nextInt(var8 * 2 + 1) - var8;

							if (par1World.getBlockTypeIdAt(var12, par4, var14) == 0) {
								int var15 = 0;

								if (BlockHelper.isSolid(par1World.getBlockTypeIdAt(var12 - 1, par4, var14))) {
									++var15;
								}

								if (BlockHelper.isSolid(par1World.getBlockTypeIdAt(var12 + 1, par4, var14))) {
									++var15;
								}

								if (BlockHelper.isSolid(par1World.getBlockTypeIdAt(var12, par4, var14 - 1))) {
									++var15;
								}

								if (BlockHelper.isSolid(par1World.getBlockTypeIdAt(var12, par4, var14 + 1))) {
									++var15;
								}

								if (var15 == 1) {
									par1World.getBlockAt(var12, par4, var14).setTypeId(54);
									Chest var16 = (Chest) par1World.getBlockAt(var12, par4, var14).getState();

									if (var16 != null) {
										for (int var17 = 0; var17 < 8; ++var17) {
											ItemStack var18 = pickCheckLootItem(par2Random);

											if (var18 != null) {
												var16.getInventory().setItem(par2Random.nextInt(var16.getInventory().getSize()), var18);
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

			par1World.getBlockAt(par3, par4, par5).setTypeId(52);
			CreatureSpawner var19 = (CreatureSpawner) par1World.getBlockAt(par3, par4, par5).getState();

			if (var19 != null) {
				var19.setCreatureTypeByName(pickMobSpawner(par2Random));
			} else {
				System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Picks potentially a random item to add to a dungeon chest.
	 */
	private ItemStack pickCheckLootItem(Random par1Random) {
		int var2 = par1Random.nextInt(11);
		return var2 == 0 ? new ItemStack(329) : (var2 == 1 ? new ItemStack(265, par1Random.nextInt(4) + 1) : (var2 == 2 ? new ItemStack(297) : (var2 == 3 ? new ItemStack(296, par1Random.nextInt(4) + 1) : (var2 == 4 ? new ItemStack(289, par1Random.nextInt(4) + 1) : (var2 == 5 ? new ItemStack(287, par1Random.nextInt(4) + 1) : (var2 == 6 ? new ItemStack(325) : (var2 == 7 && par1Random.nextInt(100) == 0 ? new ItemStack(322) : (var2 == 8 && par1Random.nextInt(2) == 0 ? new ItemStack(331, par1Random.nextInt(4) + 1) : (var2 == 9 && par1Random.nextInt(10) == 0 ? new ItemStack(2256 + par1Random.nextInt(2)) : (var2 == 10 ? new ItemStack(351, 1, (short) 3) : null))))))))));
	}

	/**
	 * Randomly decides which spawner to use in a dungeon
	 */
	private String pickMobSpawner(Random par1Random) {
		int var2 = par1Random.nextInt(4);
		return var2 == 0 ? "Skeleton" : (var2 == 1 ? "Zombie" : (var2 == 2 ? "Zombie" : (var2 == 3 ? "Spider" : "")));
	}
}