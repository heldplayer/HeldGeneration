package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;

import org.bukkit.World;

public class ComponentVillageWoodHut extends ComponentVillage {
	private int averageGroundLevel = -1;
	private final boolean isTallHouse;
	private final int tablePosition;

	public ComponentVillageWoodHut(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
		super(par1);
		this.coordBaseMode = par4;
		this.boundingBox = par3StructureBoundingBox;
		this.isTallHouse = par2Random.nextBoolean();
		this.tablePosition = par2Random.nextInt(3);
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current
	 * Location of StructGen
	 */
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
	}

	public static ComponentVillageWoodHut findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
		StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 4, 6, 5, par5);
		return canVillageGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentVillageWoodHut(par6, par1Random, var7, par5) : null;
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

		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 5, 4, 0, 0, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 3, 0, 4, 4, 4, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 3, 3, 3, false);

		if (this.isTallHouse) {
			fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 2, 4, 3, 17, 17, false);
		} else {
			fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 2, 5, 3, 17, 17, false);
		}

		placeBlockAtCurrentPosition(par1World, 17, 0, 1, 4, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 2, 4, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 1, 4, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 2, 4, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 0, 4, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 0, 4, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 0, 4, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 3, 4, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 3, 4, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 17, 0, 3, 4, 3, par3StructureBoundingBox);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 3, 0, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 0, 3, 3, 0, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 4, 0, 3, 4, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 4, 3, 3, 4, 17, 17, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 3, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, 1, 3, 3, 3, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 0, 2, 3, 0, 5, 5, false);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 4, 2, 3, 4, 5, 5, false);
		placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 102, 0, 3, 2, 2, par3StructureBoundingBox);

		if (this.tablePosition > 0) {
			placeBlockAtCurrentPosition(par1World, 85, 0, this.tablePosition, 1, 3, par3StructureBoundingBox);
			placeBlockAtCurrentPosition(par1World, 72, 0, this.tablePosition, 2, 3, par3StructureBoundingBox);
		}

		placeBlockAtCurrentPosition(par1World, 0, 0, 1, 1, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 1, 2, 0, par3StructureBoundingBox);
		placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, getMetadataWithOffset(64, 1));

		if (getBlockIdAtCurrentPosition(par1World, 1, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 1, -1, -1, par3StructureBoundingBox) != 0) {
			placeBlockAtCurrentPosition(par1World, 67, getMetadataWithOffset(67, 3), 1, 0, -1, par3StructureBoundingBox);
		}

		for (int var4 = 0; var4 < 5; ++var4) {
			for (int var5 = 0; var5 < 4; ++var5) {
				clearCurrentPositionBlocksUpwards(par1World, var5, 6, var4, par3StructureBoundingBox);
				fillCurrentPositionBlocksDownwards(par1World, 4, 0, var5, -1, var4, par3StructureBoundingBox);
			}
		}

		spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
		return true;
	}
}