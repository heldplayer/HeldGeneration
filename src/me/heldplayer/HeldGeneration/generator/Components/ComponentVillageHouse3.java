
package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;

import org.bukkit.World;

public class ComponentVillageHouse3 extends ComponentVillage {
    private int averageGroundLevel = -1;

    public ComponentVillageHouse3(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
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
    public static ComponentVillageHouse3 findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 9, 7, 12, par5);
        return canVillageGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentVillageHouse3(par6, par1Random, var7, par5) : null;
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

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 7 - 1, 0);
        }

        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 4, 4, 0, 0, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 2, 1, 6, 8, 4, 10, 0, 0, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 5, 8, 0, 10, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 7, 0, 4, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 3, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 8, 0, 0, 8, 3, 10, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 7, 2, 0, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 5, 2, 1, 5, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 2, 0, 6, 2, 3, 10, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 10, 7, 3, 10, 4, 4, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 3, 0, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 2, 3, 5, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 1, 8, 4, 1, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 4, 3, 4, 4, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 2, 8, 5, 3, 5, 5, false);
        placeBlockAtCurrentPosition(par1World, 5, 0, 0, 4, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 0, 4, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 8, 4, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 8, 4, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 8, 4, 4, par3StructureBoundingBox);
        int var4 = getMetadataWithOffset(53, 3);
        int var5 = getMetadataWithOffset(53, 2);
        int var6;
        int var7;

        for (var6 = -1; var6 <= 2; ++var6) {
            for (var7 = 0; var7 <= 8; ++var7) {
                placeBlockAtCurrentPosition(par1World, 53, var4, var7, 4 + var6, var6, par3StructureBoundingBox);

                if ((var6 > -1 || var7 <= 1) && (var6 > 0 || var7 <= 3) && (var6 > 1 || var7 <= 4 || var7 >= 6)) {
                    placeBlockAtCurrentPosition(par1World, 53, var5, var7, 4 + var6, 5 - var6, par3StructureBoundingBox);
                }
            }
        }

        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 4, 5, 3, 4, 10, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 7, 4, 2, 7, 4, 10, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 4, 4, 5, 10, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 6, 5, 4, 6, 5, 10, 5, 5, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 5, 6, 3, 5, 6, 10, 5, 5, false);
        var6 = getMetadataWithOffset(53, 0);
        int var8;

        for (var7 = 4; var7 >= 1; --var7) {
            placeBlockAtCurrentPosition(par1World, 5, 0, var7, 2 + var7, 7 - var7, par3StructureBoundingBox);

            for (var8 = 8 - var7; var8 <= 10; ++var8) {
                placeBlockAtCurrentPosition(par1World, 53, var6, var7, 2 + var7, var8, par3StructureBoundingBox);
            }
        }

        var7 = getMetadataWithOffset(53, 1);
        placeBlockAtCurrentPosition(par1World, 5, 0, 6, 6, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 7, 5, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 53, var7, 6, 6, 4, par3StructureBoundingBox);
        int var9;

        for (var8 = 6; var8 <= 8; ++var8) {
            for (var9 = 5; var9 <= 10; ++var9) {
                placeBlockAtCurrentPosition(par1World, 53, var7, var8, 12 - var8, var9, par3StructureBoundingBox);
            }
        }

        placeBlockAtCurrentPosition(par1World, 17, 0, 0, 2, 1, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 0, 2, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 0, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 4, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 5, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 6, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 8, 2, 1, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 2, 2, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 2, 3, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 8, 2, 4, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 8, 2, 5, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 8, 2, 6, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 2, 7, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 8, 2, 8, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 8, 2, 9, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 2, 2, 6, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 2, 2, 7, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 2, 2, 8, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 2, 2, 9, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 4, 4, 10, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 102, 0, 5, 4, 10, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 17, 0, 6, 4, 10, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 5, 0, 5, 5, 10, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 0, 0, 2, 1, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 0, 0, 2, 2, 0, par3StructureBoundingBox);
        placeBlockAtCurrentPosition(par1World, 50, 0, 2, 3, 1, par3StructureBoundingBox);
        placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, 2, 1, 0, getMetadataWithOffset(64, 1));
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, -1, 3, 2, -1, 0, 0, false);

        if (getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0) {
            placeBlockAtCurrentPosition(par1World, 67, getMetadataWithOffset(67, 3), 2, 0, -1, par3StructureBoundingBox);
        }

        for (var8 = 0; var8 < 5; ++var8) {
            for (var9 = 0; var9 < 9; ++var9) {
                clearCurrentPositionBlocksUpwards(par1World, var9, 7, var8, par3StructureBoundingBox);
                fillCurrentPositionBlocksDownwards(par1World, 4, 0, var9, -1, var8, par3StructureBoundingBox);
            }
        }

        for (var8 = 5; var8 < 11; ++var8) {
            for (var9 = 2; var9 < 9; ++var9) {
                clearCurrentPositionBlocksUpwards(par1World, var9, 7, var8, par3StructureBoundingBox);
                fillCurrentPositionBlocksDownwards(par1World, 4, 0, var9, -1, var8, par3StructureBoundingBox);
            }
        }

        spawnVillagers(par1World, par3StructureBoundingBox, 4, 1, 2, 2);
        return true;
    }
}
