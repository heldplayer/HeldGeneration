
package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenSand extends WorldGenerator {
    /** Stores ID for WorldGenSand */
    private int sandID;

    /** The maximum radius used when generating a patch of blocks. */
    private int radius;

    public WorldGenSand(int radius, int typeId) {
        this.sandID = typeId;
        this.radius = radius;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (world.getBlockTypeIdAt(x, y, z) != Mat.WaterMoving.id && world.getBlockTypeIdAt(x, y, z) != Mat.WaterStill.id) {
            return false;
        }
        else {
            int rad = rand.nextInt(this.radius - 2) + 2;
            byte height = 2;

            for (int posX = x - rad; posX <= x + rad; ++posX) {
                for (int posZ = z - rad; posZ <= z + rad; ++posZ) {
                    int relPosX = posX - x;
                    int relPosZ = posZ - z;

                    if (relPosX * relPosX + relPosZ * relPosZ <= rad * rad) {
                        for (int posY = y - height; posY <= y + height; ++posY) {
                            int typeId = world.getBlockTypeIdAt(posX, posY, posZ);

                            if (typeId == Mat.Dirt.id || typeId == Mat.Grass.id) {
                                setBlock(world, posX, posY, posZ, this.sandID);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
