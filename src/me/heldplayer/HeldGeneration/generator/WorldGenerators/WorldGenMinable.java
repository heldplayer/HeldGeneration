package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;
import me.heldplayer.HeldGeneration.helpers.MathHelper;

import org.bukkit.World;

public class WorldGenMinable extends WorldGenerator {
	/** The block ID of the ore to be placed using this generator. */
	private int minableBlockId;

	/** The number of blocks to generate. */
	private int numberOfBlocks;

	public WorldGenMinable(int typeId, int blockAmount) {
		this.minableBlockId = typeId;
		this.numberOfBlocks = blockAmount;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		float var6 = rand.nextFloat() * (float) Math.PI;
		double var7 = (double) ((float) (x + 8) + MathHelper.sin(var6) * (float) this.numberOfBlocks / 8.0F);
		double var9 = (double) ((float) (x + 8) - MathHelper.sin(var6) * (float) this.numberOfBlocks / 8.0F);
		double var11 = (double) ((float) (z + 8) + MathHelper.cos(var6) * (float) this.numberOfBlocks / 8.0F);
		double var13 = (double) ((float) (z + 8) - MathHelper.cos(var6) * (float) this.numberOfBlocks / 8.0F);
		double var15 = (double) (y + rand.nextInt(3) - 2);
		double var17 = (double) (y + rand.nextInt(3) - 2);

		for (int counter = 0; counter <= this.numberOfBlocks; ++counter) {
			double var20 = var7 + (var9 - var7) * (double) counter / (double) this.numberOfBlocks;
			double var22 = var15 + (var17 - var15) * (double) counter / (double) this.numberOfBlocks;
			double var24 = var11 + (var13 - var11) * (double) counter / (double) this.numberOfBlocks;
			double var26 = rand.nextDouble() * (double) this.numberOfBlocks / 16.0D;
			double var28 = (double) (MathHelper.sin((float) counter * (float) Math.PI / (float) this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
			double var30 = (double) (MathHelper.sin((float) counter * (float) Math.PI / (float) this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
			int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
			int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
			int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
			int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
			int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
			int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

			for (int orePosX = var32; orePosX <= var35; ++orePosX) {
				double relPosX = ((double) orePosX + 0.5D - var20) / (var28 / 2.0D);

				if (relPosX * relPosX < 1.0D) {
					for (int orePosY = var33; orePosY <= var36; ++orePosY) {
						double relPosY = ((double) orePosY + 0.5D - var22) / (var30 / 2.0D);

						if (relPosX * relPosX + relPosY * relPosY < 1.0D) {
							for (int orePosZ = var34; orePosZ <= var37; ++orePosZ) {
								double relPosZ = ((double) orePosZ + 0.5D - var24) / (var28 / 2.0D);

								if (relPosX * relPosX + relPosY * relPosY + relPosZ * relPosZ < 1.0D && world.getBlockTypeIdAt(orePosX, orePosY, orePosZ) == Mat.Stone.id) {
									setBlock(world, orePosX, orePosY, orePosZ, this.minableBlockId);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
