package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;

import org.bukkit.World;

public class ComponentVillageTorch extends ComponentVillage {
	private int averageGroundLevel = -1;

	public ComponentVillageTorch(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
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

	/**
	 * Trys to find a valid place to put this component.
	 */
	public static StructureBoundingBox findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5) {
		StructureBoundingBox var6 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 3, 4, 2, par5);
		return StructureComponent.findIntersecting(par0List, var6) != null ? null : var6;
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

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
		}

		fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 2, 3, 1, 0, 0, false);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 0, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 1, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 85, 0, 1, 2, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 35, 15, 1, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 50, 15, 0, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 50, 15, 1, 3, 1, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 50, 15, 2, 3, 0, par3StructureBoundingBox);
		placeBlockAtCurrentPosition(par1World, 50, 15, 1, 3, -1, par3StructureBoundingBox);
		return true;
	}
}