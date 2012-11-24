package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;
import me.heldplayer.HeldGeneration.generator.Structures.StructureVillagePieces;

import org.bukkit.World;

public class ComponentVillageWell extends ComponentVillage {
	private final boolean field_35104_a = true;
	private int averageGroundLevel = -1;

	public ComponentVillageWell(int par1, Random par2Random, int par3, int par4) {
		super(par1);
		this.coordBaseMode = par2Random.nextInt(4);

		switch (this.coordBaseMode) {
		case 0:
		case 2:
			this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 6 - 1, 78, par4 + 6 - 1);
			break;

		default:
			this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 6 - 1, 78, par4 + 6 - 1);
		}
	}

	/**
	 * Initiates construction of the Structure Component picked, at the current
	 * Location of StructGen
	 */
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 1, getComponentType());
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, 3, getComponentType());
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, 2, getComponentType());
		StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece) par1StructureComponent, par2List, par3Random, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, 0, getComponentType());
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

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 3, 0);
		}

		if (this.field_35104_a) {
			;
		}

		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 4, 12, 4, 4, 8, false);
		placeBlockAtCurrentPosition(par1World, 0, 0, 2, 12, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 3, 12, 2, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 2, 12, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 0, 0, 3, 12, 3, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 13, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 14, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 4, 13, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 4, 14, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 13, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 14, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 4, 13, 4, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 4, 14, 4, par3StructureBoundingBox);
		fillWithBlocks(par1World, par3StructureBoundingBox, 1, 15, 1, 4, 15, 4, 4, 4, false);

		for (int var4 = 0; var4 <= 5; ++var4) {
			for (int var5 = 0; var5 <= 5; ++var5) {
				if (var5 == 0 || var5 == 5 || var4 == 0 || var4 == 5) {
					placeBlockAtCurrentPosition(par1World, 13, 0, var5, 11, var4, par3StructureBoundingBox);
					clearCurrentPositionBlocksUpwards(par1World, var5, 12, var4, par3StructureBoundingBox);
				}
			}
		}

		return true;
	}
}
