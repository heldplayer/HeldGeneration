package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenDesertWells extends WorldGenerator {
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		while (par1World.getBlockTypeIdAt(par3, par4, par5) == 0 && par4 > 2) {
			--par4;
		}

		int var6 = par1World.getBlockTypeIdAt(par3, par4, par5);

		if (var6 != Mat.Sand.id) {
			return false;
		} else {
			int var7;
			int var8;

			for (var7 = -2; var7 <= 2; ++var7) {
				for (var8 = -2; var8 <= 2; ++var8) {
					if (par1World.getBlockTypeIdAt(par3 + var7, par4 - 1, par5 + var8) == 0 && par1World.getBlockTypeIdAt(par3 + var7, par4 - 2, par5 + var8) == 0) {
						return false;
					}
				}
			}

			for (var7 = -1; var7 <= 0; ++var7) {
				for (var8 = -2; var8 <= 2; ++var8) {
					for (int var9 = -2; var9 <= 2; ++var9) {
						setBlock(par1World, par3 + var8, par4 + var7, par5 + var9, Mat.Sandstone.id);
					}
				}
			}

			setBlock(par1World, par3, par4, par5, Mat.WaterMoving.id);
			setBlock(par1World, par3 - 1, par4, par5, Mat.WaterMoving.id);
			setBlock(par1World, par3 + 1, par4, par5, Mat.WaterMoving.id);
			setBlock(par1World, par3, par4, par5 - 1, Mat.WaterMoving.id);
			setBlock(par1World, par3, par4, par5 + 1, Mat.WaterMoving.id);

			for (var7 = -2; var7 <= 2; ++var7) {
				for (var8 = -2; var8 <= 2; ++var8) {
					if (var7 == -2 || var7 == 2 || var8 == -2 || var8 == 2) {
						setBlock(par1World, par3 + var7, par4 + 1, par5 + var8, Mat.Sandstone.id);
					}
				}
			}

			setBlockAndMetadata(par1World, par3 + 2, par4 + 1, par5, Mat.SingleSlab.id, 1);
			setBlockAndMetadata(par1World, par3 - 2, par4 + 1, par5, Mat.SingleSlab.id, 1);
			setBlockAndMetadata(par1World, par3, par4 + 1, par5 + 2, Mat.SingleSlab.id, 1);
			setBlockAndMetadata(par1World, par3, par4 + 1, par5 - 2, Mat.SingleSlab.id, 1);

			for (var7 = -1; var7 <= 1; ++var7) {
				for (var8 = -1; var8 <= 1; ++var8) {
					if (var7 == 0 && var8 == 0) {
						setBlock(par1World, par3 + var7, par4 + 4, par5 + var8, Mat.Sandstone.id);
					} else {
						setBlockAndMetadata(par1World, par3 + var7, par4 + 4, par5 + var8, Mat.SingleSlab.id, 1);
					}
				}
			}

			for (var7 = 1; var7 <= 3; ++var7) {
				setBlock(par1World, par3 - 1, par4 + var7, par5 - 1, Mat.Sandstone.id);
				setBlock(par1World, par3 - 1, par4 + var7, par5 + 1, Mat.Sandstone.id);
				setBlock(par1World, par3 + 1, par4 + var7, par5 - 1, Mat.Sandstone.id);
				setBlock(par1World, par3 + 1, par4 + var7, par5 + 1, Mat.Sandstone.id);
			}

			return true;
		}
	}
}