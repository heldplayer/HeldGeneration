
package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;

import org.bukkit.World;

public class ComponentVillageHouse1 extends ComponentVillage {
    private int averageGroundLevel = -1;

    public ComponentVillageHouse1(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
        super(par1);
        this.coordBaseMode = par4;
        this.boundingBox = par3StructureBoundingBox;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current
     * Location of StructGen
     */
    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {}

    /**
     * Trys to find a valid place to put this component.
     */
    public static ComponentVillageHouse1 findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 9, 9, 6, par5);
        return canVillageGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentVillageHouse1(par6, par1Random, var7, par5) : null;
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

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 9 - 1, 0);
        }

        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 5, 4, 0, 0, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 8, 0, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 8, 5, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 1, 8, 6, 4, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 7, 2, 8, 7, 3, 4, 4, false);
        int var4 = getMetadataWithOffset(53, 3);
        int var5 = getMetadataWithOffset(53, 2);
        int var6;
        int var7;

        for (var6 = -1; var6 <= 2; ++var6) {
            for (var7 = 0; var7 <= 8; ++var7) {
                placeBlockAtCurrentPosition(par1World, 53, var4, var7, 6 + var6, var6, par3StructureBoundingBox);
                placeBlockAtCurrentPosition(par1World, 53, var5, var7, 6 + var6, 5 - var6, par3StructureBoundingBox);
            }
        }

        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 1, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 5, 8, 1, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 1, 0, 8, 1, 4, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 0, 7, 1, 0, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 4, 0, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 4, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 5, 8, 4, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 0, 8, 4, 0, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 1, 0, 4, 4, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 7, 4, 5, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 2, 1, 8, 4, 4, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 4, 0, 5, 5, false);
        placeBlockAtCurrentPosition(par1World, 102, 0, 4, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 5, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 6, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 4, 3, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 5, 3, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 6, 3, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 0, 3, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 0, 3, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 2, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 3, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 3, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 2, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 3, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 5, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 6, 2, 5, par3StructureBoundingBox);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 1, 7, 4, 1, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 4, 7, 4, 4, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 7, 3, 4, 47, 47, false);
        placeBlockAtCurrentPosition(par1World, 5, 0, 7, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 53, getMetadataWithOffset(53, 0), 7, 1, 3, par3StructureBoundingBox);
        var6 = getMetadataWithOffset(53, 3);
        placeBlockAtCurrentPosition(par1World, 53, var6, 6, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 53, var6, 5, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 53, var6, 4, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 53, var6, 3, 1, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 85, 0, 6, 1, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 72, 0, 6, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 85, 0, 4, 1, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 72, 0, 4, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 58, 0, 7, 1, 1, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 0, 0, 1, 1, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 0, 0, 1, 2, 0, par3StructureBoundingBox);
        placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, getMetadataWithOffset(64, 1));

        if (getBlockIdAtCurrentPosition(par1World, 1, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 1, -1, -1, par3StructureBoundingBox) != 0) {
            placeBlockAtCurrentPosition(par1World, 67, getMetadataWithOffset(67, 3), 1, 0, -1, par3StructureBoundingBox);
        }

        for (var7 = 0; var7 < 6; ++var7) {
            for (int var8 = 0; var8 < 9; ++var8) {
                clearCurrentPositionBlocksUpwards(par1World, var8, 9, var7, par3StructureBoundingBox);
                fillCurrentPositionBlocksDownwards(par1World, 4, 0, var8, -1, var7, par3StructureBoundingBox);
            }
        }

        spawnVillagers(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
        return true;
    }

    /**
     * Returns the villager type to spawn in this component, based on the number
     * of villagers already spawned.
     */
    @Override
    protected int getVillagerType(int par1) {
        return 1;
    }
}
