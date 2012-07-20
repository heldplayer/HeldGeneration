package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenLiquids extends WorldGenerator {
	/** The ID of the liquid block used in this liquid generator. */
	private int liquidBlockId;

	public WorldGenLiquids(int par1) {
		this.liquidBlockId = par1;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		if (par1World.getBlockTypeIdAt(par3, par4 + 1, par5) != Mat.Stone.id) {
			return false;
		} else if (par1World.getBlockTypeIdAt(par3, par4 - 1, par5) != Mat.Stone.id) {
			return false;
		} else if (par1World.getBlockTypeIdAt(par3, par4, par5) != 0 && par1World.getBlockTypeIdAt(par3, par4, par5) != Mat.Stone.id) {
			return false;
		} else {
			int var6 = 0;

			if (par1World.getBlockTypeIdAt(par3 - 1, par4, par5) == Mat.Stone.id) {
				++var6;
			}

			if (par1World.getBlockTypeIdAt(par3 + 1, par4, par5) == Mat.Stone.id) {
				++var6;
			}

			if (par1World.getBlockTypeIdAt(par3, par4, par5 - 1) == Mat.Stone.id) {
				++var6;
			}

			if (par1World.getBlockTypeIdAt(par3, par4, par5 + 1) == Mat.Stone.id) {
				++var6;
			}

			int var7 = 0;

			if (par1World.getBlockTypeIdAt(par3 - 1, par4, par5) == 0) {
				++var7;
			}

			if (par1World.getBlockTypeIdAt(par3 + 1, par4, par5) == 0) {
				++var7;
			}

			if (par1World.getBlockTypeIdAt(par3, par4, par5 - 1) == 0) {
				++var7;
			}

			if (par1World.getBlockTypeIdAt(par3, par4, par5 + 1) == 0) {
				++var7;
			}

			if (var6 == 3 && var7 == 1) {
				par1World.getBlockAt(par3, par4, par5).setTypeIdAndData(this.liquidBlockId, (byte) 0, true);
			}

			return true;
		}
	}
}