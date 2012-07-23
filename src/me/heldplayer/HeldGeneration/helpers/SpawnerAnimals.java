package me.heldplayer.HeldGeneration.helpers;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

public class SpawnerAnimals {

	/**
	 * Returns whether or not the specified creature type can spawn at the
	 * specified location.
	 */
	public static boolean canCreatureTypeSpawnAtLocation(EntityType entityType, World world, int x, int y, int z) {
		int typeId = world.getBlockTypeIdAt(x, y - 1, z);
		return BlockHelper.isNormalCube(typeId) && typeId != Mat.Bedrock.id && !BlockHelper.isNormalCube(world.getBlockTypeIdAt(x, y, z)) && !BlockHelper.isLiquid(world.getBlockTypeIdAt(x, y, z)) && !BlockHelper.isNormalCube(world.getBlockTypeIdAt(x, y + 1, z));
	}

	/**
	 * Called during chunk generation to spawn initial creatures.
	 */
	public static void performWorldGenSpawning(World world, Biome biome, int startX, int startZ, int sizeX, int sizeZ, Random rand, PopulatorAssist assist) {
		List<SpawnListEntry> spawnableCreatures = assist.spawnableCreatures;

		if (!spawnableCreatures.isEmpty()) {
			while (rand.nextFloat() < assist.spawningChance) {
				SpawnListEntry entry = (SpawnListEntry) WeightedRandom.getRandomItem(assist.getRandomSeed(), spawnableCreatures);
				int amount = entry.minGroupCount + rand.nextInt(1 + entry.maxGroupCount - entry.minGroupCount);
				int spawnLocX = startX + rand.nextInt(sizeX);
				int spawnLocZ = startZ + rand.nextInt(sizeZ);
				int otherSpawnLocX = spawnLocX;
				int otherSpawnLocZ = spawnLocZ;

				for (int count = 0; count < amount; ++count) {
					boolean spawned = false;

					for (int attempts = 0; !spawned && attempts < 4; ++attempts) {
						int var17 = world.getHighestBlockYAt(spawnLocX, spawnLocZ);

						if (canCreatureTypeSpawnAtLocation(entry.entityType, world, spawnLocX, var17, spawnLocZ)) {
							float posX = (float) spawnLocX + 0.5F;
							float posY = (float) var17;
							float posZ = (float) spawnLocZ + 0.5F;
							world.spawnCreature(new Location(world, (double) posX, (double) posY, (double) posZ, rand.nextFloat() * 360.0F, 0.0F), entry.entityType);

							spawned = true;
						}

						spawnLocX += rand.nextInt(5) - rand.nextInt(5);

						for (spawnLocZ += rand.nextInt(5) - rand.nextInt(5); spawnLocX < startX || spawnLocX >= startX + sizeX || spawnLocZ < startZ || spawnLocZ >= startZ + sizeX; spawnLocZ = otherSpawnLocZ + rand.nextInt(5) - rand.nextInt(5)) {
							spawnLocX = otherSpawnLocX + rand.nextInt(5) - rand.nextInt(5);
						}
					}
				}
			}
		}
	}
}
