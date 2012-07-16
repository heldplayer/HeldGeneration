package me.heldplayer.HeldGeneration.generator.MapGenerators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.ChunkCoordIntPair;
import me.heldplayer.HeldGeneration.generator.ChunkProviderGenerate;
import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;
import me.heldplayer.HeldGeneration.generator.Structures.StructureStart;

import org.bukkit.Location;
import org.bukkit.World;

public abstract class MapGenStructure extends MapGenBase {
	protected HashMap<Long, StructureStart> coordMap = new HashMap<Long, StructureStart>();

	@Override
	public void generate(World world, int cx, int cz, byte[] chunkBlocks, ChunkProviderGenerate provider) {
		super.generate(world, cx, cz, chunkBlocks, provider);
	}

	/**
	 * Recursively called by generate() (generate) and optionally by itself.
	 */
	@Override
	protected void recursiveGenerate(World world, int cx, int cz, int ccx, int ccz, byte[] chunkBlocks, ChunkProviderGenerate provider) {
		if (!this.coordMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(cx, cz)))) {
			this.rand.nextInt();

			if (canSpawnStructureAtCoords(cx, cz, provider)) {
				StructureStart var7 = getStructureStart(cx, cz);
				this.coordMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(cx, cz)), var7);
			}
		}
	}

	/**
	 * Generates structures in specified chunk next to existing structures. Does
	 * *not* generate StructureStarts.
	 */
	public boolean generateStructuresInChunk(World world, Random rand, int cx, int cz, ChunkProviderGenerate provider) {
		int centX = (cx << 4) + 8;
		int centZ = (cz << 4) + 8;
		boolean hasGenerated = false;
		Iterator<StructureStart> structures = this.coordMap.values().iterator();

		while (structures.hasNext()) {
			StructureStart structure = (StructureStart) structures.next();

			if (structure.isSizeableStructure() && structure.getBoundingBox().intersectsWith(centX, centZ, centX + 15, centZ + 15)) {
				structure.generateStructure(world, rand, new StructureBoundingBox(centX, centZ, centX + 15, centZ + 15));
				hasGenerated = true;
			}
		}

		return hasGenerated;
	}

	//	public boolean func_40483_a(int par1, int par2, int par3) {
	//		Iterator<StructureStart> var4 = this.coordMap.values().iterator();
	//
	//		while (var4.hasNext()) {
	//			StructureStart var5 = (StructureStart) var4.next();
	//
	//			if (var5.isSizeableStructure() && var5.getBoundingBox().intersectsWith(par1, par3, par1, par3)) {
	//				Iterator<StructureComponent> var6 = var5.getComponents().iterator();
	//
	//				while (var6.hasNext()) {
	//					StructureComponent var7 = (StructureComponent) var6.next();
	//
	//					if (var7.getBoundingBox().isVecInside(par1, par2, par3)) {
	//						return true;
	//					}
	//				}
	//			}
	//		}
	//
	//		return false;
	//	}

	public Location getNearestInstance(World par1World, int par2, int par3, int par4, ChunkProviderGenerate provider) {
		this.worldObj = par1World;
		this.rand.setSeed(par1World.getSeed());
		long var5 = this.rand.nextLong();
		long var7 = this.rand.nextLong();
		long var9 = (par2 >> 4) * var5;
		long var11 = (par4 >> 4) * var7;
		this.rand.setSeed(var9 ^ var11 ^ par1World.getSeed());
		recursiveGenerate(par1World, par2 >> 4, par4 >> 4, 0, 0, (byte[]) null, provider);
		double var13 = Double.MAX_VALUE;
		Location var15 = null;
		Iterator<StructureStart> var16 = this.coordMap.values().iterator();
		Location var19;
		int var21;
		int var20;
		double var23;
		int var22;

		while (var16.hasNext()) {
			StructureStart var17 = (StructureStart) var16.next();

			if (var17.isSizeableStructure()) {
				StructureComponent var18 = (StructureComponent) var17.getComponents().get(0);
				var19 = var18.getCenter(par1World);
				var20 = var19.getBlockX() - par2;
				var21 = var19.getBlockY() - par3;
				var22 = var19.getBlockZ() - par4;
				var23 = (var20 + var20 * var21 * var21 + var22 * var22);

				if (var23 < var13) {
					var13 = var23;
					var15 = var19;
				}
			}
		}

		if (var15 != null) {
			return var15;
		} else {
			List var25 = func_40482_a();

			if (var25 != null) {
				Location var26 = null;
				Iterator var27 = var25.iterator();

				while (var27.hasNext()) {
					var19 = (Location) var27.next();
					var20 = var19.getBlockX() - par2;
					var21 = var19.getBlockY() - par3;
					var22 = var19.getBlockZ() - par4;
					var23 = (var20 + var20 * var21 * var21 + var22 * var22);

					if (var23 < var13) {
						var13 = var23;
						var26 = var19;
					}
				}

				return var26;
			} else {
				return null;
			}
		}
	}

	protected List func_40482_a() {
		return null;
	}

	protected abstract boolean canSpawnStructureAtCoords(int cx, int cz, ChunkProviderGenerate provider);

	protected abstract StructureStart getStructureStart(int var1, int var2);
}
