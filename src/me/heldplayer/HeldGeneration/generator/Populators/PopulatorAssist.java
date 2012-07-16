package me.heldplayer.HeldGeneration.generator.Populators;

import java.util.HashMap;
import java.util.Map;

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

	private PopulatorAssist(Biome biome) {
		this.biome = biome;

		assistMap.put(biome, this);
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
