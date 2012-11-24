
package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTaiga1 extends WorldGenerator {
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int totalHeight = rand.nextInt(5) + 7;
        int leavesStart = totalHeight - rand.nextInt(2) - 3;
        int height = totalHeight - leavesStart;
        int rad = 1 + rand.nextInt(height + 1);
        boolean canGrow = true;

        if (y >= 1 && y + totalHeight + 1 <= 128) {
            int posY1typeId2;
            int posX1posY2;
            int posZ1posX2;
            int typeId1relPosX2;
            int ringRad;

            for (posY1typeId2 = y; posY1typeId2 <= y + 1 + totalHeight && canGrow; ++posY1typeId2) {
                if (posY1typeId2 - y < leavesStart) {
                    ringRad = 0;
                }
                else {
                    ringRad = rad;
                }

                for (posX1posY2 = x - ringRad; posX1posY2 <= x + ringRad && canGrow; ++posX1posY2) {
                    for (posZ1posX2 = z - ringRad; posZ1posX2 <= z + ringRad && canGrow; ++posZ1posX2) {
                        if (posY1typeId2 >= 0 && posY1typeId2 < 128) {
                            typeId1relPosX2 = world.getBlockTypeIdAt(posX1posY2, posY1typeId2, posZ1posX2);

                            if (typeId1relPosX2 != 0 && typeId1relPosX2 != Mat.Leaves.id) {
                                canGrow = false;
                            }
                        }
                        else {
                            canGrow = false;
                        }
                    }
                }
            }

            if (!canGrow) {
                return false;
            }
            else {
                posY1typeId2 = world.getBlockTypeIdAt(x, y - 1, z);

                if ((posY1typeId2 == Mat.Grass.id || posY1typeId2 == Mat.Dirt.id) && y < 128 - totalHeight - 1) {
                    this.setBlock(world, x, y - 1, z, Mat.Dirt.id);
                    ringRad = 0;

                    for (posX1posY2 = y + totalHeight; posX1posY2 >= y + leavesStart; --posX1posY2) {
                        for (posZ1posX2 = x - ringRad; posZ1posX2 <= x + ringRad; ++posZ1posX2) {
                            typeId1relPosX2 = posZ1posX2 - x;

                            for (int posZ = z - ringRad; posZ <= z + ringRad; ++posZ) {
                                int relPosZ = posZ - z;

                                if ((Math.abs(typeId1relPosX2) != ringRad || Math.abs(relPosZ) != ringRad || ringRad <= 0) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(posZ1posX2, posX1posY2, posZ))) {
                                    this.setBlockAndMetadata(world, posZ1posX2, posX1posY2, posZ, Mat.Leaves.id, 1);
                                }
                            }
                        }

                        if (ringRad >= 1 && posX1posY2 == y + leavesStart + 1) {
                            --ringRad;
                        }
                        else if (ringRad < rad) {
                            ++ringRad;
                        }
                    }

                    for (posX1posY2 = 0; posX1posY2 < totalHeight - 1; ++posX1posY2) {
                        posZ1posX2 = world.getBlockTypeIdAt(x, y + posX1posY2, z);

                        if (posZ1posX2 == 0 || posZ1posX2 == Mat.Leaves.id) {
                            this.setBlockAndMetadata(world, x, y + posX1posY2, z, Mat.Log.id, 1);
                        }
                    }

                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }
}
