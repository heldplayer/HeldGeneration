package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;

import org.bukkit.World;

public class ComponentVillageHouse4_Garden extends ComponentVillage {
	private int averageGroundLevel = -1;
	private final boolean isRoofAccessible;

	public ComponentVillageHouse4_Garden(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
		super(par1);
		this.coordBaseMode = par4;
		this.boundingBox = par3StructureBoundingBox;
		this.isRoofAccessible = par2Random.nextBoolean();
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current
	 * Location of StructGen
	 */
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
	}

	public static ComponentVillageHouse4_Garden findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
		StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 5, 6, 5, par5);
		return StructureComponent.findIntersecting(par0List, var7) != null ? null : new ComponentVillageHouse4_Garden(par6, par1Random, var7, par5);
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs,
	 * Mob Spawners, it closes Mineshafts at
	 * the end, it adds Fences...
	 */
	@Override
	public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
		if (this.averageGroundLevel < 0) {
			this.averageGroundLevel = getAverageGroundLevel(par1World, par3StructureBoundingBox);

			if (this.averageGroundLevel < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
		}

		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 0, 4, 4, 4, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, 4, 4, 4, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 3, 4, 3, 5, 5, false);
		placeBlockAtCurrentPosition(par1World, 4, 0, 0, 1, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 0, 2, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 0, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 4, 1, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 4, 2, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 4, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 0, 1, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 0, 2, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 0, 3, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 4, 1, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 4, 2, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 4, 0, 4, 3, 4, par3StructureBoundingBox);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 3, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 4, 3, 3, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 4, 3, 3, 4, 5, 5, false);
		placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 2, 2, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 4, 2, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 1, 1, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 1, 2, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 1, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 2, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 3, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 3, 2, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 3, 1, 0, par3StructureBoundingBox);

		if (getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0) {
			placeBlockAtCurrentPosition(par1World, 67, getMetadataWithOffset(67, 3), 2, 0, -1, par3StructureBoundingBox);
		}

		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 3, 3, 0, 0, false);

		if (this.isRoofAccessible) {
			placeBlockAtCurrentPosition(par1World, 85, 0, 0, 5, 0, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 1, 5, 0, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 2, 5, 0, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 3, 5, 0, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 4, 5, 0, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 0, 5, 4, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 1, 5, 4, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 2, 5, 4, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 3, 5, 4, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 4, 5, 4, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 4, 5, 1, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 4, 5, 2, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 4, 5, 3, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 0, 5, 1, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 0, 5, 2, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 85, 0, 0, 5, 3, par3StructureBoundingBox);
		}

		int var4;

		if (this.isRoofAccessible) {
			var4 = getMetadataWithOffset(65, 3);
			placeBlockAtCurrentPosition(par1World, 65, var4, 3, 1, 3, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 65, var4, 3, 2, 3, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 65, var4, 3, 3, 3, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 65, var4, 3, 4, 3, par3StructureBoundingBox);
		}

		placeBlockAtCurrentPosition(par1World, 50, 0, 2, 3, 1, par3StructureBoundingBox);

		for (var4 = 0; var4 < 5; ++var4) {
			for (int var5 = 0; var5 < 5; ++var5) {
				clearCurrentPositionBlocksUpwards(par1World, var5, 6, var4, par3StructureBoundingBox);
				fillCurrentPositionBlocksDownwards(par1World, 4, 0, var5, -1, var4, par3StructureBoundingBox);
			}
		}

		spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
		return true;
	}
}