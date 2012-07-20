package me.heldplayer.HeldGeneration.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Populators.BiomeDecorator;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenBigTree;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenForest;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenHugeTrees;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenShrub;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenSwamp;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenTaiga1;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenTaiga2;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenTrees;
import me.heldplayer.HeldGeneration.generator.WorldGenerators.WorldGenerator;

import org.bukkit.block.Biome;

public class PopulatorAssist {
	private static Map<Biome, PopulatorAssist> assistMap = new HashMap<Biome, PopulatorAssist>();

	public static PopulatorAssist getAssist(Biome biome) {
		if (assistMap.containsKey(biome)) {
			return assistMap.get(biome);
		}

		return new PopulatorAssist(biome);
	}

	public int waterlilyPerChunk = 0;
	public int treesPerChunk = 0;
	public int flowersPerChunk = 2;
	public int grassPerChunk = 1;
	public int deadBushPerChunk = 0;
	public int mushroomsPerChunk = 0;
	public int reedsPerChunk = 0;
	public int cactiPerChunk = 0;
	public int sandPerChunk = 1;
	public int sandPerChunk2 = 3;
	public int clayPerChunk = 1;
	public int bigMushroomsPerChunk = 0;
	public boolean generateLakes = true;
	public final Biome biome;
	public static BiomeDecorator decorator = new BiomeDecorator();
	public WorldGenTrees worldGenTrees = new WorldGenTrees(false);
	public WorldGenBigTree worldGenBigTree = new WorldGenBigTree(false);
	public WorldGenForest worldGenForest = new WorldGenForest(false);
	public WorldGenSwamp worldGenSwamp = new WorldGenSwamp();
	public WorldGenTaiga1 worldGenTaiga1 = new WorldGenTaiga1();
	public WorldGenTaiga2 worldGenTaiga2 = new WorldGenTaiga2(false);
	public WorldGenShrub worldGenShrub = new WorldGenShrub(3, 0);

	private PopulatorAssist(Biome biome) {
		this.biome = biome;

		assistMap.put(biome, this);
	}

	public WorldGenerator getRandomTreeGen(Random rand) {
		if (biome == Biome.FOREST || biome == Biome.FOREST_HILLS) {
			if (rand.nextInt(5) == 0) {
				return this.worldGenForest;
			}
		}

		if (biome == Biome.SWAMPLAND) {
			return worldGenSwamp;
		}

		if (biome == Biome.TAIGA || biome == Biome.TAIGA_HILLS) {
			return (rand.nextInt(3) == 0 ? this.worldGenTaiga1 : this.worldGenTaiga2);
		}

		if (biome == Biome.JUNGLE || biome == Biome.JUNGLE_HILLS) {
			if (rand.nextInt(10) == 0) {
				return this.worldGenBigTree;
			} else if (rand.nextInt(2) == 0) {
				return this.worldGenShrub;
			} else if (rand.nextInt(3) == 0) {
				return new WorldGenHugeTrees(false, 10 + rand.nextInt(20), 3, 3);
			} else {
				return new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
			}
		}

		return (rand.nextInt(10) == 0 ? this.worldGenBigTree : this.worldGenTrees);
	}

	static {
		// Beach
		PopulatorAssist beachAssist = new PopulatorAssist(Biome.BEACH);
		beachAssist.treesPerChunk = -999;
		beachAssist.deadBushPerChunk = 0;
		beachAssist.reedsPerChunk = 0;
		beachAssist.cactiPerChunk = 0;
		// Desert
		PopulatorAssist desertAssist = new PopulatorAssist(Biome.DESERT);
		desertAssist.treesPerChunk = -999;
		desertAssist.deadBushPerChunk = 2;
		desertAssist.reedsPerChunk = 50;
		desertAssist.cactiPerChunk = 10;
		PopulatorAssist desertHillsAssist = new PopulatorAssist(Biome.DESERT_HILLS);
		desertHillsAssist.treesPerChunk = -999;
		desertHillsAssist.deadBushPerChunk = 2;
		desertHillsAssist.reedsPerChunk = 50;
		desertHillsAssist.cactiPerChunk = 10;
		// Forest
		PopulatorAssist forestAssist = new PopulatorAssist(Biome.FOREST);
		forestAssist.treesPerChunk = 10;
		forestAssist.grassPerChunk = 2;
		PopulatorAssist forestHillsAssist = new PopulatorAssist(Biome.FOREST_HILLS);
		forestHillsAssist.treesPerChunk = 10;
		forestHillsAssist.grassPerChunk = 2;
		// Jungle
		PopulatorAssist jungleAssist = new PopulatorAssist(Biome.JUNGLE);
		jungleAssist.treesPerChunk = 50;
		jungleAssist.grassPerChunk = 25;
		jungleAssist.flowersPerChunk = 4;
		PopulatorAssist jungleHillsAssist = new PopulatorAssist(Biome.JUNGLE_HILLS);
		jungleHillsAssist.treesPerChunk = 50;
		jungleHillsAssist.grassPerChunk = 25;
		jungleHillsAssist.flowersPerChunk = 4;
		// Mushroom Island
		PopulatorAssist mushroomIslandAssist = new PopulatorAssist(Biome.MUSHROOM_ISLAND);
		mushroomIslandAssist.treesPerChunk = -100;
		mushroomIslandAssist.grassPerChunk = -100;
		mushroomIslandAssist.flowersPerChunk = -100;
		mushroomIslandAssist.mushroomsPerChunk = 1;
		mushroomIslandAssist.bigMushroomsPerChunk = 1;
		PopulatorAssist mushroomIslandShoreAssist = new PopulatorAssist(Biome.MUSHROOM_SHORE);
		mushroomIslandShoreAssist.treesPerChunk = -100;
		mushroomIslandShoreAssist.grassPerChunk = -100;
		mushroomIslandShoreAssist.flowersPerChunk = -100;
		mushroomIslandShoreAssist.mushroomsPerChunk = 1;
		mushroomIslandShoreAssist.bigMushroomsPerChunk = 1;
		// Plains
		PopulatorAssist plainsAssist = new PopulatorAssist(Biome.PLAINS);
		plainsAssist.treesPerChunk = -999;
		plainsAssist.grassPerChunk = 10;
		plainsAssist.flowersPerChunk = 4;
		// Swamp
		PopulatorAssist swampAssist = new PopulatorAssist(Biome.SWAMPLAND);
		swampAssist.treesPerChunk = 2;
		swampAssist.flowersPerChunk = -999;
		swampAssist.deadBushPerChunk = 1;
		swampAssist.mushroomsPerChunk = 8;
		swampAssist.reedsPerChunk = 10;
		swampAssist.clayPerChunk = 1;
		swampAssist.waterlilyPerChunk = 4;
		// Taiga
		PopulatorAssist taigaAssist = new PopulatorAssist(Biome.TAIGA);
		taigaAssist.treesPerChunk = 10;
		taigaAssist.grassPerChunk = 1;
		PopulatorAssist taigaHillsAssist = new PopulatorAssist(Biome.TAIGA_HILLS);
		taigaHillsAssist.treesPerChunk = 10;
		taigaHillsAssist.grassPerChunk = 1;
	}
}
