package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;
import me.heldplayer.HeldGeneration.generator.Structures.StructurePieceTreasure;

import org.bukkit.World;

public class ComponentVillageHouse2 extends ComponentVillage {
	private static final StructurePieceTreasure[] chestLoot = new StructurePieceTreasure[] {
			new StructurePieceTreasure(264, 0, 1, 3, 3), new StructurePieceTreasure(265, 0, 1, 5, 10), new StructurePieceTreasure(266, 0, 1, 3, 5),
			new StructurePieceTreasure(297, 0, 1, 3, 15), new StructurePieceTreasure(260, 0, 1, 3, 15), new StructurePieceTreasure(257, 0, 1, 1, 5),
			new StructurePieceTreasure(267, 0, 1, 1, 5), new StructurePieceTreasure(307, 0, 1, 1, 5), new StructurePieceTreasure(306, 0, 1, 1, 5),
			new StructurePieceTreasure(308, 0, 1, 1, 5), new StructurePieceTreasure(309, 0, 1, 1, 5), new StructurePieceTreasure(49, 0, 3, 7, 5),
			new StructurePieceTreasure(6, 0, 3, 7, 5) };
	private int averageGroundLevel = -1;
	private boolean hasMadeChest;

	public ComponentVillageHouse2(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
		super(par1);
		this.coordBaseMode = par4;
		this.boundingBox = par3StructureBoundingBox;
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current
	 * Location of StructGen
	 */
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
	}

	public static ComponentVillageHouse2 findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
		StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 10, 6, 7, par5);
		return canVillageGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentVillageHouse2(par6, par1Random, var7, par5) : null;
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

		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 9, 4, 6, 0, 0, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 9, 0, 6, 4, 4, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, 9, 4, 6, 4, 4, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 9, 5, 6, 44, 44, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 8, 5, 5, 0, 0, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 0, 2, 3, 0, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 4, 0, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 0, 3, 4, 0, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 6, 0, 4, 6, 17, 17, false);
		placeBlockAtCurrentPosition(par1World, 5, 0, 3, 3, 1, par3StructureBoundingBox);
		fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 2, 3, 3, 2, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 3, 5, 3, 3, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 5, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 6, 5, 3, 6, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 5, 1, 0, 5, 3, 0, 85, 85, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, 0, 9, 3, 0, 85, 85, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 4, 9, 4, 6, 4, 4, false);
		placeBlockAtCurrentPosition(par1World, 10, 0, 7, 1, 5, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 10, 0, 8, 1, 5, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 101, 0, 9, 2, 5, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 101, 0, 9, 2, 4, par3StructureBoundingBox);
		fillWithBlocks(par1World, par3StructureBoundingBox, 7, 2, 4, 8, 2, 5, 0, 0, false);
		placeBlockAtCurrentPosition(par1World, 4, 0, 6, 1, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 61, 0, 6, 2, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 61, 0, 6, 3, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 43, 0, 8, 1, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 2, 2, 6, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 4, 2, 6, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 2, 1, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 72, 0, 2, 2, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 5, 0, 1, 1, 5, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 53, getMetadataWithOffset(53, 3), 2, 1, 5, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 53, getMetadataWithOffset(53, 1), 1, 1, 4, par3StructureBoundingBox);
		int var4;
		int var5;

		if (!this.hasMadeChest) {
			var4 = getYWithOffset(1);
			var5 = getXWithOffset(5, 5);
			int var6 = getZWithOffset(5, 5);

			if (par3StructureBoundingBox.isVecInside(var5, var4, var6)) {
				this.hasMadeChest = true;
				createTreasureChestAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 5, 1, 5, chestLoot, 3 + par2Random.nextInt(6));
			}
		}

		for (var4 = 6; var4 <= 8; ++var4) {
			if (getBlockIdAtCurrentPosition(par1World, var4, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, var4, -1, -1, par3StructureBoundingBox) != 0) {
				placeBlockAtCurrentPosition(par1World, 67, getMetadataWithOffset(67, 3), var4, 0, -1, par3StructureBoundingBox);
			}
		}

		for (var4 = 0; var4 < 7; ++var4) {
			for (var5 = 0; var5 < 10; ++var5) {
				clearCurrentPositionBlocksUpwards(par1World, var5, 6, var4, par3StructureBoundingBox);
				fillCurrentPositionBlocksDownwards(par1World, 4, 0, var5, -1, var4, par3StructureBoundingBox);
			}
		}

		spawnVillagers(par1World, par3StructureBoundingBox, 7, 1, 1, 1);
		return true;
	}

	/**
	 * Returns the villager type to spawn in this component, based on the number
	 * of villagers already spawned.
	 */
	@Override
	protected int getVillagerType(int par1) {
		return 3;
	}
}