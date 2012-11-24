
package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenTrees extends WorldGenerator {
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** Indicator that the tree generator needs to grown vines on the trees. */
    private final boolean growVines;

    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;

    public WorldGenTrees(boolean notify) {
        this(notify, 4, 0, 0, false);
    }

    public WorldGenTrees(boolean notify, int minHeight, int logData, int leavesData, boolean growVines) {
        super(notify);
        this.minTreeHeight = minHeight;
        this.metaWood = logData;
        this.metaLeaves = leavesData;
        this.growVines = growVines;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(3) + this.minTreeHeight;
        boolean canGrow = true;

        if (y >= 1 && y + height + 1 <= 256) {
            int posY1;
            byte rad;
            int posZ1posY2;
            int typeId1relPosY2;

            for (posY1 = y; posY1 <= y + 1 + height; ++posY1) {
                rad = 1;

                if (posY1 == y) {
                    rad = 0;
                }

                if (posY1 >= y + 1 + height - 2) {
                    rad = 2;
                }

                for (int posX = x - rad; posX <= x + rad && canGrow; ++posX) {
                    for (posZ1posY2 = z - rad; posZ1posY2 <= z + rad && canGrow; ++posZ1posY2) {
                        if (posY1 >= 0 && posY1 < 256) {
                            typeId1relPosY2 = world.getBlockTypeIdAt(posX, posY1, posZ1posY2);

                            if (typeId1relPosY2 != 0 && typeId1relPosY2 != Mat.Leaves.id && typeId1relPosY2 != Mat.Grass.id && typeId1relPosY2 != Mat.Dirt.id && typeId1relPosY2 != Mat.Log.id) {
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
                posY1 = world.getBlockTypeIdAt(x, y - 1, z);

                if ((posY1 == Mat.Grass.id || posY1 == Mat.Dirt.id) && y < 256 - height - 1) {
                    this.setBlock(world, x, y - 1, z, Mat.Dirt.id);
                    rad = 3;
                    byte uselessByte = 0;
                    int relRelPosY2;
                    int posX2;
                    int relPosX2;

                    for (posZ1posY2 = y - rad + height; posZ1posY2 <= y + height; ++posZ1posY2) {
                        typeId1relPosY2 = posZ1posY2 - (y + height);
                        relRelPosY2 = uselessByte + 1 - typeId1relPosY2 / 2;

                        for (posX2 = x - relRelPosY2; posX2 <= x + relRelPosY2; ++posX2) {
                            relPosX2 = posX2 - x;

                            for (int posZ2 = z - relRelPosY2; posZ2 <= z + relRelPosY2; ++posZ2) {
                                int relPosZ = posZ2 - z;

                                if ((Math.abs(relPosX2) != relRelPosY2 || Math.abs(relPosZ) != relRelPosY2 || rand.nextInt(2) != 0 && typeId1relPosY2 != 0) && !BlockHelper.isOpaqueCube(world.getBlockTypeIdAt(posX2, posZ1posY2, posZ2))) {
                                    this.setBlockAndMetadata(world, posX2, posZ1posY2, posZ2, Mat.Leaves.id, this.metaLeaves);
                                }
                            }
                        }
                    }

                    for (posZ1posY2 = 0; posZ1posY2 < height; ++posZ1posY2) {
                        typeId1relPosY2 = world.getBlockTypeIdAt(x, y + posZ1posY2, z);

                        if (typeId1relPosY2 == 0 || typeId1relPosY2 == Mat.Leaves.id) {
                            this.setBlockAndMetadata(world, x, y + posZ1posY2, z, Mat.Log.id, this.metaWood);

                            if (this.growVines && posZ1posY2 > 0) {
                                if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x - 1, y + posZ1posY2, z) == 0) {
                                    this.setBlockAndMetadata(world, x - 1, y + posZ1posY2, z, Mat.Vine.id, 8);
                                }

                                if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x + 1, y + posZ1posY2, z) == 0) {
                                    this.setBlockAndMetadata(world, x + 1, y + posZ1posY2, z, Mat.Vine.id, 2);
                                }

                                if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x, y + posZ1posY2, z - 1) == 0) {
                                    this.setBlockAndMetadata(world, x, y + posZ1posY2, z - 1, Mat.Vine.id, 1);
                                }

                                if (rand.nextInt(3) > 0 && world.getBlockTypeIdAt(x, y + posZ1posY2, z + 1) == 0) {
                                    this.setBlockAndMetadata(world, x, y + posZ1posY2, z + 1, Mat.Vine.id, 4);
                                }
                            }
                        }
                    }

                    if (this.growVines) {
                        for (posZ1posY2 = y - 3 + height; posZ1posY2 <= y + height; ++posZ1posY2) {
                            typeId1relPosY2 = posZ1posY2 - (y + height);
                            relRelPosY2 = 2 - typeId1relPosY2 / 2;

                            for (posX2 = x - relRelPosY2; posX2 <= x + relRelPosY2; ++posX2) {
                                for (relPosX2 = z - relRelPosY2; relPosX2 <= z + relRelPosY2; ++relPosX2) {
                                    if (world.getBlockTypeIdAt(posX2, posZ1posY2, relPosX2) == Mat.Leaves.id) {
                                        if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(posX2 - 1, posZ1posY2, relPosX2) == 0) {
                                            this.growVines(world, posX2 - 1, posZ1posY2, relPosX2, 8);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(posX2 + 1, posZ1posY2, relPosX2) == 0) {
                                            this.growVines(world, posX2 + 1, posZ1posY2, relPosX2, 2);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(posX2, posZ1posY2, relPosX2 - 1) == 0) {
                                            this.growVines(world, posX2, posZ1posY2, relPosX2 - 1, 1);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockTypeIdAt(posX2, posZ1posY2, relPosX2 + 1) == 0) {
                                            this.growVines(world, posX2, posZ1posY2, relPosX2 + 1, 4);
                                        }
                                    }
                                }
                            }
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

    private void growVines(World world, int x, int y, int z, int face) {
        this.setBlockAndMetadata(world, x, y, z, Mat.Vine.id, face);
        int attempts = 4;

        while (true) {
            --y;

            if (world.getBlockTypeIdAt(x, y, z) != 0 || attempts <= 0) {
                return;
            }

            this.setBlockAndMetadata(world, x, y, z, Mat.Vine.id, face);
            --attempts;
        }
    }
}
