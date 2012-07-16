package me.heldplayer.HeldGeneration;

import org.bukkit.Material;

public class BlockHelper {
	public static boolean isLiquid(Material mat) {
		return isLiquid(mat.getId());
	}

	public static boolean isLiquid(int id) {
		switch (id) {
		case 8:
		case 9:
		case 10:
		case 11:
			return true;
		}
		return false;
	}

	public static boolean isSolid(Material mat) {
		return isSolid(mat.getId());
	}

	public static boolean isSolid(int id) {
		switch (id) {
		case 0:
		case 8:
		case 9:
		case 10:
		case 11:
			return false;
		}
		return true;
	}
}
