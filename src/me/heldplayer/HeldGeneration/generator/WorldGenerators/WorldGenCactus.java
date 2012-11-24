
package me.heldplayer.HeldGeneration.generator.WorldGenerators;

import java.util.Random;

import me.heldplayer.HeldGeneration.helpers.BlockHelper;
import me.heldplayer.HeldGeneration.helpers.Mat;

import org.bukkit.World;

public class WorldGenCactus extends WorldGenerator {
    public boolean generate(World world, Random rand, int x, int y, int z) {
        for (int attempts = 0; attempts < 10; ++attempts) {
            int xPos = x + rand.nextInt(8) - rand.nextInt(8);
            int yPos = y + rand.nextInt(4) - rand.nextInt(4);
            int zPos = z + rand.nextInt(8) - rand.nextInt(8);

            if (world.getBlockTypeIdAt(xPos, yPos, zPos) == 0) {
                int height = 1 + rand.nextInt(rand.nextInt(3) + 1);

                for (int yOffset = 0; yOffset < height; ++yOffset) {
                    if (BlockHelper.canBlockStay(world.getBlockAt(xPos, yPos + yOffset, zPos), Mat.Cactus)) {
                        setBlock(world, xPos, yPos + yOffset, zPos, Mat.Cactus.id);
                    }
                }
            }
        }

        return true;
    }
}
