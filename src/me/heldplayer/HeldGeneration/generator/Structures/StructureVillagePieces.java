
package me.heldplayer.HeldGeneration.generator.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import me.heldplayer.HeldGeneration.generator.Components.ComponentVillage;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageChurch;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageField;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageField2;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageHall;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageHouse1;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageHouse2;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageHouse3;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageHouse4_Garden;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillagePathGen;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageStartPiece;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageTorch;
import me.heldplayer.HeldGeneration.generator.Components.ComponentVillageWoodHut;
import me.heldplayer.HeldGeneration.generator.MapGenerators.MapGenVillage;
import me.heldplayer.HeldGeneration.helpers.MathHelper;

public class StructureVillagePieces {
    public static ArrayList<StructureVillagePieceWeight> getStructureVillageWeightedPieceList(Random rand, int par1) {
        ArrayList<StructureVillagePieceWeight> pieceTypes = new ArrayList<StructureVillagePieceWeight>();
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageHouse4_Garden.class, 4, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 4 + par1 * 2)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageChurch.class, 20, MathHelper.getRandomIntegerInRange(rand, 0 + par1, 1 + par1)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageHouse1.class, 20, MathHelper.getRandomIntegerInRange(rand, 0 + par1, 2 + par1)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageWoodHut.class, 3, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 5 + par1 * 3)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageHall.class, 15, MathHelper.getRandomIntegerInRange(rand, 0 + par1, 2 + par1)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageField.class, 3, MathHelper.getRandomIntegerInRange(rand, 1 + par1, 4 + par1)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageField2.class, 3, MathHelper.getRandomIntegerInRange(rand, 2 + par1, 4 + par1 * 2)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageHouse2.class, 15, MathHelper.getRandomIntegerInRange(rand, 0, 1 + par1)));
        pieceTypes.add(new StructureVillagePieceWeight(ComponentVillageHouse3.class, 8, MathHelper.getRandomIntegerInRange(rand, 0 + par1, 3 + par1 * 2)));
        Iterator<StructureVillagePieceWeight> pieceTypesIterator = pieceTypes.iterator();

        while (pieceTypesIterator.hasNext()) {
            if (((StructureVillagePieceWeight) pieceTypesIterator.next()).villagePiecesLimit == 0) {
                pieceTypesIterator.remove();
            }
        }

        return pieceTypes;
    }

    private static int getAvailablePieceWeight(List<StructureVillagePieceWeight> par0ArrayList) {
        boolean var1 = false;
        int var2 = 0;
        StructureVillagePieceWeight var4;

        for (Iterator<StructureVillagePieceWeight> var3 = par0ArrayList.iterator(); var3.hasNext(); var2 += var4.villagePieceWeight) {
            var4 = (StructureVillagePieceWeight) var3.next();

            if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit) {
                var1 = true;
            }
        }

        return var1 ? var2 : -1;
    }

    private static ComponentVillage getVillageComponentFromWeightedPiece(StructureVillagePieceWeight par0StructureVillagePieceWeight, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        Class<? extends ComponentVillage> var8 = par0StructureVillagePieceWeight.villagePieceClass;
        Object var9 = null;

        if (var8 == ComponentVillageHouse4_Garden.class) {
            var9 = ComponentVillageHouse4_Garden.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageChurch.class) {
            var9 = ComponentVillageChurch.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageHouse1.class) {
            var9 = ComponentVillageHouse1.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageWoodHut.class) {
            var9 = ComponentVillageWoodHut.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageHall.class) {
            var9 = ComponentVillageHall.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageField.class) {
            var9 = ComponentVillageField.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageField2.class) {
            var9 = ComponentVillageField2.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageHouse2.class) {
            var9 = ComponentVillageHouse2.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (var8 == ComponentVillageHouse3.class) {
            var9 = ComponentVillageHouse3.findValidPlacement(par1List, par2Random, par3, par4, par5, par6, par7);
        }

        return (ComponentVillage) var9;
    }

    /**
     * attempts to find a next Village Component to be spawned
     */
    private static ComponentVillage getNextVillageComponent(ComponentVillageStartPiece startPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        int var8 = getAvailablePieceWeight(startPiece.structureVillageWeightedPieceList);

        if (var8 <= 0) {
            return null;
        }
        else {
            int var9 = 0;

            while (var9 < 5) {
                ++var9;
                int var10 = par2Random.nextInt(var8);
                Iterator var11 = startPiece.structureVillageWeightedPieceList.iterator();

                while (var11.hasNext()) {
                    StructureVillagePieceWeight var12 = (StructureVillagePieceWeight) var11.next();
                    var10 -= var12.villagePieceWeight;

                    if (var10 < 0) {
                        if (!var12.canSpawnMoreVillagePiecesOfType(par7) || var12 == startPiece.structVillagePieceWeight && startPiece.structureVillageWeightedPieceList.size() > 1) {
                            break;
                        }

                        ComponentVillage var13 = getVillageComponentFromWeightedPiece(var12, par1List, par2Random, par3, par4, par5, par6, par7);

                        if (var13 != null) {
                            ++var12.villagePiecesSpawned;
                            startPiece.structVillagePieceWeight = var12;

                            if (!var12.canSpawnMoreVillagePieces()) {
                                startPiece.structureVillageWeightedPieceList.remove(var12);
                            }

                            return var13;
                        }
                    }
                }
            }

            StructureBoundingBox var14 = ComponentVillageTorch.findValidPlacement(par1List, par2Random, par3, par4, par5, par6);

            if (var14 != null) {
                return new ComponentVillageTorch(par7, par2Random, var14, par6);
            }
            else {
                return null;
            }
        }
    }

    /**
     * attempts to find a next Structure Component to be spawned, private
     * Village function
     */
    private static StructureComponent getNextVillageStructureComponent(ComponentVillageStartPiece startPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        if (par7 > 50) {
            return null;
        }
        else if (Math.abs(par3 - startPiece.getBoundingBox().minX) <= 112 && Math.abs(par5 - startPiece.getBoundingBox().minZ) <= 112) {
            ComponentVillage var8 = getNextVillageComponent(startPiece, par1List, par2Random, par3, par4, par5, par6, par7 + 1);

            if (var8 != null) {
                int var9 = (var8.boundingBox.minX + var8.boundingBox.maxX) / 2;
                int var10 = (var8.boundingBox.minZ + var8.boundingBox.maxZ) / 2;
                int var11 = var8.boundingBox.maxX - var8.boundingBox.minX;
                int var12 = var8.boundingBox.maxZ - var8.boundingBox.minZ;
                int var13 = var11 > var12 ? var11 : var12;

                if (startPiece.helper.areBiomesViable(var9, var10, var13 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
                    //if (par0ComponentVillageStartPiece.getWorldChunkManager().areBiomesViable(var9, var10, var13 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
                    par1List.add(var8);
                    startPiece.field_35108_e.add(var8);
                    return var8;
                }
            }

            return null;
        }
        else {
            return null;
        }
    }

    private static StructureComponent getNextComponentVillagePath(ComponentVillageStartPiece startPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        if (par7 > 3 + startPiece.terrainType) {
            return null;
        }
        else if (Math.abs(par3 - startPiece.getBoundingBox().minX) <= 112 && Math.abs(par5 - startPiece.getBoundingBox().minZ) <= 112) {
            StructureBoundingBox var8 = ComponentVillagePathGen.func_35087_a(startPiece, par1List, par2Random, par3, par4, par5, par6);

            if (var8 != null && var8.minY > 10) {
                ComponentVillagePathGen var9 = new ComponentVillagePathGen(par7, par2Random, var8, par6);
                int var10 = (var9.boundingBox.minX + var9.boundingBox.maxX) / 2;
                int var11 = (var9.boundingBox.minZ + var9.boundingBox.maxZ) / 2;
                int var12 = var9.boundingBox.maxX - var9.boundingBox.minX;
                int var13 = var9.boundingBox.maxZ - var9.boundingBox.minZ;
                int var14 = var12 > var13 ? var12 : var13;

                if (startPiece.helper.areBiomesViable(var10, var11, var14 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
                    //if (par0ComponentVillageStartPiece.getWorldChunkManager().areBiomesViable(var10, var11, var14 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
                    par1List.add(var9);
                    startPiece.field_35106_f.add(var9);
                    return var9;
                }
            }

            return null;
        }
        else {
            return null;
        }
    }

    /**
     * attempts to find a next Structure Component to be spawned
     */
    public static StructureComponent getNextStructureComponent(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        return getNextVillageStructureComponent(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6, par7);
    }

    public static StructureComponent getNextStructureComponentVillagePath(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7) {
        return getNextComponentVillagePath(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6, par7);
    }
}
