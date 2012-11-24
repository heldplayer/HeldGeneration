
package me.heldplayer.HeldGeneration.generator.Components;

import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Structures.StructureBoundingBox;
import me.heldplayer.HeldGeneration.generator.Structures.StructureComponent;
import me.heldplayer.HeldGeneration.helpers.MathHelper;

import org.bukkit.World;

public class ComponentVillageField2 extends ComponentVillage {
    private int averageGroundLevel = -1;

    public ComponentVillageField2(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
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
    public static ComponentVillageField2 findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
        StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, 0, 0, 0, 7, 4, 9, par5);
        return canVillageGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null ? new ComponentVillageField2(par6, par1Random, var7, par5) : null;
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

        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 6, 4, 8, 0, 0, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 7, 60, 60, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 1, 5, 0, 7, 60, 60, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 0, 0, 8, 17, 17, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 6, 0, 0, 6, 0, 8, 17, 17, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 5, 0, 0, 17, 17, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 8, 5, 0, 8, 17, 17, false);
        fillWithBlocks(par1World, par3StructureBoundingBox, 3, 0, 1, 3, 0, 7, 8, 8, false);
        int var4;

        for (var4 = 1; var4 <= 7; ++var4) {
            placeBlockAtCurrentPosition(par1World, 59, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 1, 1, var4, par3StructureBoundingBox);
            placeBlockAtCurrentPosition(par1World, 59, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 2, 1, var4, par3StructureBoundingBox);
            placeBlockAtCurrentPosition(par1World, 59, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 4, 1, var4, par3StructureBoundingBox);
            placeBlockAtCurrentPosition(par1World, 59, MathHelper.getRandomIntegerInRange(par2Random, 2, 7), 5, 1, var4, par3StructureBoundingBox);
        }

        for (var4 = 0; var4 < 9; ++var4) {
            for (int var5 = 0; var5 < 7; ++var5) {
                clearCurrentPositionBlocksUpwards(par1World, var5, 4, var4, par3StructureBoundingBox);
                fillCurrentPositionBlocksDownwards(par1World, 3, 0, var5, -1, var4, par3StructureBoundingBox);
            }
        }

        return true;
    }
}
