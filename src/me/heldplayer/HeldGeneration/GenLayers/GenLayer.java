package me.heldplayer.HeldGeneration.GenLayers;

import org.bukkit.WorldType;

public abstract class GenLayer {
	/** seed from World#getWorldSeed that is used in the LCG prng */
	private long worldGenSeed;

	/** parent GenLayer that was provided via the constructor */
	protected GenLayer parent;

	/**
	 * final part of the LCG prng that uses the chunk X, Z coords along with the
	 * other two seeds to generate
	 * pseudorandom numbers
	 */
	private long chunkSeed;

	/** base seed to the LCG prng provided via the constructor */
	private long baseSeed;

	public static GenLayer[] func_48425_a(long seed, WorldType world) {
		GenLayerIsland islandLayer = new GenLayerIsland(1L);
		GenLayerFuzzyZoom fuzzyIslandLayer = new GenLayerFuzzyZoom(2000L, islandLayer);
		GenLayerAddIsland islandAddLayer = new GenLayerAddIsland(1L, fuzzyIslandLayer);
		GenLayerZoom zoomLayer = new GenLayerZoom(2001L, islandAddLayer);
		islandAddLayer = new GenLayerAddIsland(2L, zoomLayer);
		GenLayerAddSnow snowLayer = new GenLayerAddSnow(2L, islandAddLayer);
		zoomLayer = new GenLayerZoom(2002L, snowLayer);
		islandAddLayer = new GenLayerAddIsland(3L, zoomLayer);
		zoomLayer = new GenLayerZoom(2003L, islandAddLayer);
		islandAddLayer = new GenLayerAddIsland(4L, zoomLayer);
		GenLayerAddMushroomIsland mushroomIslandLayer = new GenLayerAddMushroomIsland(5L, islandAddLayer);
		byte var4 = 4;
		GenLayer zoomedLayer = GenLayerZoom.zoom(1000L, mushroomIslandLayer, 0);
		GenLayerRiverInit riverInitLayer = new GenLayerRiverInit(100L, zoomedLayer);
		zoomedLayer = GenLayerZoom.zoom(1000L, riverInitLayer, var4 + 2);
		GenLayerRiver riverLayer = new GenLayerRiver(1L, zoomedLayer);
		GenLayerSmooth smoothLayer = new GenLayerSmooth(1000L, riverLayer);
		GenLayer zoomedLayer2 = GenLayerZoom.zoom(1000L, mushroomIslandLayer, 0);
		GenLayerBiome biomeLayer = new GenLayerBiome(200L, zoomedLayer2, world);
		zoomedLayer2 = GenLayerZoom.zoom(1000L, biomeLayer, 2);
		Object hillsLayer = new GenLayerHills(1000L, zoomedLayer2);

		for (int counter = 0; counter < var4; ++counter) {
			hillsLayer = new GenLayerZoom((long) (1000 + counter), (GenLayer) hillsLayer);

			if (counter == 0) {
				hillsLayer = new GenLayerAddIsland(3L, (GenLayer) hillsLayer);
			}

			if (counter == 1) {
				hillsLayer = new GenLayerShore(1000L, (GenLayer) hillsLayer);
			}

			if (counter == 1) {
				hillsLayer = new GenLayerSwampRivers(1000L, (GenLayer) hillsLayer);
			}
		}

		GenLayerSmooth smoothLayer2 = new GenLayerSmooth(1000L, (GenLayer) hillsLayer);
		GenLayerRiverMix riverMixLayer = new GenLayerRiverMix(100L, smoothLayer2, smoothLayer);
		GenLayerVoronoiZoom zoomLayer2 = new GenLayerVoronoiZoom(10L, riverMixLayer);
		riverMixLayer.initWorldGenSeed(seed);
		zoomLayer2.initWorldGenSeed(seed);
		return new GenLayer[] { riverMixLayer, zoomLayer2, riverMixLayer };
	}

	public GenLayer(long seed) {
		this.baseSeed = seed;
		this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
		this.baseSeed += seed;
		this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
		this.baseSeed += seed;
		this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
		this.baseSeed += seed;
	}

	/**
	 * Initialize layer's local worldGenSeed based on its own baseSeed and the
	 * world's global seed (passed in as an
	 * argument).
	 */
	public void initWorldGenSeed(long seed) {
		this.worldGenSeed = seed;

		if (this.parent != null) {
			this.parent.initWorldGenSeed(seed);
		}

		this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		this.worldGenSeed += this.baseSeed;
		this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		this.worldGenSeed += this.baseSeed;
		this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		this.worldGenSeed += this.baseSeed;
	}

	/**
	 * Initialize layer's current chunkSeed based on the local worldGenSeed and
	 * the (x,z) chunk coordinates.
	 */
	public void initChunkSeed(long cx, long cz) {
		this.chunkSeed = this.worldGenSeed;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += cx;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += cz;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += cx;
		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += cz;
	}

	/**
	 * returns a LCG pseudo random number from [0, x). Args: int x
	 */
	protected int nextInt(int max) {
		int var2 = (int) ((this.chunkSeed >> 24) % max);

		if (var2 < 0) {
			var2 += max;
		}

		this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
		this.chunkSeed += this.worldGenSeed;
		return var2;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be
	 * interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer
	 * subclass.
	 */
	public abstract int[] getInts(int startX, int startZ, int sizeX, int sizeZ);
}
