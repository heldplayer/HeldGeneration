package me.heldplayer.HeldGeneration.GenLayers;

import java.util.ArrayList;
import java.util.List;

public class IntCache {
	private static int intCacheSize = 256;

	/**
	 * A list of pre-allocated int[256] arrays that are currently unused and can
	 * be returned by getIntCache()
	 */
	private static List freeSmallArrays = new ArrayList();

	/**
	 * A list of pre-allocated int[256] arrays that were previously returned by
	 * getIntCache() and which will not be re-
	 * used again until resetIntCache() is called.
	 */
	private static List inUseSmallArrays = new ArrayList();

	/**
	 * A list of pre-allocated int[cacheSize] arrays that are currently unused
	 * and can be returned by getIntCache()
	 */
	private static List freeLargeArrays = new ArrayList();

	/**
	 * A list of pre-allocated int[cacheSize] arrays that were previously
	 * returned by getIntCache() and which will not
	 * be re-used again until resetIntCache() is called.
	 */
	private static List inUseLargeArrays = new ArrayList();

	public static int[] getIntCache(int size) {
		int[] array;

		if (size <= 256) {
			if (freeSmallArrays.size() == 0) {
				array = new int[256];
				inUseSmallArrays.add(array);
				return array;
			} else {
				array = (int[]) freeSmallArrays.remove(freeSmallArrays.size() - 1);
				inUseSmallArrays.add(array);
				return array;
			}
		} else if (size > intCacheSize) {
			intCacheSize = size;
			freeLargeArrays.clear();
			inUseLargeArrays.clear();
			array = new int[intCacheSize];
			inUseLargeArrays.add(array);
			return array;
		} else if (freeLargeArrays.size() == 0) {
			array = new int[intCacheSize];
			inUseLargeArrays.add(array);
			return array;
		} else {
			array = (int[]) freeLargeArrays.remove(freeLargeArrays.size() - 1);
			inUseLargeArrays.add(array);
			return array;
		}
	}

	/**
	 * Mark all pre-allocated arrays as available for re-use by moving them to
	 * the appropriate free lists.
	 */
	public static void resetIntCache() {
		if (freeLargeArrays.size() > 0) {
			freeLargeArrays.remove(freeLargeArrays.size() - 1);
		}

		if (freeSmallArrays.size() > 0) {
			freeSmallArrays.remove(freeSmallArrays.size() - 1);
		}

		freeLargeArrays.addAll(inUseLargeArrays);
		freeSmallArrays.addAll(inUseSmallArrays);
		inUseLargeArrays.clear();
		inUseSmallArrays.clear();
	}
}
